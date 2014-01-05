import java.io.FileNotFoundException;
import java.io.FileReader;

public class FileManager {

    private FileReader file;

    public void load(String url){
        try {
            this.file = new FileReader(url);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void

}
