package duke;

import duke.command.Command;
import duke.command.CommandType;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.TodoCommand;
import duke.command.UnmarkCommand;
import duke.task.TaskList;
import duke.task.TaskType;

import java.util.Arrays;

import static java.lang.Integer.parseInt;

/**
 * Interprets user commands.
 */
public class Parser {

    private boolean isInteger(String str) {
        return str.chars().allMatch(Character::isDigit);
    }

    private int getDateIndex(String[] splitInput) throws DukeException {
        for (int i = 0; i < splitInput.length; i++) {
            if (splitInput[i].equals("/by") || splitInput[i].equals("/at")) {
                return i;
            }
        }
        throw new DukeException("Duke: OOPS!!! The task is missing a date property.");
    }

    /**
     * Returns a task field.
     *
     * @param splitInput array of user input words
     * @param start leftmost index of task field
     * @param end rightmost index of task field
     * @return task field
     * @throws DukeException if task description or date field is empty
     */
    private String getTaskField(String[] splitInput, int start, int end) throws DukeException {
        String field = String.join(" ", Arrays.copyOfRange(splitInput, start, end));
        if (field.equals("")) {
            throw new DukeException("Duke: OOPS!!! The task description/date cannot be empty.");
        }
        return field;
    }

    /**
     * Edit <code>Task</code> and <code>TaskList</code> based on user input.
     *
     * @param cmd command to be executed
     * @param splitInput array of user input words
     * @return <code>command</code> to edit tasks in <code>TaskList</code>
     * @throws DukeException if the index is out of range of the <code>TaskList</code>
     */
    private Command parseEdit(CommandType cmd, String[] splitInput) throws DukeException {
        int len = splitInput.length;
        if (len != 2 || !isInteger(splitInput[1])) {
            throw new DukeException("Please indicate the index of the task using an integer!");
        }
        int index = parseInt(splitInput[1]) - 1;
        switch (cmd) {
        case MARK:
            return new MarkCommand(index);
        case UNMARK:
            return new UnmarkCommand(index);
        case DELETE:
            return new DeleteCommand(index);
        default:
            throw new DukeException("Unable to edit task");
        }
    }

    /**
     * Creates a new task from user input and adds it to the <code>TaskList</code>.
     *
     * @param splitInput array of user input words
     * @return <code>command</code> to create <code>Task</code>
     * @throws DukeException if user input is invalid
     */
    private Command parseCreateTask(TaskType taskType, String[] splitInput) throws DukeException {
        int len = splitInput.length;
        String taskDescription;
        String date;
        int index;
        switch (taskType) {
        case TODO:
            taskDescription = getTaskField(splitInput, 1, len);
            return new TodoCommand(taskDescription);
        case DEADLINE:
            index = getDateIndex(splitInput);
            taskDescription = getTaskField(splitInput, 1, index);
            date = getTaskField(splitInput, index + 1, len);
            return new DeadlineCommand(taskDescription, date);
        case EVENT:
            index = getDateIndex(splitInput);
            taskDescription = getTaskField(splitInput, 1, index);
            date = getTaskField(splitInput, index + 1, len);
            return new EventCommand(taskDescription, date);
        default:
            throw new DukeException("Unable to create task!");
        }
    }

    /**
     * Searches for tasks in the <code>TaskList</code> with the input keyword.
     *
     * @param splitInput array of user input words
     * @return <code>command</code> to find task
     * @throws DukeException if user input does not have a keyword
     */
    private Command parseFind(String[] splitInput) throws DukeException {
        int len = splitInput.length;
        if (len == 1) {
            throw new DukeException("Enter a keyword to search for tasks");
        }
        String keyword = String.join(" ", Arrays.copyOfRange(splitInput, 1, len));
        return new FindCommand(keyword);
    }

    /**
     * Interprets and executes the input command from the user.
     *
     * @param userInput input from user
     * @return <code>command</code> to be executed
     * @throws DukeException if command does not follow the format
     */
    public Command parse(String userInput) throws DukeException {
        String[] splitInput = userInput.split(" ");
        String cmd = splitInput[0];
        if (userInput.equals("bye")) {
            return new ExitCommand();
        } else if (userInput.equals("list")) {
            return new ListCommand();
        } else if (cmd.equals("find")) {
            return parseFind(splitInput);
        } else if (cmd.equals("mark")) {
            return parseEdit(CommandType.MARK, splitInput);
        } else if (cmd.equals("unmark")) {
            return parseEdit(CommandType.UNMARK, splitInput);
        } else if (cmd.equals("delete")) {
            return parseEdit(CommandType.DELETE, splitInput);
        } else if (cmd.equals("todo")) {
            return parseCreateTask(TaskType.TODO, splitInput);
        } else if (cmd.equals("deadline")) {
            return parseCreateTask(TaskType.DEADLINE, splitInput);
        } else if (cmd.equals("event")) {
            return parseCreateTask(TaskType.EVENT, splitInput);
        } else {
            throw new DukeException("Sorry! I don't know what that means!");
        }
    }
}
