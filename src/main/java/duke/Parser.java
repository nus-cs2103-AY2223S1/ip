package duke;


/**
 * Parser to parse user inputs
 *
 * @author Gavin Cho
 * @version CS2103T AY22/23 Sem 1
 */
public class Parser {

    /**
     * Parses the input with the tasks, ui and storage
     * @param input Input of the user
     * @param tasks TaskList of which input is to be stored
     * @param ui Ui needed for the input
     * @param storage Storage needed for the input
     * @return True if program should continue, false otherwise
     * @throws DukeException If the user input is invalid
     */
    public static String parse(String input, TaskList tasks, Ui ui, Storage storage)
            throws DukeException {
        if (!isValidCommand(input)) {
            throw new DukeException("Sorry! I don't Understand what you are saying.");
        } else {
            if (input.equals("list")) {
                return tasks.list();
            } else if (input.startsWith("delete")) {
                return tasks.delete(input);
            } else if (input.startsWith("find")) {
                return tasks.find(input);
            } else if (input.startsWith("unmark")) {
                return tasks.unmarkTask(input);
            } else if (input.startsWith("mark")) {
                return tasks.markTask(input);
            } else if (input.startsWith("deadline")) {
                return tasks.deadline(input);
            } else if (input.startsWith("event")) {
                return tasks.event(input);
            } else if (input.startsWith("todo")) {
                return tasks.todo(input);
            } else if (input.startsWith("tag")) {
                return tasks.tag(input);
            } else if (input.startsWith("listtag")) {
                return tasks.listTags(input);
            } else {
                throw new UnknownCommandException();
            }
        }
    }


    /**
     * Returns true if input is within scope of the Keywords.
     *
     * @param input Input of the user.
     * @return True if input is within scope of the program, false otherwise.
     */
    public static boolean isValidCommand(String input) {
        return (input.startsWith("list") || input.startsWith("mark") || input.startsWith("unmark") || input.startsWith("deadline")
                || input.startsWith("event") || input.startsWith("todo") || input.startsWith("delete") || input.startsWith("find")
                || input.startsWith("tag") || input.startsWith("listtags"));
    }
}
