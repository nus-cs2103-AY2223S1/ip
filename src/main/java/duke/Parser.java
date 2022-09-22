package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.SortCommand;
import duke.command.UnmarkCommand;

/**
 * Parser deals with making sense of the user command.
 */
public class Parser {
    private static final String MARK_ERROR = "OOPS!!! This mark command is invalid.";
    private static final String UNMARK_ERROR = "OOPS!!! This unmark command is invalid.";
    private static final String DELETE_ERROR = "OOPS!!! This delete command is invalid.";
    private static final String FIND_ERROR = "OOPS!!! This find command is invalid.";
    private static final String SORT_ERROR = "OOPS!!! This sort command is invalid. (sort [chrono / rchrono])";
    private static final String EMPTY_DESC_ERROR = "OOPS!!! The description of this task cannot be empty.";
    private static final String UNKNOWN_COMMAND = "OOPS!!! I'm sorry, but I don't know what that means :-(";
    private static final String INVALID_COMMAND = "OOPS!!! This command is invalid";
    private static final String EMPTY_COMMAND = "OOPS!!! Please enter a command";
    /**
     * Parses the inputted user command.
     *
     * @param fullCommand The full user input.
     * @return Command corresponding to the input.
     * @throws DukeException If an error is encountered with a command.
     */
    public static Command parse(String fullCommand) throws DukeException {

        String[] split = fullCommand.split(" " , 2);
        String command = split[0];

        if (split.length < 2) {
            switch (command) {
            case "list":
                return new ListCommand();

            case "bye":
                return new ExitCommand();

            case "mark":
                throw new DukeException(MARK_ERROR);

            case "unmark":
                throw new DukeException(UNMARK_ERROR);

            case "delete":
                throw new DukeException(DELETE_ERROR);

            case "find":
                throw new DukeException(FIND_ERROR);

            case "sort":
                throw new DukeException(SORT_ERROR);

            default:
                if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                    throw new DukeException(EMPTY_DESC_ERROR);
                } else if (command.isBlank()) {
                    throw new DukeException(EMPTY_COMMAND);
                }
                throw new DukeException(UNKNOWN_COMMAND);
            }
        } else {
            String details = split[1];
            int index;
            try {
                switch (command) {
                case "mark":
                    index = Integer.parseInt(details) - 1;
                    assert index >= 0 : "index should be greater than or equal to 0";
                    return new MarkCommand(index);

                case "unmark":
                    index = Integer.parseInt(details) - 1;
                    assert index >= 0 : "index should be greater than or equal to 0";
                    return new UnmarkCommand(index);

                case "delete":
                    index = Integer.parseInt(details) - 1;
                    assert index >= 0 : "index should be greater than or equal to 0";
                    return new DeleteCommand(index);

                case "find":
                    assert !details.equals("") && !details.equals(" ") : "keyword should not be empty";
                    return new FindCommand(details);

                case "sort":
                    assert details.equalsIgnoreCase("chrono")
                            || details.equalsIgnoreCase("rChrono")
                            : "Sort specification should be chrono or rChrono";
                    return new SortCommand(details);

                default:
                    return new AddCommand(fullCommand);
                }
            } catch (Exception e) {
                // Any exceptions related to Duke are DukeExceptions
                throw new DukeException(INVALID_COMMAND);
            }
        }

    }

}
