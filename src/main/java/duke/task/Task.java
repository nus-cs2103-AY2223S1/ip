package duke.task;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Task for Duke application.
 *
 * @author Farrel Dwireswara Salim
 */
public abstract class Task {
    private final String description;
    private boolean isFinished;
    private final Set<String> tags;

    /**
     * Constructs a new Task instance.
     *
     * @param description the description of the task.
     * @param tags the tags of the task.
     */
    public Task(String description, String ... tags) {
        this.description = description;
        this.isFinished = false;

        this.tags = new HashSet<>();
        for (int i = 0; i < tags.length; i++) {
            this.tags.add(tags[i].trim().toLowerCase());
        }
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

    protected String getTagsString() {
        if (this.tags.isEmpty()) {
            return "";
        }

        StringBuilder tagString = new StringBuilder();

        for (String tag : tags) {
            tagString.append(tag + ",");
        }

        tagString.deleteCharAt(tagString.length() - 1);
        return tagString.toString();
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
     * @param keywords the strings of keyword.
     * @return true if Task matches the keyword, false otherwise.
     */
    protected boolean containsKeyword(String ... keywords) {
        for (int i = 0; i < keywords.length; i++) {
            if (this.description.toLowerCase()
                    .contains(keywords[i].trim().toLowerCase())) {
                return true;
            }
        }

        return false;
    }

    /**
     * Returns true if the current Task matches any of the tags.
     *
     * @param chosenTags the chosen tags.
     * @return true if the task contains any of the chosen tags, false otherwise.
     */
    protected boolean containsAnyTags(String ... chosenTags) {
        for (int i = 0; i < chosenTags.length; i++) {
            if (this.tags.contains(chosenTags[i].trim().toLowerCase())) {
                return true;
            }
        }

        return false;
    }

    /**
     * Returns true if the current Task matches all the tags.
     *
     * @param chosenTags the chosen tags.
     * @return true if the task contains all the chosen tags, false otherwise.
     */
    protected boolean containsAllTags(String ... chosenTags) {
        for (int i = 0; i < chosenTags.length; i++) {
            if (!this.tags.contains(chosenTags[i].trim().toLowerCase())) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns true if the task happens on the given date.
     *
     * @param selectedDates the selected dates.
     * @return true if the task happens on the selected date, false otherwise.
     */
    protected abstract boolean isOnGivenDate(LocalDate ... selectedDates);

}
