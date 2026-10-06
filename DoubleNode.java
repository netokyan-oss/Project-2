package list;

/**
 * A node used by the DoubleLinkedList.
 *
 * @param <T> the type of item stored in the node
 */
public class DoubleNode<T> {

    private T item;
    private DoubleNode<T> next;
    private DoubleNode<T> prev;

    /**
     * Creates an empty node.
     */
    public DoubleNode() {
        this(null, null, null);
    }

    /**
     * Creates a node containing an item.
     *
     * @param anItem item to store
     */
    public DoubleNode(T anItem) {
        this(anItem, null, null);
    }

    /**
     * Creates a node with an item, next node, and previous node.
     *
     * @param anItem item to store
     * @param nxt next node
     * @param prv previous node
     */
    public DoubleNode(T anItem, DoubleNode<T> nxt, DoubleNode<T> prv) {
        item = anItem;
        next = nxt;
        prev = prv;
    }

    /**
     * Sets the item.
     *
     * @param anItem new item
     */
    public void setItem(T anItem) {
        item = anItem;
    }

    /**
     * Sets the next node.
     *
     * @param nextNode next node
     */
    public void setNext(DoubleNode<T> nextNode) {
        next = nextNode;
    }

    /**
     * Sets the previous node.
     *
     * @param prevNode previous node
     */
    public void setPrev(DoubleNode<T> prevNode) {
        prev = prevNode;
    }

    /**
     * Gets the item.
     *
     * @return stored item
     */
    public T getItem() {
        return item;
    }

    /**
     * Gets the next node.
     *
     * @return next node
     */
    public DoubleNode<T> getNext() {
        return next;
    }

    /**
     * Gets the previous node.
     *
     * @return previous node
     */
    public DoubleNode<T> getPrev() {
        return prev;
    }
}