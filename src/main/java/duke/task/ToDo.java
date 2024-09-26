package duke.task;

/**
 * ToDo is a task without a specified date/time.
 */
public class ToDo extends Task {

    /**
     * Constructor for ToDo.
     *
     * @param description Description of the Deadline.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the String representation of the ToDo.
     *
     * @return String representation of the ToDo.
     */
    @Override
    public String toString() {
        String tagsString = super.getTagsString();
        return ("[T]" + super.toString() + tagsString);
    }

    /**
     * Returns the String representation to be stored.
     *
     * @return String storage representation of the ToDo.
     */
    @Override
    public String toStorageString() {
        String doneDescriptionString = super.toStorageString();
        String tagsString = super.getTagsStorageString();
        return "T" + Task.STORAGE_DELIMITER
                + doneDescriptionString
                + tagsString;
    }
}
