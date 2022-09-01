package duke;

import duke.commands.*;

/**
 * Deals with making sense of the user command.
 */
public class Parser {
    /**
     * Parses user's input command.
     *
     * @param command The user's input command.
     * @return The command to be done.
     * @throws DukeException If the input is incorrect.
     */
    public static Command parse(String command) throws DukeException {
        command = command.toLowerCase();

        String[] input = command.split(" ", 2);

        switch (input[0]) {
                case "bye":
                    return new ExitCommand();
                case "list":
                    return new ListCommand();
                case "delete":
                    if (input.length < 2) {
                        throw new DukeException("Please input a task number");
                    }
                    return new DeleteCommand(input[1]);
                case "todo":
                    if (input.length < 2) {
                        throw new DukeException("Please input a description");
                    }
                    return new ToDoCommand(input[1]);
                case "event":
                    if (input.length < 2) {
                        throw new DukeException("Please input a description");
                    }
                    return new EventCommand(input[1]);
                case "deadline":
                    if (input.length < 2) {
                        throw new DukeException("Please input a description");
                    }
                    return new DeadlineCommand(input[1]);
                case "mark":
                    if (input.length < 2) {
                        throw new DukeException("Please input a task number");
                    }
                    return new MarkCommand(input[1]);
                case "unmark":
                    if (input.length < 2) {
                        throw new DukeException("Please input a task number");
                    }
                    return new UnmarkCommand(input[1]);
                case "on":
                    if (input.length < 2) {
                        throw new DukeException("Please input a date!");
                    }
                    return new OnCommand(input[1]);
                case "find":
                    if (input.length < 2) {
                        return new FindCommand("");
                    }
                    return new FindCommand(input[1].trim());
                default:
                        throw new DukeException("What's " + command + " ??\n"
                                + "Please say something I can understand!");
            }

    }
}
