import java.io.*;
import java.util.ArrayList;

public class FileDecoder {

    private String fileUrl;
    private ArrayList<Node> binaryTable = new ArrayList<Node>();
    public String out = "";

    public FileDecoder(ArrayList<Node> binaryTable, String fileUrl){
        this.fileUrl = fileUrl;
        this.binaryTable = binaryTable;
    }

    public void decode(){
        try {
            File file = new File(fileUrl);
            file.createNewFile();

            FileReader fr = new FileReader(this.fileUrl);
            BufferedReader br = new BufferedReader(fr);
            String binaryCode = "";

            int data = br.read();
            while(data != -1) {
                binaryCode += (char) data;
                for(Node node : binaryTable){
                    if(node instanceof NodeCharacter){
                        NodeCharacter nodeCharacter = (NodeCharacter) node;
                        if(nodeCharacter.getBinaryCode().equals(binaryCode)){
                            this.out += nodeCharacter.getCharacter();
                            binaryCode = "";
                        }
                    }
                }
                data = br.read();
            }

            br.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void write(String fileUrl){
        File file = new File(fileUrl);
        try {
            file.createNewFile();
            FileWriter fw = new FileWriter(fileUrl);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(out);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
