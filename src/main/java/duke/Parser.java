package duke;

import duke.command.*;
import duke.exception.DukeException;

/**
 * The Parser class deals with making sense of the user command.
 */
public class Parser {
    /** A user interface to interact with the user */
    private Ui ui;
    /** The command input by the user */
    private String command;
    private static final String LINE = "____________________________________________________________\n";

    /**
     * Instantiates the Parser object to deal with user input.
     *
     * @param command The command input by the user.
     * @param ui
     */
    public Parser(String command, Ui ui) {
        this.command = command;
        this.ui = ui;
    }

    public static Command parse(String input) throws DukeException {
        String[] inputArray = input.split(" ");
        String first = inputArray[0];
        switch (first) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "unmark":
            return new UnmarkCommand(input);
        case "mark":
            return new MarkCommand(input);
        case "todo":
            return new TodoCommand(input);
        case "deadline":
            return new DeadlineCommand(input);
        case "event":
            return new EventCommand(input);
        case "delete":
            return new DeleteCommand(input);
        case "find":
            return new FindCommand(input);
        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :(\n");
        }
    }
}
