package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Parser to parse user inputs
 *
 * @author Aaron Pang
 * @version CS2103T AY22/23 Sem 1
 */
public class Parser {

    /**
     * Parses the input with the tasks, ui and storage
     * @param in Input of the user
     * @param tasks TaskList of which input is to be stored
     * @param ui Ui needed for the input
     * @param storage Storage needed for the input
     * @return True if program should continue, false otherwise
     * @throws DukeException If the user input is invalid
     */
    public static boolean parse(String in, TaskList tasks, Ui ui, Storage storage)
        throws DukeException {
        if (!withinScope(in)) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-( ");
        } else if (in.equals("list")) {
            tasks.list();
            return false;
        } else if (in.startsWith("delete")) {
            tasks.delete(in);
            return false;
        } else if (in.startsWith("find")) {
            tasks.find(in);
            return false;
        } else if (in.startsWith("unmark")) {
            tasks.unmarkTask(in);
            return false;
        } else if (in.startsWith("mark")) {
            tasks.markTask(in);
            return false;
        } else if (in.startsWith("deadline")) {
            tasks.deadline(in);
            return false;
        } else if (in.startsWith("event")) {
            tasks.event(in);
            return false;
        } else if (in.startsWith("todo")) {
            tasks.todo(in);
            return false;
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry but please check spelling :-(");
        }
    }


    /**
     * Returns true if input is within scope of the program.
     *
     * @param in Input of the user.
     * @return True if input is within scope of the program, false otherwise.
     */
    public static boolean withinScope(String in) {
        return (in.startsWith("list") || in.startsWith("mark") || in.startsWith("unmark") || in.startsWith("deadline")
                || in.startsWith("event") || in.startsWith("todo") || in.startsWith("delete") || in.startsWith("find"));
    }


}
