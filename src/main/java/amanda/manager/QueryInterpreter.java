package amanda.manager;

import java.util.StringTokenizer;

import amanda.command.*;
import amanda.exception.EmptyDateException;
import amanda.exception.InvalidCommandException;
import amanda.exception.InvalidDateFormatException;
import amanda.exception.InvalidDescriptionException;
import amanda.exception.InvalidIndexException;
import amanda.task.Task;


/**
 * QueryInterpreter is interprets/parse the user input.
 */
public class QueryInterpreter {
    /**
     * Interprets the user input.
     * @param input one line of the user input command.
     * @return command object that can execute what is interpreted from the user input.
     *      and cannot be interpreted by the method.
     */
    public static Command interpret(String input) throws InvalidCommandException, InvalidDateFormatException,
            EmptyDateException, InvalidDescriptionException, InvalidIndexException {

        TaskMaker taskMaker = new TaskMaker();
        String type = getType(input);

        switch (type) {
        case "task":
            return new AddCommand(taskMaker.make(input));
        case "list":
            return new ListCommand();
        case "find":
            return new FindCommand(input);
        case "mark":
            return new MarkCommand(TaskList.getList().get(getIndex(input) - 1), getIndex(input));
        case "unmark":
            return new UnmarkCommand(TaskList.getList().get(getIndex(input) - 1), getIndex(input));
        case "delete":
            return new DeleteCommand(TaskList.getList().get(getIndex(input) - 1), getIndex(input));
        default:
            throw new InvalidCommandException();
        }
    }

    /**
     * Get the type of command that the user input.
     * @param input one line of the user input.
     * @return the type of command that the user input.
     */
    public static String getType(String input) {
        if (input.equals("")) {
            return "";
        }
        StringTokenizer tokens = new StringTokenizer(input, " ");
        String type = tokens.nextToken(); // type is the first word entered by the user
        if (type.equals("todo") | type.equals("deadline") | type.equals("event")) {
            return "task";
        } else {
            return type;
        }
    }

    /**
     * Get the index of the task in the current task list that the user wants to operate on.
     * @param input one line of the user input.
     * @return the index of the task that the user wants to operate on.
     */
    public static int getIndex(String input) throws InvalidIndexException {
        StringTokenizer tokens = new StringTokenizer(input, " ");
        tokens.nextToken(); // skips the first word of the user input
        if (!tokens.hasMoreTokens()) {
            throw new InvalidIndexException();
        }
        try {
            return Integer.parseInt(tokens.nextToken());
        } catch (NumberFormatException e) {
            throw new InvalidIndexException();
        }
    }
}
