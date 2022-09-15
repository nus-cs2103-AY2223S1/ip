package froggy.parser;

import java.util.Objects;

import froggy.exception.FroggyException;
import froggy.task.Task;
import froggy.command.AddCommand;
import froggy.command.Command;
import froggy.command.DeleteCommand;
import froggy.command.ExitCommand;
import froggy.command.FindCommand;
import froggy.command.InvalidCommand;
import froggy.command.ListCommand;
import froggy.command.MarkCommand;
import froggy.command.UpdateCommand;
import froggy.task.Deadline;
import froggy.task.Event;
import froggy.task.ToDo;

/**
 * The parser class handles the parsing of a user input into a Command object that the program can handle.
 */
public class Parser {

    /**
     * Returns a Command object based on the argument fullCommand.
     *
     * @param fullCommand The user input.
     */
    public static Command parse(String fullCommand) throws FroggyException {
        String[] commands = fullCommand.split(" ", 2);

        if (Objects.equals(commands[0], "bye")) {
            return new ExitCommand();
        } else if (Objects.equals(commands[0], "list")) {
            return new ListCommand();
        } else if (Objects.equals(commands[0], "unmark") || Objects.equals(commands[0], "mark")) {
            try {
                return new MarkCommand(commands[0], commands[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new FroggyException("An index must be provided for mark or unmark commands.");
            }
        } else if (Objects.equals(commands[0], "deadline")) {
            try {
                String[] taskDetails = commands[1].split(" /by ");
                return new AddCommand(commands[0], taskDetails[0], taskDetails[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new FroggyException("The description or date time of a deadline cannot be empty.");
            }
        } else if (Objects.equals(commands[0], "event")) {
            try {
                String[] taskDetails = commands[1].split(" /at ");
                return new AddCommand(commands[0], taskDetails[0], taskDetails[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new FroggyException("The description or date time of an event cannot be empty.");
            }
        } else if (Objects.equals(commands[0], "todo")) {
            try {
                return new AddCommand(commands[0], commands[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new FroggyException("The description of a todo cannot be empty.");
            }
        } else if (Objects.equals(commands[0], "find")) {
            try {
                return new FindCommand(commands[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new FroggyException("There needs to be a keyword specified");
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
                throw new FroggyException("There should be details provided on what should be updated");
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
