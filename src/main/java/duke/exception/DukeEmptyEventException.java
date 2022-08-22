package duke.exception;

/**
 * DukeEmptyEventException Class.
 * Exception when Event description is empty.
 *
 * CS2103T IP
 * AY22/23 Semester 1
 * @author Tan Jia Rong
 */
public class DukeEmptyEventException extends DukeException {
    /**
     * Constructor for DukeEmptyEventException.
     */
    public DukeEmptyEventException() {
        super("OOPS!!! The description of a event cannot be empty!\n"
                + "Remember to add a /at (time)!!");
    }
}
