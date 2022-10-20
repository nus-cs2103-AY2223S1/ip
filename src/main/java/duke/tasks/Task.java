package duke.tasks;

import java.time.LocalDate;
import java.util.ArrayList;

public class Task {
    protected ArrayList<String> tags;
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status of the task.
     *
     * @return The status of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Loads the task.
     * @param data the data to be loaded
     * @return Tasks that have been loaded
     */
    public static Task loadTask(String data) {
        String[] input = data.split( " \\| ", 4);
        char c = input[0].charAt(0);
        boolean isDone = input[1].equals("1");
        String description = input[2];
        LocalDate time = input.length == 3 ? LocalDate.parse(input[2]) : null;

        if (c == 'D') {
            return new Deadline(description, isDone, time);
        } else if (c == 'E') {
            return new Event(description, isDone, time);
        } else {
            return new Todo(description, isDone);
        }
    }

    public void tagWith(String input) {
        tags.add(input);
    }

    public String listTheTags() {
        int count = 1;
        String list = "Here are the tags of this task:" + "\n";
        for (String tag : tags) {
            String newLine = count + ". " + "#" + tag + "\n";
            list += newLine;
            count += 1;
        }
        return list;
    }

    /**
     * Saves the task
     * @return The string message of the saved task
     */
    public String saveTask() {
        return String.format("%d | %s", isDone ? 1 : 0, description);
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}