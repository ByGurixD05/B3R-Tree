package main;

/**
 * Represents a B3R Tree (a type of tree with 3 pointers per node, used in various applications such as databases).
 * This tree structure supports insertion of integer values while maintaining the tree's properties.
 * It includes functionality for adding values to the tree, checking the number of keys, and ensuring the tree remains balanced.
 */
public class B3RTree {

    //----------ATTRIBUTES----------

    /** Root node of the tree. */
    private Node root;

    /** Total number of keys in the tree. */
    private int numKeysTree;

    /**
     * Default constructor that initializes an empty B3R tree.
     */
    public B3RTree() {
        root = null;
        numKeysTree = 0;
    }

    /**
     * Retrieves the total number of keys in the tree.
     * @return The number of keys in the tree.
     */
    public int getNumKeys() {
        return numKeysTree;
    }

    /**
     * Checks if the tree is empty.
     * @return {@code True} if the tree is empty, otherwise {@code False}.
     */
    private boolean isEmpty() {
        return root == null;
    }

    /**
     * Inserts a new value into the tree. If the tree is empty, it initializes a new root.
     * If the root has space, the value is inserted there. If not, the tree is traversed 
     * and the appropriate position is found for the new value.
     * Balancing of the tree may occur after insertion.
     * @param value The integer value to be inserted into the tree.
     */
    public void insertValue(int value) {
        if (isEmpty()) {
            root = new Node();
            root.insertKey(value);
            numKeysTree++;
            return;
        }

        // If root has space, insert the value into root.
        if (root.hasSpace()) {
            root.insertKey(value);
            numKeysTree++;
            if (!root.hasSpace()) root.setLeaf(false);
            return;
        }

        // Traverse the tree and find the appropriate place for the value.
        Node current = root;
        Node previous = null;
        char flag = ' ';

        while (!current.isLeaf()) {
            previous = current;
            if (current.hasSpace()) {
                current = (current.getKeys()[0] > value) ? current.getLeft() : current.getRight();
                flag = (current.getKeys()[0] > value) ? 'l' : 'r';
            } else {
                if (current.getKeys()[0] > value) {
                    current = current.getLeft();
                    flag = 'l';
                } else if (current.getKeys()[1] < value) {
                    current = current.getRight();
                    flag = 'r';
                } else {
                    current = current.getCenter();
                    flag = 'c';
                }
            }

            // If a null child is encountered and previous node is not a leaf, create a new node and insert the value.
            if (current == null) {
                current = new Node();
                current.insertKey(value);
                switch (flag) {
                    case 'l' -> previous.setLeft(current);
                    case 'r' -> previous.setRight(current);
                    case 'c' -> previous.setCenter(previous);
                }
            }
        }

        // Increment the number of keys and balance the tree.
        numKeysTree++;
        balanceTree();
    }

    /**
     * Balances the tree after an insertion to ensure that it maintains its correct structure.
     * This method is currently a placeholder and should be implemented to balance the tree.
     */
    private void balanceTree() {
        // Placeholder for future implementation of tree balancing.
    }
}