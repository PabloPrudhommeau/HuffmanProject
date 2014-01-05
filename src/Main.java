
public class Main {

    public Main() {
        FileManager file = new FileManager();
        file.load("res/file.txt");

        FileEncoder encoder = new FileEncoder();
        encoder.encode(file);

    }

    public static void main(String[] args) {

        System.out.println("Hello World!"); //commentaire

    }
}
