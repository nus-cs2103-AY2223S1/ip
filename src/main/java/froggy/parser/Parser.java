package seedu.duke.parser;

import java.util.Objects;

import seedu.duke.command.AddCommand;
import seedu.duke.command.Command;
import seedu.duke.command.DeleteCommand;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.FindCommand;
import seedu.duke.command.InvalidCommand;
import seedu.duke.command.ListCommand;
import seedu.duke.command.MarkCommand;
import seedu.duke.command.UpdateCommand;
import seedu.duke.exception.DukeException;
import seedu.duke.task.Deadline;
import seedu.duke.task.Event;
import seedu.duke.task.Task;
import seedu.duke.task.ToDo;

/**
 * The parser class handles the parsing of a user input into a Command object that the program can handle.
 */
public class Parser {

    /**
     * Returns a Command object based on the argument fullCommand.
     *
     * @param fullCommand The user input.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] commands = fullCommand.split(" ", 2);

        if (Objects.equals(commands[0], "bye")) {
            return new ExitCommand();
        } else if (Objects.equals(commands[0], "list")) {
            return new ListCommand();
        } else if (Objects.equals(commands[0], "unmark") || Objects.equals(commands[0], "mark")) {
            try {
                return new MarkCommand(commands[0], commands[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("An index must be provided for mark or unmark commands.");
            }
        } else if (Objects.equals(commands[0], "deadline")) {
            try {
                String[] taskDetails = commands[1].split(" /by ");
                return new AddCommand(commands[0], taskDetails[0], taskDetails[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("The description or date time of a deadline cannot be empty.");
            }
        } else if (Objects.equals(commands[0], "event")) {
            try {
                String[] taskDetails = commands[1].split(" /at ");
                return new AddCommand(commands[0], taskDetails[0], taskDetails[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("The description or date time of an event cannot be empty.");
            }
        } else if (Objects.equals(commands[0], "todo")) {
            try {
                return new AddCommand(commands[0], commands[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
        } else if (Objects.equals(commands[0], "find")) {
            try {
                return new FindCommand(commands[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("There needs to be a keyword specified");
            }
        } else if (Objects.equals(commands[0], "delete")) {
            return new DeleteCommand(Integer.parseInt(commands[1]));
        } else if (Objects.equals(commands[0], "update")) {
            try {
                String[] taskDetails = commands[1].split(" ", 2);
                String[] updateDetails = new String[0];

                if (taskDetails[1].contains("/dateTime")) {
                    updateDetails = taskDetails[1].split("/dateTime ", 2);
                    return new UpdateCommand(Integer.parseInt(taskDetails[0]), "/dateTime", updateDetails[1]);
                } else if (taskDetails[1].contains("/description")) {
                    updateDetails = taskDetails[1].split("/description ", 2);
                    return new UpdateCommand(Integer.parseInt(taskDetails[0]), "/description", updateDetails[1]);
                }
                return new InvalidCommand();
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("There should be details provided on what should be updated");
            }
        }
        return new InvalidCommand();
    }

    /**
     * Returns a Task based on the data input.
     *
     * @param data A line containing either E, D or T.
     * @return A Task object.
     */
    public static Task readLine(String data) {
        String[] output = data.split(" , ", 0);
        boolean isDone = Integer.parseInt(output[1]) == 1;
        if (Objects.equals(output[0], "E")) {
            return new Event(output[2], output[3], isDone);
        } else if (Objects.equals(output[0], "D")) {
            return new Deadline(output[2], output[3], isDone);
        } else if (Objects.equals(output[0], "T")) {
            return new ToDo(output[2], isDone);
        } else {
            return new Task("null");
        }
    }
}
