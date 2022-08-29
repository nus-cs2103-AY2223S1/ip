package duke.task;

import java.time.LocalDate;

/**
 * Task for Duke application.
 *
 * @author Farrel Dwireswara Salim
 */
public abstract class Task {
    private final String description;
    private boolean isFinished;

    /**
     * Constructs a new Task instance.
     *
     * @param description the description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isFinished = false;
    }

    /**
     * Marks current task as finished.
     */
    public void markAsFinished() {
        this.isFinished = true;
    }

    /**
     * Marks current task as not finished.
     */
    public void markAsNotFinished() {
        this.isFinished = false;
    }

    protected String getStatusIcon() {
        return this.isFinished ? "X" : " ";
    }

    /**
     * Gets the string representation of the Task.
     *
     * @return the string which represents current Task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    /**
     * Transforms the Task to a string that is compatible to Duke's storage.
     *
     * @return the string to be saved in the storage.
     */
    public String toStorageRepresentation() {
        return this.getStatusIcon() + "|" + this.description;
    }

    /**
     * Returns true if the description of current Task matches with the keyword.
     *
     * @param keyWords the strings of keyword.
     * @return true if Task matches the keyword, false otherwise.
     */
    public boolean isContainKeyWord(String ... keyWords) {
        for (int i = 0; i < keyWords.length; i++) {
            if (this.description.contains(keyWords[i].trim())) {
                return true;
            }
        }

        return false;
    }

    /**
     * Returns true if the task happens on the given date.
     *
     * @param selectedDates the selected dates.
     * @return true if the task happens on the selected date, false otherwise.
     */
    public abstract boolean isOnGivenDate(LocalDate ... selectedDates);

}
