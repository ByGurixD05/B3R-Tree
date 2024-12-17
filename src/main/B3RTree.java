package main;

public class B3RTree{
    private Node root;
    private int numNodes;
    private int numKeys;

    public B3RTree(){
        root = new Node();
        numKeys = 0;
        numNodes = 1;
    }
    
    public int getNumberofNodes(){
        return numNodes;
    }

    public int getNumberofKeys(){
        return numKeys;
    } 


    public void insertValue(int value){
        if(root.hasSpace()){
            root.insertKey(value);
            numKeys++;
        }
    }

    @Override
    public String toString(){
        return root.toString();
    }
}