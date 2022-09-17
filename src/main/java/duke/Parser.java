package duke;

import duke.command.DateCommand;
import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.SearchCommand;
import duke.command.Command;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * A parser which handles parsing of the user input.
 */
public class Parser {
    /**
     * Constructor method for a Parser.
     */
    public Parser() {
    }

    /**
     * Parses the user input.
     *
     * @param input input from the user
     * @param tasks list of tasks of the user
     * @throws DukeException if there is an error with the input
     */
    public Command parseUserInput(String input, TaskList tasks) throws DukeException {
        String[] inputArr;
        String commandWord = input;
        String restInput = "";
        if (input.contains(" ")) {
            inputArr = input.split(" ", 2);
            commandWord = inputArr[0];
            restInput = inputArr[1];
        }
        Command command;

        switch (commandWord) {
        case "list":
            command = new ListCommand();
            break;
        case "mark":
            command = new MarkCommand(restInput, true);
            break;
        case "unmark":
            if (!hasRestInput) {
                throw new DukeException("Oops, no task given to mark as not done.");
            }
            command = new MarkCommand(Integer.parseInt(restInput) - 1, false);
            break;
        case "todo":
            command = new AddCommand(new Todo(restInput));
            break;
        case "deadline":
            if (!restInput.contains("/by")) {
                throw new DukeException("Oops, no deadline given for deadline task.");
            }
            String[] str = restInput.split(" /by ", 2);
            command = new AddCommand(new Deadline(str[0], str[1]));
            break;
        case "event":
            if (!restInput.contains("/at")) {
                throw new DukeException("Oops, no date given for event task.");
            }
            String[] str1 = restInput.split(" /at ", 2);
            command = new AddCommand(new Event(str1[0], str1[1]));
            break;
        case "delete":
            command = new DeleteCommand(restInput);
            break;
        case "date":
            command = new DateCommand(restInput);
            break;
        case "find":
            command = new SearchCommand(restInput);
            break;
        default:
            throw new DukeException("Oops, I don't know what " + commandWord + " means");
        }

        return command;
    }
}
