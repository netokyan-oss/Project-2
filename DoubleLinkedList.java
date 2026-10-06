package list;

import java.util.Objects;

/**
 * A generic doubly linked list with finger searching.
 *
 * @param <T> the type of item stored in the list
 */
public class DoubleLinkedList<T> {

    private DoubleNode<T> head;
    private int itemCount;
    private Finger<T> finger;

    /**
     * Creates an empty doubly linked list.
     */
    public DoubleLinkedList() {
        head = null;
        itemCount = 0;
        finger = new Finger<>(1, head);
    }

    /**
     * Determines whether the list is empty.
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return itemCount == 0;
    }

    /**
     * Gets the number of items in the list.
     *
     * @return list length
     */
    public int getLength() {
        return itemCount;
    }

    /**
     * Inserts an item at the specified position.
     *
     * @param position position beginning at 1
     * @param entry item to insert
     * @return true if successful
     */
    public boolean insert(int position, T entry) {

        if (position < 1 || position > itemCount + 1) {
            return false;
        }

        DoubleNode<T> newNode =
                new DoubleNode<>(entry, null, null);

        if (position == 1) {

            newNode.setNext(head);

            if (head != null) {
                head.setPrev(newNode);
            }

            head = newNode;

        } else {

            DoubleNode<T> previous = getNodeAt(position - 1);
            DoubleNode<T> next = previous.getNext();

            newNode.setPrev(previous);
            newNode.setNext(next);

            previous.setNext(newNode);

            if (next != null) {
                next.setPrev(newNode);
            }
        }

        itemCount++;

        updateFinger(newNode, position);

        return true;
    }

    /**
     * Removes an item at the specified position.
     *
     * @param position position beginning at 1
     */
    public void remove(int position) {

        if (position < 1 || position > itemCount) {
            throw new IndexOutOfBoundsException(
                    "Position out of bounds: " + position);
        }

        DoubleNode<T> node = getNodeAt(position);

        DoubleNode<T> previous = node.getPrev();
        DoubleNode<T> next = node.getNext();

        if (previous == null) {
            head = next;
        } else {
            previous.setNext(next);
        }

        if (next != null) {
            next.setPrev(previous);
        }

        itemCount--;

        if (itemCount == 0) {
            finger = new Finger<>(1, head);
        } else {
            finger = new Finger<>(1, head);
        }
    }

    /**
     * Removes all items from the list.
     */
    public void clear() {
        head = null;
        itemCount = 0;
        finger = new Finger<>(1, head);
    }

    /**
     * Gets the item at a position.
     *
     * @param position position beginning at 1
     * @return item at position
     */
    public T getEntry(int position) {

        if (position < 1 || position > itemCount) {
            throw new IndexOutOfBoundsException(
                    "Position out of bounds: " + position);
        }

        return getNodeAt(position).getItem();
    }

    /**
     * Replaces an item at a position.
     *
     * @param position position beginning at 1
     * @param entry new item
     * @return old item
     */
    public T replace(int position, T entry) {

        if (position < 1 || position > itemCount) {
            throw new IndexOutOfBoundsException(
                    "Position out of bounds: " + position);
        }

        DoubleNode<T> node = getNodeAt(position);

        T oldItem = node.getItem();

        node.setItem(entry);

        updateFinger(node, position);

        return oldItem;
    }

    /**
     * Finds the position of a key.
     *
     * @param key item to find
     * @return position of the item, or -1 if not found
     */
    public int getIndexOf(T key) {

        DoubleNode<T> current = head;
        int index = 1;

        while (current != null) {

            if (Objects.equals(current.getItem(), key)) {
                updateFinger(current, index);
                return index;
            }

            current = current.getNext();
            index++;
        }

        return -1;
    }

    /**
     * Converts the list to an array.
     *
     * @return array containing list items
     */
    public Object[] toArray() {

        Object[] result = new Object[itemCount];

        DoubleNode<T> current = head;

        for (int i = 0; i < itemCount; i++) {
            result[i] = current.getItem();
            current = current.getNext();
        }

        return result;
    }

    /**
     * Gets the node at a position using finger searching.
     *
     * @param position position beginning at 1
     * @return node at position
     */
    private DoubleNode<T> getNodeAt(int position) {

        if (position < 1 || position > itemCount) {
            throw new IndexOutOfBoundsException(
                    "Position out of bounds: " + position);
        }

        Finger<T> closest = getClosest(position);

        DoubleNode<T> current = closest.getNode();
        int currentIndex = closest.getIndex();

        if (currentIndex < position) {

            while (currentIndex < position) {
                current = current.getNext();
                currentIndex++;
            }

        } else {

            while (currentIndex > position) {
                current = current.getPrev();
                currentIndex--;
            }
        }

        return current;
    }

    /**
     * Updates the finger to a node and position.
     *
     * @param curr node
     * @param position position of node
     */
    private void updateFinger(DoubleNode<T> curr, int position) {

        finger.setNode(curr);
        finger.setIndex(position);
    }

    /**
     * Finds the closest reference between the head and finger.
     *
     * @param idx desired index
     * @return closest reference
     */
    private Finger<T> getClosest(int idx) {

        if (head == null) {
            return new Finger<>(1, null);
        }

        int headIndex = 1;

        int fingerIndex = finger.getIndex();

        if (finger.getNode() == null) {
            return new Finger<>(headIndex, head);
        }

        int headDistance = Math.abs(idx - headIndex);
        int fingerDistance = Math.abs(idx - fingerIndex);

        if (headDistance <= fingerDistance) {
            return new Finger<>(headIndex, head);
        }

        return new Finger<>(fingerIndex, finger.getNode());
    }

    /**
     * Returns a string representation of the list.
     *
     * @return formatted list
     */
    @Override
    public String toString() {

        if (isEmpty()) {
            return "Empty List.";
        }

        StringBuilder result = new StringBuilder();

        DoubleNode<T> current = head;
        int index = 1;

        while (current != null) {

            result.append("[")
                  .append(index)
                  .append("] ")
                  .append(current.getItem());

            current = current.getNext();

            if (current != null) {
                result.append(System.lineSeparator());
            }

            index++;
        }

        return result.toString();
    }
}