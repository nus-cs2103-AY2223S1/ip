package seedu.duke.parser;

import seedu.duke.command.*;
import seedu.duke.exception.DukeException;
import seedu.duke.task.Deadline;
import seedu.duke.task.Event;
import seedu.duke.task.Task;
import seedu.duke.task.ToDo;


import java.util.Objects;

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
