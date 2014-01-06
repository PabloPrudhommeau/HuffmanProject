import java.io.*;
import java.util.ArrayList;
import java.util.ListIterator;

public class FileEncoder {

    private ArrayList<Node> nodeArray = new ArrayList<Node>();
    private String fileUrl;
    private int[] frequencyArrayLetters = new int[256];
    private char[] characterArray = new char[256];

    public FileEncoder(String url) {
        this.fileUrl = url;
    }

    public void encode() {
        this.buildCharTree();
//        this.showTree();

        this.buildFinalTree();
//        this.showTree();

        this.buildBinaryTable();
        this.showBinaryTable();
    }

    public void buildFinalTree() {
        ListIterator<Node> it = nodeArray.listIterator();
        while(it.hasNext()){
            Node leftNode = (Node) it.next();
            if(it.hasNext()){
                Node rightNode = this.nodeArray.get(it.nextIndex());
                Node sumNode = new Node();
                sumNode.setLeftNode(leftNode);
                sumNode.setRightNode(rightNode);
                sumNode.setValue(leftNode.getValue() + rightNode.getValue());
                it.add(sumNode);
                this.sortTree();
            }
        }
    }

    public void buildBinaryTable(){
        Node currentNode = this.nodeArray.get(this.nodeArray.size() - 1);
        currentNode.setBinaryCode(""); // Fonction récursive qui assigne une valeur binaire à chaque noeud
    }

    public void showTree(){
        for (Node node : nodeArray) {
            if(node instanceof NodeCharacter){
                NodeCharacter nodeCharacter = (NodeCharacter) node;
                if(nodeCharacter.getValue() != 0){
                    System.out.println(nodeCharacter.getCharacter() + " - " + nodeCharacter.getValue());
                }
            } else {
                System.out.println("SUM --> " + node.getValue());
                if(node.getLeftNode() instanceof NodeCharacter){
                    NodeCharacter nodeCharacter = (NodeCharacter) node.getLeftNode();
                    System.out.println("--- LeftNode - " + nodeCharacter.getCharacter() + " - " + nodeCharacter.getValue());
                } else {
                    System.out.println("--- LeftNode - " + node.getLeftNode().getValue());
                }

                if(node.getRightNode() instanceof NodeCharacter){
                    NodeCharacter nodeCharacter = (NodeCharacter) node.getRightNode();
                    System.out.println("--- RightNode - " + nodeCharacter.getCharacter() + " - " + nodeCharacter.getValue());
                } else {
                    System.out.println("--- RightNode - " + node.getRightNode().getValue());
                }
            }
        }
        System.out.println("-----------\n\n\n");
    }

    public void showBinaryTable(){
        for (Node node : nodeArray) {
            if(node instanceof NodeCharacter){
                NodeCharacter nodeCharacter = (NodeCharacter) node;
                if(nodeCharacter.getValue() != 0){
                    System.out.println(nodeCharacter.getCharacter() + " - " + nodeCharacter.getValue() + " - " + nodeCharacter.getBinaryCode());
                }
            }
        }
    }

    public void sortTree(){
        this.mergeSort(0, this.nodeArray.size() - 1);
    }

    public void buildCharTree() {
        InputStream inputFile = null;
        try {
            inputFile = new FileInputStream(fileUrl);
            int data = inputFile.read();

            while(data != -1) {
                frequencyArrayLetters[data]++;
                data = inputFile.read();
            }

            for( int i = 0; i < 256 ; i++) {
                this.characterArray[i] = (char) i;
                if(this.frequencyArrayLetters[i] != 0){
                    NodeCharacter nodeCharacter = new NodeCharacter(this.characterArray[i], this.frequencyArrayLetters[i]);
                    this.nodeArray.add(nodeCharacter);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.sortTree();
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
        int h = low;
        int i = low;
        int j = mid + 1;
        int k;

        Node[] arr2 = new Node[this.nodeArray.size()];

        while ((h <= mid) && (j <= high)) {
            if (this.nodeArray.get(h).getValue() <= this.nodeArray.get(j).getValue()) {
                arr2[i] = this.nodeArray.get(h);
                h++;
            }
            else {
                arr2[i] = this.nodeArray.get(j);
                j++;
            }
            i++;
        }

        if (h > mid) for (k=j; k<=high; k++) {
            arr2[i] = this.nodeArray.get(k);
            i++;
        }
        else for (k=h; k<=mid; k++) {
            arr2[i] = this.nodeArray.get(k);
            i++;
        }

        for (k=low; k<=high; k++)  {
            this.nodeArray.set(k, arr2[k]);
        }
    }

}
