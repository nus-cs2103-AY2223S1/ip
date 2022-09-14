package nyanduke.task;

import java.time.LocalDate;

import nyanduke.NyanDukeException;

/**
 * The Task class represents a task added to NyanDuke.
 */
public abstract class Task {
    /** The description of the task. */
    private final String description;
    /** Whether the task has been completed or not. */
    private boolean isDone = false;

    /**
     * Constructs a new Task with a specified description.
     *
     * @param description A string specifying the description of the Task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Parses a string into the task it represents.
     *
     * @param s The string read from the Duke data file.
     * @return The task represented by the string read from the data file.
     * @throws NyanDukeException when the string in the data file is invalid.
     */
    public static Task parseData(String s) throws NyanDukeException {
        assert s != null : "Task::parseData invoked with null argument.";
        try {
            String[] strings = s.split(" \\| ", 3);
            String[] argStrings;
            Task task;

            switch (strings[0]) {
            case "T":
                task = new Todo(strings[2]);
                break;

            case "D":
                argStrings = strings[2].split(" \\| ", 2);
                task = new Deadline(argStrings[0], argStrings[1]);
                break;

            case "E":
                argStrings = strings[2].split(" \\| ", 2);
                task = new Event(argStrings[0], argStrings[1]);
                break;

            default:
                throw new NyanDukeException("Invalid task data loaded.");
            }

            if (strings[1].equals("X")) {
                task.markAsDone();
            } else if (!strings[1].equals(" ")) {
                throw new NyanDukeException("Invalid task data loaded.");
            }

            return task;
        } catch (ArrayIndexOutOfBoundsException | NyanDukeException e) {
            throw new NyanDukeException("Invalid task data loaded.");
        }
    }

    /**
     * Marks a Task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks a Task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns an icon that represents if the task is done or not.
     *
     * @return "X" if the task is done, " " if not.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns the representation of a task when stored in a data file on the hard disk.
     *
     * @return A string representing the task as it is stored on a data file on the hard disk.
     */
    public String toData() {
        return getStatusIcon() + " | " + description;
    }

    /**
     * Checks if the description of a task contains a specified keyword (or phrase).
     *
     * @param keyword The specified keyword (or phrase) to check.
     * @return true if the task description contains the specified keyword (or phrase).
     */
    public boolean contains(String keyword) {
        return description.contains(keyword);
    }

    /**
     * Checks if a Task occurs by/at a specific date.
     *
     * @param date The specified date to check.
     * @return true if the task occurs by/at the specified date.
     */
    public abstract boolean isOnDate(LocalDate date);
}
