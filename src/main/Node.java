package main;

/**
 * Represents a node structure for a B3R Tree Implementation.
 * Each node contains references to its parent and children (left, center, right), 
 * an array of keys, and a flag indicating if it is a leaf node.
 */
public class Node {

    //----------ATTRIBUTES----------

    /** Pointer to the parent node. */
    private Node parent;

    /** Pointer to the left child node. */
    private Node left;

    /** Pointer to the center child node. */
    private Node center;

    /** Pointer to the right child node. */
    private Node right;

    /** Array storing up to two integer keys. */
    private Integer keys[];

    /** Indicates whether the node is a leaf node. */
    private boolean leaf;

    /** Number of keys currently in the node. */
    private int numKeys;

    /**
     * Default constructor that initializes a node with no parent or children,
     * an empty keys array, and sets the node as a leaf.
     */
    public Node() {
        parent = left = center = right = null;
        keys = new Integer[2]; // Array to hold keys, with a maximum of 2 keys.
        leaf = true; // Node is a leaf by default.
        numKeys = 0; // No keys initially.
    }

    //----------SETTERS AND GETTERS----------

    /**
     * Retrieves the parent node.
     * @return The parent node.
     */
    public Node getParent() {
        return parent;
    }

    /**
     * Sets the parent node.
     * @param parent The parent node to be set.
     */
    public void setParent(Node parent) {
        this.parent = parent;
    }

    /**
     * Retrieves the number of keys currently in the node.
     * @return The number of keys.
     */
    public int getNumKeys(){
        return numKeys;
    }

    /**
     * Retrieves the left child node.
     * @return The left child node.
     */
    public Node getLeft() {
        return left;
    }

    /**
     * Sets the left child node.
     * @param left The left child node to be set.
     */
    public void setLeft(Node left) {
        this.left = left;
    }

    /**
     * Retrieves the center child node.
     * @return The center child node.
     */
    public Node getCenter() {
        return center;
    }

    /**
     * Sets the center child node.
     * @param center The center child node to be set.
     */
    public void setCenter(Node center) {
        this.center = center;
    }

    /**
     * Retrieves the right child node.
     * @return The right child node.
     */
    public Node getRight() {
        return right;
    }

    /**
     * Sets the right child node.
     * @param right The right child node to be set.
     */
    public void setRight(Node right) {
        this.right = right;
    }

    /**
     * Retrieves the keys stored in the node.
     * @return An array of keys.
     */
    public Integer[] getKeys() {
        return keys;
    }

    /**
     * Sets the keys stored in the node.
     * @param keys An array of keys to be set.
     */
    public void setKeys(Integer[] keys) {
        this.keys = keys;
    }

    /**
     * Checks if the node is a leaf.
     * @return {@code True} if the node is a leaf, otherwise {@code False}.
     */
    public boolean isLeaf() {
        return this.leaf;
    }

    /**
     * Sets whether the node is a leaf.
     * @param leaf {@code True} to set the node as a leaf, otherwise {@code False}.
     */
    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    /**
     * Checks if the node has space for additional keys.
     * @return {@code True} if there is space, otherwise {@code False}.
     */
    public boolean hasSpace() {
        for (Integer key : keys) {
            if (key == null) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the node has a child.
     * @return {@code True} if it has children, otherwise {@code False}.
     */
    public boolean hasChildren() {
        return left != null || center != null || right != null;
    }

    /**
     * Retrieves the pointer type of the current node's position.
     * @param current The current node whose child pointer flag is to be determined.
     * @return 'r' for right, 'l' for left, or 'c' for center.
     */
    public char getChildPointerFlag(Node current){
        return 
        (current.getParent().getRight() == right) ? 'r' : 
        (current.getParent().getLeft() == left)   ? 'l' : 
        'c'; // Determines whether the current node is the left, center, or right child.
    }

    //----------NODE OPERATION METHODS----------

    /**
     * Inserts a key into the node if space is available.
     * @param value The key value to be inserted.
     */
    public void insertKey(int value) {
        if (!hasSpace()) return; // No space available, do nothing.

        // Insert the value into the correct position in the keys array.
        if (keys[0] == null || keys[0] > value) {
            if (keys[0] != null) {
                keys[1] = keys[0]; // Move the first key to the second position.
            }
            keys[0] = value;
            numKeys++;
        } else {
            keys[1] = value; // Insert the value in the second key position.
            numKeys++;
        }
    }

    /**
     * Removes a key from the node.
     * @param value The key value to be removed.
     */
    public void removeKey(int value) {
        if (keys[0] != null && keys[0] == value) {
            keys[0] = null;

            if (keys[1] != null) {
                keys[0] = keys[1];
                keys[1] = null;
            }
            numKeys--;
        } else if (keys[1] != null && keys[1] == value) {
            keys[1] = null;
            numKeys--;
        }
    }

    /**
     * Pops (removes and returns) a key from the node.
     * @param value The key value to be popped.
     * @return The popped key value, or null if not found.
     */
    public Integer popKey(int value) {
        if (keys[0] != null && keys[0] == value) {
            Integer poppedValue = keys[0];
            keys[0] = null;

            if (keys[1] != null) {
                keys[0] = keys[1];
                keys[1] = null;
            }
            numKeys--;
            return poppedValue;
        } else if (keys[1] != null && keys[1] == value) {
            Integer poppedValue = keys[1];
            keys[1] = null;
            numKeys--;
            return poppedValue;
        }

        return null;
    }

    //----------REPRESENTATION NODE METHODS----------

    /**
     * Returns a string representation of the node's keys.
     * @return A string in the format [k1, k2].
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < keys.length; i++) {
            sb.append(keys[i] == null ? "null" : keys[i]);
            if (i < keys.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}