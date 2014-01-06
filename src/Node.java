
public class Node {

    private Node leftNode;
    private Node rightNode;
    private String binaryCode = "";

    protected int value;

    public int getValue(){
        return this.value;
    }

    public void setValue(int frequency){
        this.value = frequency;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }

    public String getBinaryCode() {
        return binaryCode;
    }

    public void setBinaryCode(String binaryCode) {
        this.binaryCode = this.binaryCode+binaryCode;
        if(this.hasLeftNode()){
            this.leftNode.setBinaryCode(this.binaryCode+"0");
        }
        if(this.hasRightNode()){
            this.rightNode.setBinaryCode(this.binaryCode+"1");
        }
    }

    public boolean hasLeftNode() {
        return this.leftNode != null;
    }

    public boolean hasRightNode() {
        return this.rightNode != null;
    }

}
