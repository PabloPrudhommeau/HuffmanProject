import java.io.*;
import java.util.ArrayList;

public class FileEncoder {

    private ArrayList<Node> tree;
    private String fileUrl;
    private int[] frequencyArrayLetters = new int[256];
    private int[] arr2 = new int[256];

    public FileEncoder(String url) {
        this.fileUrl = url;
    }

    public void encode() {
        try {
            this.setLettersFrequency();
            this.mergeSort(0, 255);
            System.out.println("Merge Sort");
               int i =0;
            for( i = 0; i < 256 ; i++) {
                if(frequencyArrayLetters[i] != 0){
                    System.out.println(frequencyArrayLetters[i]);
                }
            }

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

    public void mergeSort(int low, int high) {
        if (low < high) {
            int mid = (low+high) / 2;
            this.mergeSort(low,mid);
            this.mergeSort(mid+1, high);
            this.merge(low, mid, high);
        }
    }

    public void merge(int low, int mid, int high) {
        int h = low, i = low, j = mid+1, k;
        while ((h <= mid) && (j <= high)) {
            if (this.frequencyArrayLetters[h] <= this.frequencyArrayLetters[j]) {
                arr2[i] = this.frequencyArrayLetters[h]; h++;
            }
            else {
                arr2[i] = this.frequencyArrayLetters[j]; j++;
            } i++;
        }
        if (h > mid) for (k=j; k<=high; k++) {
            arr2[i] = this.frequencyArrayLetters[k]; i++;
        }
        else for (k=h; k<=mid; k++) {
            arr2[i] = this.frequencyArrayLetters[k]; i++;
        }
        for (k=low; k<=high; k++) this.frequencyArrayLetters[k] = arr2[k];

    }

}