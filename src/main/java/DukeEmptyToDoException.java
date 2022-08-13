/**
 * DukeEmptyToDoException Class
 * Exception when ToDo description is empty
 * CS2103T IP
 * AY22/23 Semester 1
 *
 * @author Tan Jia Rong
 */
public class DukeEmptyToDoException extends DukeException {

    public DukeEmptyToDoException() {
        super("OOPS!!! The description of a todo cannot be empty.");
    }
}
