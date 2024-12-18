package main;

/**
 * This class implemetn
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