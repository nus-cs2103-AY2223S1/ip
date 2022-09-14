package duke.tasks;

import java.util.ArrayList;

/**
 * Used to represent any type of tasks, to do, deadline, or events and their relevant information
 */
public abstract class Task {
    protected boolean isDone;
    protected String description;
    protected ArrayList<String> tags;

    /**
     * Constructor of Task
     *
     * @param description description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tags = new ArrayList<String>();
    }

    /**
     * Marks a task as done
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks a task; marks a task as undone
     */
    public void unmark() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Get corresponding letter tag for each type of task
     *
     * @return "T" if to do, "E" if event, "D" if deadline
     */
    public abstract String getLetterTag();

    /**
     * Get deadline from deadlines and event timings from events
     *
     * @return string representing deadline for deadlines, event timings for events, "" for to do
     */
    public abstract String getAdditionalInfo();

    /**
     * Adds tag to task
     *
     * @param tag tag to be added
     */
    public void addTag(String tag) {
        if (tag.startsWith("#")) {
            this.tags.add(tag);
        } else {
            this.tags.add("#" + tag);
        }
    }

    /**
     * Formats the tag for the task
     *
     * @return Properly formatted string of tags for task
     */
    public String getTagString() {
            StringBuilder tagString = new StringBuilder();
            for (String tag : tags) {
                tagString.append(tag).append(" ");
            }
            return tagString.toString();
    }

}
