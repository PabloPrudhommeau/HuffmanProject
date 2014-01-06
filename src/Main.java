import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    private String selectedFile;
    private JLabel labelField = new JLabel();
    private JPanel statistics = new JPanel();

    public Main() {
        this.setSize(500, 150);
        this.getContentPane().setBackground(Color.darkGray);
        this.setLayout(new FlowLayout());
        this.setResizable(false);

        this.statistics.setBackground(Color.white);
        this.statistics.setPreferredSize(new Dimension(500,50));

        JButton buttonFileCompressed = new JButton("Choix du fichier à compresser");
        buttonFileCompressed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setApproveButtonText("Choix du fichier à compresser");
                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                    selectedFile = chooser.getSelectedFile().getAbsolutePath();
                    labelField.setForeground(Color.white);
                    labelField.setText("Fichier choisi : "+selectedFile);
                }
            }
        });

        JButton buttonLaunchCompression = new JButton("Lancer la compression");
        buttonLaunchCompression.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                labelField.setText("");

                if(selectedFile == null){
                    labelField.setForeground(Color.red);
                    labelField.setText("Erreur : veuillez choisir un fichier à compresser");
                } else {
                    FileEncoder encoder = new FileEncoder(selectedFile);
                    encoder.encode();
                    encoder.write(selectedFile + ".packed");

                    float textInitialWeight = encoder.getTextCharacterNumber() * 8;
                    float textCompressedWeight = encoder.getCompressedFileWeight();
                    float compressionRatio = (textCompressedWeight / textInitialWeight) * 100;

                    JLabel compressionRatioLabel = new JLabel("Taux de compression : " + String.valueOf(compressionRatio) + "% ");
                    compressionRatioLabel.setForeground(Color.black);
                    statistics.removeAll();
                    statistics.add(compressionRatioLabel);

                    FileDecoder decoder = new FileDecoder(encoder.getBinaryTable(), selectedFile+".packed");
                    decoder.decode();
                    decoder.write(selectedFile+".unpacked");
                    labelField.setForeground(Color.GREEN);
                    labelField.setText("Compression terminée !");
                }
            }
        });

        this.add(buttonFileCompressed);
        this.add(buttonLaunchCompression);
        this.add(labelField);

        this.add(statistics);

        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}
