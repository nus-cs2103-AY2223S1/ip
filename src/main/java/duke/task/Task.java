package duke.task;

import duke.tag.Tag;
import duke.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Represents a task the user wishes to complete.
 */
public abstract class Task {
    protected DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEE, dd LLL yyyy");
    protected DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mma");
    protected DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(
            "EEE, dd LLL yyyy, hh:mma"
    );
    private String description;
    private boolean isDone;
    private ArrayList<Tag> tagList = new ArrayList<>();

    /**
     * TaskType values are used to indicate the type of the Task
     * to retrieve the corresponding information.
     */
    public enum TaskType {
        ToDo, Deadline, Event
    }

    /**
     * Constructs a new task.
     *
     * @param description A brief description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Sets the status of the task to done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Sets the status of the task to not done.
     */
    public void setUndone() {
        this.isDone = false;
    }

    /**
     * Converts the information of the task to a format suitable
     * for saving in a data file.
     *
     * @return A description of the task to be saved in a data file.
     */
    public String fileDescription() {
        if (isDone) {
            return "1 | " + this.description;
        } else {
            return "0 | " + this.description;
        }
    }

    /**
     * Converts the tags the task has been tagged with into a format
     * suitable for saving in a data file.
     *
     * @return A list of tags the task has been tagged with for saving to a data file.
     */
    public String fileTags() {
        if (tagList.size() == 0) {
            return "";
        }

        String tags = " |";
        for (Tag tag : tagList) {
            tags += " " + tag.toString();
        }
        return tags;
    }

    /**
     * Indicates whether the task description contains the given search term.
     *
     * @param searchTerm Search term to search for.
     * @return True if the task description contains the given search term.
     */
    public boolean containsSearchTerm(String searchTerm) {
        return this.description.contains(searchTerm);
    }

    /**
     * Tags the task with the given tag.
     *
     * @param tag Tag to tag the task with.
     * @return A string informing the user whether the task has been tagged.
     */
    public String addTag(Tag tag) {
        if (tagList.contains(tag)) {
            return Ui.START + "hmm, this task already has the tag '" + tag.toString() + "'!";
        }
        tagList.add(tag);
        Tag.addTaskToTag(this, tag);
        return Ui.START + "okay! the task has been tagged as '" + tag.toString() + "'.";
    }

    /**
     * Deletes the given tag from the task.
     *
     * @param tag Tag to delete from the task.
     * @return A string informing the user whether the tag has been deleted.
     */
    public String deleteTag(Tag tag) {
        if (!tagList.contains(tag)) {
            return Ui.START + "hmm, this task has not been tagged as '" + tag.toString() + "'.";
        }

        tagList.remove(tag);
        Tag.deleteTaskFromTag(this, tag);
        return Ui.START + "okay! the task is no longer tagged as '" + tag.toString() + "'.";
    }

    /**
     * Prints out a list of tags the task has been tagged with, if any.
     *
     * @return A string containing a list of tags the task has been tagged with,
     *     if any.
     */
    public String printTags() {
        if (tagList.size() == 0) {
            return Ui.START + "the task has not been tagged with any tags.";
        }

        String tags = Ui.START + "here's a list of the tags you have added so far to this task: ";
        for (Tag tag : tagList) {
            tags += "\n  " + tag.toString();
        }
        return tags;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        String done;
        if (isDone) {
            done = "[X]";
        } else {
            done = "[ ]";
        }
        return done + " " + this.description;
    }

    /**
     * Indicates whether the task occurs on the given date.
     *
     * @param searchDate The date to be checked.
     * @return True if the task occurs on the given date.
     */
    public abstract boolean isOn(LocalDate searchDate);
}
