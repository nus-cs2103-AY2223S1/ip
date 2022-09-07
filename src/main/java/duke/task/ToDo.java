package duke.task;

import java.time.LocalDate;

/**
 * To Do task for Duke application.
 *
 * @author Farrel Dwireswara Salim
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo instance.
     *
     * @param description the description of the task.
     * @param tags the tags of the task.
     */
    public ToDo(String description, String ... tags) {
        super(description, tags);
    }

    /**
     * Gets the string representation of the ToDo.
     *
     * @return the string which represents the current ToDo.
     */
    @Override
    public String toString() {
        String tagInfo = super.getTagsString().isEmpty()
                ? ""
                : "[" + super.getTagsString() + "]";

        return String.format("[T]%s %s", super.toString(), tagInfo);
    }

    /**
     * Transforms the ToDo to a string that is compatible to Duke's storage.
     *
     * @return the string to be saved in the storage.
     */
    @Override
    public String toStorageRepresentation() {
        return String.format("T|%s#%s", super.toStorageRepresentation(),
                super.getTagsString());
    }

    /**
     * Returns false as ToDo task doesn't have any date associated with it.
     *
     * @param selectedDates the selected dates.
     * @return false.
     */
    @Override
    protected boolean isOnGivenDate(LocalDate ... selectedDates) {
        return false;
    }
}
