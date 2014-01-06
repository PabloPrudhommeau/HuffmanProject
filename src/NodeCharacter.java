public class NodeCharacter extends Node {

    private int initialFrequency;
    private char character;

    public NodeCharacter(char character, int frequency){
        this.character = character;
        this.value = frequency;
    }

    public char getCharacter() {
        return character;
    }
}
