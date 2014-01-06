
public class Main {

    public Main() {
        FileEncoder encoder = new FileEncoder("res/file.txt");
        encoder.encode();

    }

    public static void main(String[] args) {

        new Main();

    }
}
