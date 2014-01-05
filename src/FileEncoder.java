import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class FileEncoder {

    private ArrayList<Node> tree;
    private String fileUrl;
    private int[] frequencyArray = new int[256];

    public FileEncoder(String url) {
        this.fileUrl = url;
    }

    public void encode() {
        try {
            this.setLettersFrequency();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setLettersFrequency() throws IOException {
        InputStream inputFile = new FileInputStream(fileUrl);

        int data = inputFile.read(), nbOctetsCompte = 0;

        while(data != -1) {
            nbOctetsCompte++;
            frequencyArray[data]++;
            data = inputFile.read();
        }

        int nbCarac = 0;
        int i = 0;
        for( i = 0; i < 256 ; i++) {
            if(frequencyArray[i] != 0){
                System.out.println((char)i+" - "+frequencyArray[i]);
                nbCarac++;
            }

        }


    }

}
