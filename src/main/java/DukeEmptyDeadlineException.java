/**
 * DukeEmptyDeadlineException Class.
 * Exception when Deadline description is empty.
 *
 * CS2103T IP
 * AY22/23 Semester 1
 * @author Tan Jia Rong
 */
public class DukeEmptyDeadlineException extends DukeException {

    public DukeEmptyDeadlineException() {
        super("OOPS!!! The description of a deadline cannot be empty!\n"
                + "Remember to add a /by (time)!!");
    }
}
