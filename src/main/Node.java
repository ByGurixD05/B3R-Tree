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

    /**
     * Default constructor that initializes a node with no parent or children,
     * an empty keys array, and sets the node as a leaf.
     */
    public Node() {
        parent = left = center = right = null;
        keys = new Integer[2];
        leaf = true;
        keys = new Integer[2];
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
     * @return True if the node is a leaf, otherwise false.
     */
    public boolean isLeaf() {
        return this.leaf;
    }

    /**
     * Sets whether the node is a leaf.
     * @param leaf True to set the node as a leaf, otherwise false.
     */
    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    /**
     * Checks if the node has space for additional keys.
     * @return True if there is space, otherwise false.
     */
    public boolean hasSpace() {
        for (Integer key : keys) {
            if (key == null) {
                return true;
            }
        }
        return false;
    }

    //----------NODE OPERATION METHODS----------

    /**
     * Inserts a key into the node if space is available.
     * @param value The key value to be inserted.
     */
    public void insertKey(int value) {
        if (!hasSpace()) return;
        if (keys[0] == null || keys[1] == null) {
            if (keys[0] == null || keys[1] < value) {
                if (keys[0] != null) {
                    keys[1] = keys[0];
                }
                keys[0] = value;
            } else {
                keys[1] = value;
            }
        } else if (value < keys[0]) {
            keys[1] = keys[0];
            keys[0] = value;
        } else if (value < keys[1]) {
            keys[1] = value;
        }
    }

    /**
     * Removes a key from the node.
     * @param value The key value to be removed.
     */
    public void removeKey(int value) {
        for (int i = 0; i < keys.length; i++) {
            if (keys[i] == value) {
                keys[i] = null;
                return;
            }
        }
    }

    /**
     * Pops (removes and returns) a key from the node.
     * @param value The key value to be popped.
     * @return The popped key value, or null if not found.
     */
    public Integer popKey(int value) {
        for (int i = 0; i < keys.length; i++) {
            if (keys[i] != null && keys[i] == value) {
                Integer poppedValue = keys[i];
                keys[i] = null;
                return poppedValue;
            }
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