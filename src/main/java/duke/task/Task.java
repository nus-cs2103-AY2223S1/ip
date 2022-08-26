package duke.task;

import java.time.LocalDate;

import duke.common.DukeException;

/**
 * A task specified by the user to Duke.
 *
 * @author Rama Aryasuta Pangestu
 */
public abstract class Task {
    protected static final String ENCODING_SEPARATOR = " | ";
    protected static final String DECODING_SEPARATOR = " \\| ";

    private final String taskType;
    private final String description;
    private boolean isDone;

    /**
     * Constructs a task specified by the user. Note that <code>Task</code>
     * is an abstract class, therefore this constructor is only used by
     * its subclasses.
     *
     * @param taskType    the type of the task, specified by its subclass
     * @param description the description of the task
     * @param isDone      denotes whether the task is marked done or not
     * @throws DukeException if the description of the task is empty
     */
    public Task(String taskType, String description, boolean isDone) throws DukeException {
        this.taskType = taskType;
        this.description = description;
        this.isDone = isDone;
        if (this.description.isBlank()) {
            throw new DukeException(
                    "OOPS!!! The description of a " + this.taskType + " cannot be empty.");
        }
    }

    /**
     * Returns a task represented by a string encoding.
     *
     * @param input the string encoding
     * @return a task represented by the string encoding
     * @throws DukeException if the string encoding is invalid
     */
    public static Task decode(String input) throws DukeException {
        String[] args = input.trim().split(Task.DECODING_SEPARATOR);
        Task task;
        try {
            switch (args[0]) {
            case "T":
                task = new ToDo(args[2], args[1].equals("X"));
                break;
            case "E":
                task = new Event(args[2], args[1].equals("X"), LocalDate.parse(args[3]));
                break;
            case "D":
                task = new Deadline(args[2], args[1].equals("X"), LocalDate.parse(args[3]));
                break;
            default:
                throw new DukeException("OOPS!!! Invalid encoded format :(");
            }
        } catch (IndexOutOfBoundsException exception) {
            throw new DukeException("OOPS!!! Invalid encoded format :(");
        } catch (java.time.format.DateTimeParseException exception) {
            throw new DukeException(
                    "OOPS!!! Can't recognize the date :(. Please input the date in yyyy-mm-dd format.");
        }
        return task;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the status icon of the task, determined by whether the task is marked done or not.
     *
     * @return a character 'X' if the task is marked done, and a white space otherwise.
     */
    public char getStatusIcon() {
        return (isDone ? 'X' : ' '); // mark done task with X
    }

    /**
     * Returns the task type icon of the task, determined by its subclass.
     *
     * @return the task type icon of the task specified by its subclass
     */
    public char getTaskTypeIcon() {
        return taskType.toUpperCase().charAt(0);
    }

    /**
     * Marks the task as either done or not done.
     *
     * @param isDone true if and only if the task is marked as done
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return the string representation of the task
     */
    @Override
    public String toString() {
        return "[" + this.getTaskTypeIcon() + "][" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns a string encoding the task for saving the task in the save file.
     *
     * @return the string encoding
     */
    public String encode() {
        return this.getTaskTypeIcon() + Task.ENCODING_SEPARATOR
                + this.getStatusIcon() + Task.ENCODING_SEPARATOR + this.description;
    }
}
