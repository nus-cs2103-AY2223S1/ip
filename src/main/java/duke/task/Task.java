package duke.task;

import duke.common.DukeException;

import java.time.format.DateTimeParseException;

/**
 * A task for the application
 *
 * @author Pontakorn Prasertsuk
 */
public abstract class Task {

    protected final String title;
    protected boolean status;

    /**
     * Constructs a new Task instance
     *
     * @param title the name of the task
     * @param status whether the task is completed or not
     */
    Task(String title, boolean status) {
        this.title = title;
        this.status = status;
    }

    /**
     * Decode the string from the file
     *
     * @param line the string to be decoded
     * @return the decoded task
     */
    public static Task decode(String input) throws DukeException {
        String[] inputs = input.trim().split("\\s+\\|\\s+");

        try {
            switch (inputs[0]) {
            case Todo.SYMBOL:
                return new Todo(inputs[2], inputs[1].equals("1"));
            case Deadline.SYMBOL:
                return new Deadline(inputs[2], inputs[1].equals("1"), inputs[3]);
            case Event.SYMBOL:
                return new Event(inputs[2], inputs[1].equals("1"), inputs[3]);
            default:
                throw new DukeException("Invalid task format!");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid task format!");
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format!");
        }
    }

    /**
     * Set task status
     *
     * @param status the status to be set
     * @return the task after changing status
     */
    Task setStatus(boolean status) {
        this.status = status;
        return this;
    }

    /**
     * Encode the task for saving into the file
     *
     * @return the string to be saved into the file
     */
    public abstract String encode();

    @Override
    public String toString() {
        return "[" + (this.status ? "X" : " ") + "] " + this.title;
    }
}
