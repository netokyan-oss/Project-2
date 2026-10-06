import list.DoubleLinkedList;

/**
 * Represents a candidate in the dodgeball draft.
 */
public class Candidate {

    private String name;
    private DoubleLinkedList<String> orderedChoices;
    private String currentMatch;

    /**
     * Creates a candidate in the base state.
     */
    public Candidate() {
        name = "";
        orderedChoices = new DoubleLinkedList<>();
        currentMatch = null;
    }

    /**
     * Creates a candidate with a name.
     *
     * @param nm candidate name
     */
    public Candidate(String nm) {
        name = nm;
        orderedChoices = new DoubleLinkedList<>();
        currentMatch = null;
    }

    /**
     * Adds a choice at the requested priority.
     *
     * @param priority priority beginning at 1
     * @param nm choice name
     * @return true if successfully inserted
     */
    public boolean addChoice(int priority, String nm) {
        return orderedChoices.insert(priority, nm);
    }

    /**
     * Gets a choice at a priority.
     *
     * @param priority priority beginning at 1
     * @return choice
     */
    public String getChoice(int priority) {

        if (priority < 1 || priority > orderedChoices.getLength()) {
            throw new IndexOutOfBoundsException(
                    "Invalid priority: " + priority);
        }

        return orderedChoices.getEntry(priority);
    }

    /**
     * Sets the candidate's name.
     *
     * @param nm new name
     */
    public void setName(String nm) {
        name = nm;
    }

    /**
     * Gets the candidate's name.
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the current match.
     *
     * @return current match
     */
    public String getCurrentMatch() {
        return currentMatch;
    }

    /**
     * Sets the current match.
     *
     * @param nm match name
     */
    public void setCurrentMatch(String nm) {
        currentMatch = nm;
    }

    /**
     * Gets the priority of a name.
     *
     * @param nm name to find
     * @return priority, or -1 if not found
     */
    public int getPriorityOf(String nm) {
        return orderedChoices.getIndexOf(nm);
    }

    /**
     * Returns the candidate and current match.
     *
     * @return formatted candidate
     */
    @Override
    public String toString() {
        return name + " <-> " + currentMatch;
    }
}