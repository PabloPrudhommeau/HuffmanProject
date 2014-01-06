import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Main extends JFrame {

    private String selectedFile;
    private String selectedFolder;

    public Main() {

        this.setVisible(true);
        this.setSize(400, 300);
        this.setLayout(new GridBagLayout());

        JButton buttonFileCompressed = new JButton("Choix du fichier à compresser");
        buttonFileCompressed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setApproveButtonText("Choix du fichier à compresser");
                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                    selectedFile = chooser.getSelectedFile().getAbsolutePath();
                }
            }
        });

        JButton buttonFolderOut = new JButton("Choix du répertoire de sortie");
        buttonFolderOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setApproveButtonText("Choix du répertoire de sortie");
                chooser.setFileSelectionMode((JFileChooser.DIRECTORIES_ONLY));
                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                    selectedFolder = chooser.getSelectedFile().getAbsolutePath();
                }
            }
        });

        JButton buttonLaunchCompression = new JButton("Lancer la compression");
        buttonLaunchCompression.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileEncoder encoder = new FileEncoder(selectedFile);
                encoder.encode();
                encoder.write(selectedFile + ".packed");

                FileDecoder decoder = new FileDecoder(encoder.getBinaryTable(), selectedFile+".packed");
                decoder.decode();
                decoder.write(selectedFile+".unpacked");
            }
        });

        this.add(buttonFileCompressed);
        this.add(buttonFolderOut);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;       //reset to default
        c.weighty = 1.0;   //request any extra vertical space
        c.anchor = GridBagConstraints.PAGE_END; //bottom of space
        c.insets = new Insets(10,0,0,0);  //top padding
        c.gridx = 0;       //aligned with button 2
        c.gridwidth = 3;   //2 columns wide
        c.gridy = 0;       //third row
        this.getContentPane().add(buttonLaunchCompression, c);
    }

    public static void main(String[] args) {
        new Main();
    }
}
