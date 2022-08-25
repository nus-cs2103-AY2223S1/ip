package duke;

import duke.command.*;

/**
 * Parser deals with making sense of the user command.
 */
public class Parser {

    /**
     * Parse the inputted user command.
     *
     * @param fullCommand The full user input.
     * @return Command corresponding to the input.
     * @throws DukeException If an error is encountered with a command.
     */
    public static Command parse(String fullCommand) throws DukeException {

        String[] split = fullCommand.split(" ");
        String command = split[0];

        if (split.length < 2) {
            switch (command) {
            case "list":
                return new ListCommand();

            case "bye":
                return new ExitCommand();

            case "mark":
                throw new DukeException("OOPS!!! This mark command is invalid.");

            case "unmark":
                throw new DukeException("OOPS!!! This unmark command is invalid.");

            case "delete":
                throw new DukeException("OOPS!!! This delete command is invalid.");

            default:
                if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                    throw new DukeException("OOPS!!! The description of this task cannot be empty.");
                }
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } else {
            String details = split[1];

            try {
                switch (command) {
                case "mark":
                    return new MarkCommand(Integer.parseInt(details) - 1);

                case "unmark":
                    return new UnmarkCommand(Integer.parseInt(details) - 1);

                case "delete":
                    return new DeleteCommand(Integer.parseInt(details) - 1);

                default:
                    return new AddCommand(fullCommand);
                }
            } catch (Exception e) {
                throw new DukeException("OOPS!!! This command is invalid");
            }
        }

    }

}
