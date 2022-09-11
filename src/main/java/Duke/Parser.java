package duke;

/**
 * Parser class deals with making sense of the user command.
 */
public class Parser {

    /**
     * Parses user input and returns the corresponding Command.
     *
     * @param input input from user.
     * @return Command that corresponds to input.
     * @throws InvalidTaskException if input does not correspond to any Command.
     */
    public static Command parse(String input) throws InvalidTaskException {
        if (input.equals("bye")) {
            return new ByeCommand();
        }
        if (input.equals("list")) {
            return new ListCommand();
        }
        if (input.equals("help")) {
            return new HelpCommand();
        }
        if (input.startsWith("mark")) {
            return new MarkCommand(input);
        }
        if (input.startsWith("unmark")) {
            return new UnmarkCommand(input);
        }
        if (input.startsWith("todo")) {
            return new ToDoCommand(input);
        }
        if (input.startsWith("deadline")) {
            return new DeadlineCommand(input);
        }
        if (input.startsWith("event")) {
            return new EventCommand(input);
        }
        if (input.startsWith("delete")) {
            return new DeleteCommand(input);
        }
        if (input.startsWith("find")) {
            return new FindCommand(input);
        }
        //  If loop reaches here, input is invalid, so throw error
        throw new InvalidTaskException("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
