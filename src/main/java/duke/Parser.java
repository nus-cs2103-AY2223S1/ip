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
        String commandWord;
        String restInput = "";
        if (input.contains(" ")) {
            inputArr = input.split(" ", 2);
            commandWord = inputArr[0];
            restInput = inputArr[1];
        } else {
            commandWord = input;
        }
        boolean hasRestInput = (restInput.length() >= 1);
        Command command;

        switch (commandWord) {
        case "list":
            command = new ListCommand();
            break;
        case "mark":
            if (!hasRestInput) {
                throw new DukeException("Oops, no task given to mark as done.");
            }
            command = new MarkCommand(Integer.parseInt(restInput) - 1, true);
            break;
        case "unmark":
            if (!hasRestInput) {
                throw new DukeException("Oops, no task given to mark as not done.");
            }
            command = new MarkCommand(Integer.parseInt(restInput) - 1, false);
            break;
        case "todo":
            if (!hasRestInput) {
                throw new DukeException("Oops, the description of a todo task cannot be empty.");
            }
            String desc = restInput;
            command = new AddCommand(new Todo(desc));
            break;
        case "deadline":
            if (!hasRestInput) {
                throw new DukeException("Oops, the description of a deadline task cannot be empty.");
            } else if (!restInput.contains("/by")) {
                throw new DukeException("Oops, no deadline given for deadline task.");
            }
            String[] str = restInput.split(" /by ", 2);
            command = new AddCommand(new Deadline(str[0], str[1]));
            break;
        case "event":
            if (!hasRestInput) {
                throw new DukeException("Oops, the description of an event task cannot be empty.");
            } else if (!restInput.contains("/at")) {
                throw new DukeException("Oops, no date given for event task.");
            }
            String[] str1 = restInput.split(" /at ", 2);
            command = new AddCommand(new Event(str1[0], str1[1]));
            break;
        case "delete":
            if (!hasRestInput) {
                throw new DukeException("Oops, no task given to delete.");
            }
            command = new DeleteCommand(Integer.parseInt(restInput) - 1);
            break;
        case "date":
            if (!hasRestInput) {
                throw new DukeException("Oops, no date given.");
            }
            String dateStr = restInput;
            command = new DateCommand(dateStr);
            break;
        case "find":
            if (!hasRestInput) {
                throw new DukeException("Oops, no keyword given.");
            }
            String keyword = restInput;
            command = new SearchCommand(keyword);
            break;
        default:
            throw new DukeException("Oops, I don't know what " + commandWord + " means");
        }

        return command;
    }
}
