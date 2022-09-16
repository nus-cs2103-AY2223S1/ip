package duke;

import duke.command.ArchiveCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.TodoCommand;
import duke.command.UnknownCommand;
import duke.command.UnmarkCommand;

/**
 * Parser parses the string input
 */
public class Parser {

    /**
     * Checks if the command line input is a proper argument.
     *
     * @param arr String array that contains the seperate parts of the command line argument.
     * @return True if the command line input is of the correct form.
     */
    private static boolean inputChecker(String[] arr) {
        if (arr.length  < 2) {
            return false;
        }
        if (arr[1].isBlank()) {
            return false;
        }
        return true;
    }

    /**
     * Converts the command line input to call the command.
     *
     * @param input The commmand line input.
     * @return Command with respect ot the command line input.
     */
    public static Command parse (String input) throws DukeException{
        String[] arr = input.split(" ", 2);

        if ((input).equalsIgnoreCase("bye")) {
            return new ExitCommand();
        }

        else if (input.equalsIgnoreCase("list")) {
            return new ListCommand();
        }

        else if (arr[0].equalsIgnoreCase("mark")){
            if (!inputChecker(arr)) {
                throw DukeException.MarkIndexEmptyException();
            } else {
                return new MarkCommand(arr);
            }

        }
        else if (arr[0].equalsIgnoreCase("unmark")) {
            if (!inputChecker(arr)) {
                throw DukeException.UnmarkIndexEmptyException();
            } else {
                return new UnmarkCommand(arr);
            }
        }

        else if (arr[0].equals("todo")) {
            if (!inputChecker(arr)) {
                throw DukeException.EmptyTaskException();
            } else {
                return new TodoCommand(arr[1]);
            }

        }

        else if (arr[0].equalsIgnoreCase("deadline")) {
            if (!inputChecker(arr)) {
                throw DukeException.EmptyTaskException();
            } else {
                return new DeadlineCommand(arr[1]);
            }

        }
        else if (arr[0].equalsIgnoreCase("event")) {
            if (!inputChecker(arr)) {
                throw DukeException.EmptyTaskException();
            } else {
                return new EventCommand(arr[1]);
            }
        }
        else if (arr[0].equalsIgnoreCase("delete")) {
            if (!inputChecker(arr)) {
                throw new DukeException("Index not found in the list!");
            } else {
                return new DeleteCommand(arr[1]);
            }

        }
        else if (arr[0].equalsIgnoreCase("find")) {
            if (!inputChecker(arr)) {
                throw new DukeException("Input the word that you want to find/match!");
            } else {
                return new FindCommand(arr[1]);
            }
        } else if (arr[0].equalsIgnoreCase("archive")) {
            if (!inputChecker(arr)) {
                throw new DukeException("Input the specific index of the task that you want to archive," +
                        " or all if want to archive all tasks!");
            } else {
                return new ArchiveCommand(arr[1]);
            }
        }
        else {
            return new UnknownCommand();
        }
    }
}
