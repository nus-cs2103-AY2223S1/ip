package violet;

import violet.command.*;
import violet.exception.VioletException;

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

    /**
     * Parses the input from user.
     *
     * @param input The user input.
     * @return A command.
     * @throws VioletException if input is invalid.
     */
    public static Command parse(String input) throws VioletException {
        String[] inputs = input.split(" ");
        String first = inputs[0];
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
        case "priority":
            return new PriorityCommand(input);
        case "help":
            return new HelpCommand();
        default:
            throw new VioletException("I'm sorry... I don't know what '" + input + "' means. Perhaps you might want to "
                    + "use 'help' to see the list of commands that I understand.\n");
        }
    }
}
