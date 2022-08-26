package duke.task;

import java.time.LocalDate;

import duke.DukeException;

/**
 * The Task class represents a task added to duke.Duke.
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
     * @throws DukeException when the string in the data file is invalid.
     */
    public static Task parseData(String s) throws DukeException {
        try {
            String[] strings = s.split(" \\| ");
            Task task;

            switch (strings[0]) {
            case "T":
                if (strings.length > 3) {
                    throw new DukeException("Invalid task data loaded.");
                }
                task = new Todo(strings[2]);
                break;
            case "D":
                if (strings.length > 4) {
                    throw new DukeException("Invalid task data loaded.");
                }
                task = new Deadline(strings[2], strings[3]);
                break;
            case "E":
                if (strings.length > 4) {
                    throw new DukeException("Invalid task data loaded.");
                }
                task = new Event(strings[2], strings[3]);
                break;
            default:
                throw new DukeException("Invalid task data loaded.");
            }

            if (strings[1].equals("X")) {
                task.markAsDone();
            } else if (!strings[1].equals(" ")) {
                throw new DukeException("Invalid task data loaded.");
            }

            return task;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Invalid task data loaded.");
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
    public void unmarkAsDone() {
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
     * @return a string representing the task as it is stored on a data file on the hard disk.
     */
    public String toData() {
        return getStatusIcon() + " | " + description;
    }

    /**
     * Checks if a Task occurs by/at a specific date.
     *
     * @param date The specified date to check.
     * @return true if the task occurs by/at the specified date.
     */
    public abstract boolean onDate(LocalDate date);
}
