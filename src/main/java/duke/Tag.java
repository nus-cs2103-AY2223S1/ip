package duke;

public class Tag extends  Task {
    private String tag;

    public Tag(String tag) {
        super(tag);
        this.tag = tag;
    }

    /**
     * Checks if the command should be added to the list.
     *
     * @return boolean
     */
    @Override
    public boolean AddToList() {
        return false;
    }

    /**
     * Returns a String representation of the command.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "OK! I have tagged:\n" + this.tag + " as #fun";
    }
}
