package duke.task;

import java.time.LocalDate;

/**
 * Abstract class for a Task.
 *
 * @author kaij77
 * @version 0.1
 */
public abstract class Task {
    private String description;
    private String note;
    private boolean isDone;

    /**
     * Public constructor for a Task.
     *
     * @param description The description of the Task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Public constructor for a Task.
     *
     * @param description The description of the Task
     * @param note The optional note about the Task
     */
    public Task(String description, String note) {
        this.description = description;
        this.note = note;
        this.isDone = false;
    }

    /**
     * Parses the String to create a type of Task according to the input.
     *
     * @param str The input String
     * @return The corresponding Task
     */
    public static Task parse(String str) {
        String[] entry = str.split(" \\| ");
        Task task = null;
        if (entry[0].equals("T")) {
            task = new ToDo(entry[2]);
        } else if (entry[0].equals("D")) {
            task = new Deadline(entry[2], LocalDate.parse(entry[3]));
        } else {
            task = new Event(entry[2], LocalDate.parse(entry[3]));
        }
        if (entry[1].equals("1")) {
            task.mark();
        }
        return task;
    }

    public String stringifyTask() {
        return String.format("%d | %s", this.isDone ? 1 : 0, this.description);
    }

    /**
     * Returns "X" is the task is done, and returns a " " otherwise.
     *
     * @return String representation of whether the Task is done
     */
    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    /**
     * Marks the Task as done.
     *
     * @return The String representation of the marked Task
     */
    public String mark() {
        this.isDone = true;
        return this.toString();
    }

    /**
     * Unmarks the task.
     *
     * @return The String representation of the unmarked Task
     */
    public String unmark() {
        this.isDone = false;
        return this.toString();
    }

    /**
     * Returns true if the Task matches the keyword, and returns false otherwise.
     *
     * @param keyword The keyword being searched for
     * @return boolean depending on whether the Task matches the keyword
     */
    public boolean match(String keyword) {
        String[] keywords = keyword.split(" ");
        boolean isMatch = true;
        for (String currKeyword : keywords) {
            if (!this.description.contains(currKeyword)) {
                isMatch = false;
            }
        }
        return isMatch;
    }

    /**
     * Adds the given note to the Task.
     *
     * @param note The note to be added
     */
    public void editNote(String note) {
        this.note = note;
    }


    public void deleteNote() {
        this.note = null;
    }

    /**
     * Returns the note of the Task.
     *
     * @return note
     */
    public String getNote() {
        return this.note;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the String representation of the Task.
     *
     * @return the String representation of the Task
     */
    @Override
    public String toString() {
        // Solution below adapted from https://github.com/dexter-sim/ip
        if (this.note == null) {
            return String.format("[%s] %s", this.getStatusIcon(), this.description);
        } else {
            assert this.note != null : "This task should have a note";
            return String.format("[%s] %s", this.getStatusIcon(), this.description) + "\n"
                    + String.format("       - Notes: %s", this.note);
        }
    }
}
