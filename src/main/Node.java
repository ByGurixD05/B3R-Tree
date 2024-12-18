package main;

import java.util.ArrayList;

/**
 * This class represents a node in a B3RTree (a type of B-tree). It contains
 * keys and child nodes, along with information about its size and whether
 * it is a leaf node.
 */
public class Node {

    /** List of keys stored in the node. */
    private ArrayList<Integer> keys;

    /** List of child nodes in the tree. */
    private ArrayList<Node> children;

    /** The number of keys currently stored in the node. */
    private int size;

    /** Indicates if the node is a leaf (does not have children). */
    private boolean isLeaf;

    /**
     * Constructs a new node with the specified degree. The node is initialized
     * with empty keys and null children.
     *
     * @param degree the maximum number of children a node can have (degree of the B-tree).
     */
    public Node(int degree) {
        keys = new ArrayList<>(degree - 1);
        children = new ArrayList<>(degree);
        for (int i = 0; i < degree - 1; i++) {
            keys.add(0);
            children.add(null);
        }
        children.add(null);
        size = 0;
        isLeaf = true;
    }

    /**
     * Returns the number of keys currently stored in the node.
     *
     * @return the size of the node.
     */
    public int getSize() {
        return size;
    }

    /**
     * Sets the number of keys stored in the node.
     *
     * @param size the new size of the node.
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Checks if the node is a leaf node (does not have children).
     *
     * @return true if the node is a leaf, false otherwise.
     */
    public boolean isLeaf() {
        return isLeaf;
    }

    /**
     * Sets whether the node is a leaf node.
     *
     * @param isLeaf true if the node should be a leaf, false otherwise.
     */
    public void setLeaf(boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    /**
     * Returns the list of keys stored in the node.
     *
     * @return the list of keys.
     */
    public ArrayList<Integer> getKeys() {
        return keys;
    }

    /**
     * Sets the list of keys stored in the node.
     *
     * @param keys the new list of keys.
     */
    public void setKeys(ArrayList<Integer> keys) {
        this.keys = keys;
    }

    /**
     * Returns the list of child nodes of the current node.
     *
     * @return the list of child nodes.
     */
    public ArrayList<Node> getChildren() {
        return children;
    }

    /**
     * Sets the list of child nodes for the current node.
     *
     * @param children the new list of child nodes.
     */
    public void setChildren(ArrayList<Node> children) {
        this.children = children;
    }
}