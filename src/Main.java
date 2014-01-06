
public class Main {

    public Main() {
        FileEncoder encoder = new FileEncoder("res/Les_miserables_Tome_I_Chapitre_1-5.txt");
        encoder.encode();
        encoder.write("res/file[zipped].txt");

        FileDecoder decoder = new FileDecoder(encoder.getBinaryTable(), "res/file[zipped].txt");
        decoder.decode();
        decoder.write("res/file[unzipped].txt");
    }

    public static void main(String[] args) {
        new Main();
    }
}
