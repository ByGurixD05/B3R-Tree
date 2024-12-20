package main;

/**
 * This class implements a B3R-Tree. This represents a BTree of degree 3. Maximum keys allowed = 2.
 */
public class B3RTree {

    /** The degree of the B3RTree, which determines the maximum number of children a node can have. */
    private final int degree;

    /** The root node of the tree. */
    private Node root;

    /**
     * Constructs a B3RTree with a default degree of 3 and an empty root node.
     */
    public B3RTree() {
        this.degree = 3;
        this.root = new Node(degree);

    }

    /**
     * Returns the root node of the tree.
     *
     * @return the root node.
     */
    public Node getRoot() {
        return root;
    }

    /**
     * Sets the root node of the tree.
     *
     * @param root the new root node.
     */
    public void setRoot(Node root) {
        this.root = root;
    }

    /**
     * Returns the upper bound on the number of keys a node can hold.
     * This is equal to degree - 1.
     *
     * @return the upper bound on the number of keys.
     */
    public int upperBoundKeys() {
        return degree - 1;
    }

    /**
     * Returns the lower bound on the number of keys a node must hold.
     * This is equal to degree / 2.
     *
     * @return the lower bound on the number of keys.
     */
    public int lowerBoundKeys() {
        return degree / 2;
    }

    /**
     * Returns the upper bound on the number of children a node can have.
     * This is equal to the degree of the tree.
     *
     * @return the upper bound on the number of children.
     */
    public int upperBoundChildren() {
        return degree;
    }

    /**
     * Counts the total number of nodes in the tree.
     *
     * @return the total number of nodes in the tree.
     */
    public int getNumNodes() {
        return countNodesHelper(root);
    }

    /**
     * Helper method for recursively counting the nodes in the tree.
     *
     * @param current the current node being traversed.
     * @return the total number of nodes in the subtree rooted at {@code current}.
     */
    private int countNodesHelper(Node current) {
        if (current == null) {
            return 0;
        }

        int count = 1; // Count the current node
        if (!current.isLeaf()) {
            for (int i = 0; i <= current.getSize(); i++) {
                count += countNodesHelper(current.getChildren().get(i));
            }
        }
        return count;
    }

    /**
     * Counts the total number of keys in the tree.
     *
     * @return the total number of keys in the tree.
     */
    public int getTotalKeys() {
        return countKeysHelper(root);
    }

    /**
     * Helper method for recursively counting the keys in the tree.
     *
     * @param current the current node being traversed.
     * @return the total number of keys in the subtree rooted at {@code current}.
     */
    private int countKeysHelper(Node current) {
        if (current == null) {
            return 0;
        }

        int keyCount = current.getSize(); // Count the keys in the current node
        if (!current.isLeaf()) {
            for (int i = 0; i <= current.getSize(); i++) {
                keyCount += countKeysHelper(current.getChildren().get(i));
            }
        }
        return keyCount;
    }


    /**
     * Inserts a key into the B3RTree. If the root node is full, it splits and a new root is created.
     *
     * @param key the key to be inserted.
     */
    public void insert(int key) {
        Node r = root;
        if (r.getSize() == upperBoundKeys()) {
            Node s = new Node(degree);
            root = s;
            s.setLeaf(false);
            s.setSize(0);
            s.getChildren().set(0, r);
            splitChild(s, 0, r);
            insertNonFull(s, key);
        } else {
            insertNonFull(r, key);
        }
    }

    /**
     * Splits a child node that is full into two nodes, promoting a key from the child to the parent node.
     *
     * @param parent the parent node.
     * @param index the index of the child to split.
     * @param child the child node to split.
     */
    private void splitChild(Node parent, int index, Node child) {
        Node sibling = new Node(degree);
        sibling.setLeaf(child.isLeaf());
        sibling.setSize(upperBoundKeys() - lowerBoundKeys() - 1);

        for (int j = 0; j < sibling.getSize(); j++) {
            sibling.getKeys().set(j, child.getKeys().get(j + lowerBoundKeys() + 1));
        }

        if (!child.isLeaf()) {
            for (int j = 0; j < sibling.getSize() + 1; j++) {
                sibling.getChildren().set(j, child.getChildren().get(j + lowerBoundKeys() + 1));
            }
        }

        child.setSize(lowerBoundKeys());
        parent.getChildren().add(index + 1, sibling);
        parent.getChildren().remove(degree);

        parent.getKeys().add(index, child.getKeys().get(lowerBoundKeys()));
        parent.getKeys().remove(upperBoundKeys());

        parent.setSize(parent.getSize() + 1);
    }

    /**
     * Inserts a key into a node that is not full. This is a recursive method.
     *
     * @param node the node where the key should be inserted.
     * @param key the key to be inserted.
     */
    private void insertNonFull(Node node, int key) {
        int i = node.getSize() - 1;
        if (node.isLeaf()) {
            while (i >= 0 && key < node.getKeys().get(i)) {
                i--;
            }
            node.getKeys().add(i + 1, key);
            node.getKeys().remove(upperBoundKeys());
            node.setSize(node.getSize() + 1);
        } else {
            while (i >= 0 && key < node.getKeys().get(i)) {
                i--;
            }
            i++;

            if (node.getChildren().get(i).getSize() == upperBoundKeys()) {
                splitChild(node, i, node.getChildren().get(i));
                if (key > node.getKeys().get(i)) {
                    i++;
                }
            }
            insertNonFull(node.getChildren().get(i), key);
        }
    }

    /**
     * Searches for a specific value in the tree.
     * 
     * @param value the value to search for.
     * @return {@code true} if the value is found in the tree, {@code false} otherwise.
     * @throws NullPointerException if the tree's root or its children are not properly initialized.
     */
    public boolean searchValue(int value){
        Node current = this.getRoot(); 
        int index;
    
        while (!current.isLeaf()) {
            index = current.getSize() - 1;
    
            while (index >= 0 && value < current.getKeys().get(index)) {
                index--;
            }
    
            if (index >= 0 && current.getKeys().get(index) == value) {
                return true;
            }

            current = current.getChildren().get(index + 1);
        }
    
        for (index = 0; index < current.getSize(); index++) {
            if (current.getKeys().get(index) == value) {
                return true;
            }
        }
    
        return false; 
    }

    /**
     * Retrieves the maximum value stored in the tree.
     * 
     * @return the maximum value in the tree.
     * @throws IllegalStateException if the tree is empty.
     */
    public int maxValue(){
        Node current = this.getRoot();

        if (current == null || current.getSize() == 0) {
            throw new IllegalStateException("El árbol está vacío.");
        }

        while (!current.isLeaf()) {
            current = current.getChildren().get(current.getSize());
        }

        return current.getKeys().get(current.getSize());
    }

    /**
     * Retrieves the minimum value stored in the tree.
     * 
     * @return the minimum value in the tree.
     * @throws IllegalStateException if the tree is empty.
     */
    public int minValue(){
        Node current = this.getRoot();

        if (current == null || current.getSize() == 0) {
            throw new IllegalStateException("El árbol está vacío.");
        }

        while (!current.isLeaf()) {
            current = current.getChildren().get(0);
        }   

        return current.getKeys().get(0);
    }

    /**
     * Returns a string representation of the tree, printing keys and levels.
     *
     * @return a string representation of the tree.
     */
    @Override
    public String toString() {
        return printTreeHelper(root, 0);
    }

    /**
     * Helper method to recursively print the tree structure.
     *
     * @param currentNode the current node to print.
     * @param depth the current depth in the tree (used for indentation).
     * @return a string representation of the tree starting from the current node.
     */
    public String printTreeHelper(Node currentNode, int depth) {
        StringBuilder result = new StringBuilder();
        String indent = " ".repeat(depth * 4);

        for (int i = 0; i < currentNode.getSize(); i++) {
            if (!currentNode.isLeaf() && currentNode.getChildren().get(i) != null) {
                result.append(printTreeHelper(currentNode.getChildren().get(i), depth + 1));
            }
            if (currentNode.getKeys().get(i) != null) {
                result.append(indent)
                      .append("Key: ")
                      .append(currentNode.getKeys().get(i))
                      .append(", Level: ")
                      .append(depth)
                      .append("\n");
            }
        }

        if (!currentNode.isLeaf()) {
            result.append(printTreeHelper(currentNode.getChildren().get(currentNode.getSize()), depth + 1));
        }

        return result.toString();
    }
}