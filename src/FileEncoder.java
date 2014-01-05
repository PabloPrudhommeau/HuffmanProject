import java.io.*;
import java.util.ArrayList;

public class FileEncoder {

    private ArrayList<Node> tree;
    private String fileUrl;
    private int[] frequencyArrayLetters = new int[256];

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

    public void sortFrequencyArrayLetters(){

    }

    public void setLettersFrequency() throws IOException {
        InputStream inputFile = new FileInputStream(fileUrl);

        int data = inputFile.read();
        int bytesCount = 0;

        while(data != -1) {
            bytesCount++;
            frequencyArrayLetters[data]++;
            data = inputFile.read();
        }

        int charNumber = 0;
        int i = 0;
        for( i = 0; i < 256 ; i++) {
            if(frequencyArrayLetters[i] != 0){
                System.out.println((char)i+" - "+frequencyArrayLetters[i]);
                charNumber++;
            }
        }

    }

}
