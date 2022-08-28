package duke;

/**
 * Handles the parsing of user input.
 */
public class Parser {
    /**
     * Enumerates the different commands that can be parsed.
     */
    public enum CommandType {
        BYE,
        MARK,
        UNMARK,
        DELETE,
        LIST,
        TODO,
        DEADLINE,
        EVENT
    }

    /**
     * Parses a user input and returns the corresponding command.
     * @param fullCommand the user input
     * @return the corresponding command
     * @throws duke.DukeException if the input is invalid
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] split = fullCommand.split(" ", 2);
        CommandType command;
        try {
            command = CommandType.valueOf(split[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        String description = split.length == 2 ? split[1] : "";
        switch (command) {
        case BYE:
            return new ExitCommand();
        case LIST:
            return new ListCommand();
        case MARK:
            return new DoneCommand(Integer.parseInt(description));
        case UNMARK:
            return new UndoneCommand(Integer.parseInt(description));
        case DELETE:
            return new DeleteCommand(Integer.parseInt(description));
        case TODO:
            return new AddCommand(new Todo(description));
        case DEADLINE:
            String[] deadline = description.split(" /by ", 2);
            return new AddCommand(new Deadline(deadline[0], deadline[1]));
        case EVENT:
            String[] event = description.split(" /at ", 2);
            return new AddCommand(new Event(event[0], event[1]));
        /* case FIND:
            return new FindCommand(description);

         */
        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
