import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    private String selectedFile;
    private JLabel labelField = new JLabel();

    public Main() {
        this.setSize(500, 130);
        this.setLayout(new FlowLayout());

        JButton buttonFileCompressed = new JButton("Choix du fichier à compresser");
        buttonFileCompressed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setApproveButtonText("Choix du fichier à compresser");
                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                    selectedFile = chooser.getSelectedFile().getAbsolutePath();
                    labelField.setForeground(Color.gray);
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

        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}
