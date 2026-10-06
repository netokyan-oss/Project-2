package list;

/**
 * Represents a finger used to speed up searches in a
 * doubly linked list.
 *
 * @param <T> the type stored in the list
 */
public class Finger<T> {

    private DoubleNode<T> node;
    private int idx;

    /**
     * Creates a finger.
     *
     * @param idx index of the node
     * @param node node referenced by the finger
     */
    public Finger(int idx, DoubleNode<T> node) {
        this.idx = idx;
        this.node = node;
    }

    /**
     * Gets the index.
     *
     * @return index
     */
    public int getIndex() {
        return idx;
    }

    /**
     * Sets the index.
     *
     * @param idx new index
     */
    public void setIndex(int idx) {
        this.idx = idx;
    }

    /**
     * Gets the node.
     *
     * @return referenced node
     */
    public DoubleNode<T> getNode() {
        return node;
    }

    /**
     * Sets the node.
     *
     * @param node new node
     */
    public void setNode(DoubleNode<T> node) {
        this.node = node;
    }
}