package duke;

public class DukeException extends Exception{

    /**
     * Error message to be shown to user.
     */
    private String message;

    public DukeException(String message) {
        this.message = message;
    }

    /**
     * Returns the error message.
     * @return Error message.
     */
    @Override
    public String toString() {
        return this.message;
    }

    /**
     * Indicates details of task being empty.
     * @return Empty task error message.
     */
    public static DukeException EmptyTaskException() {
        return new DukeException("Please input the details of your task! It cannot be empty.");
    }

    /**
     * Indicates empty index input for unmark command.
     * @return Empty index input error message.
     */
    public static DukeException UnmarkIndexEmptyException() {
        return new DukeException("Please input the index to unmark!");
    }

    /**
     * Indicates empty index input for mark command.
     * @return Empty index input error message.
     */
    public static DukeException MarkIndexEmptyException() {
        return new DukeException("Please input the index to mark as completed!");
    }


}
