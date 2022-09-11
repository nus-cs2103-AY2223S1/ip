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
import duke.command.note.AddNoteCommand;
import duke.command.note.DeleteNoteCommand;
import duke.exception.DukeException;
import duke.task.TaskType;

import java.util.Arrays;

import static java.lang.Integer.parseInt;

/**
 * Interprets user commands.
 */
public class Parser {

    private static boolean isInteger(String str) {
        return str.chars().allMatch(Character::isDigit);
    }

    private static int getDateIndex(String[] splitInput) throws DukeException {
        for (int i = 0; i < splitInput.length; i++) {
            if (splitInput[i].equals("/by") || splitInput[i].equals("/at")) {
                return i;
            }
        }
        throw new DukeException("Task description/date cannot be empty.");
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
    private static String getTaskField(String[] splitInput, int start, int end) throws DukeException {
        String field = String.join(" ", Arrays.copyOfRange(splitInput, start, end));
        if (field.isBlank()) {
            throw new DukeException("Task description/date cannot be empty.");
        }
        return field;
    }

    /**
     * Edits <code>Task</code> and <code>TaskList</code> based on user input.
     *
     * @param cmd command to be executed
     * @param splitInput array of user input words
     * @return <code>command</code> to edit tasks in <code>TaskList</code>
     * @throws DukeException if the index is out of range of the <code>TaskList</code>
     */
    private static Command parseEdit(CommandType cmd, String[] splitInput) throws DukeException {
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
        case DELETENOTE:
            return new DeleteNoteCommand(index);
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
    private static Command parseCreateTask(TaskType taskType, String[] splitInput) throws DukeException {
        int len = splitInput.length;
        String taskDescription = getTaskField(splitInput, 1, len);;
        String date;
        int index;
        switch (taskType) {
        case TODO:
            return new TodoCommand(taskDescription);
        case NOTE:
            return new AddNoteCommand(taskDescription);
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
    private static Command parseFind(String[] splitInput) throws DukeException {
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
    public static Command parse(String userInput) throws DukeException {
        if (userInput.equals("bye")) {
            return new ExitCommand();
        } else if (userInput.equals("list")) {
            return new ListCommand();
        }
        String[] splitInput = userInput.split(" ");
        String cmd = splitInput[0];
        switch (cmd) {
        case "find":
            return parseFind(splitInput);
        case "mark":
            return parseEdit(CommandType.MARK, splitInput);
        case "unmark":
            return parseEdit(CommandType.UNMARK, splitInput);
        case "delete":
            return parseEdit(CommandType.DELETE, splitInput);
        case "deletenote":
            return parseEdit(CommandType.DELETENOTE, splitInput);
        case "todo":
            return parseCreateTask(TaskType.TODO, splitInput);
        case "deadline":
            return parseCreateTask(TaskType.DEADLINE, splitInput);
        case "event":
            return parseCreateTask(TaskType.EVENT, splitInput);
        case "addnote":
            return parseCreateTask(TaskType.NOTE, splitInput);
        default:
            throw new DukeException("Sorry! I don't know what that means!");
        }
    }
}
