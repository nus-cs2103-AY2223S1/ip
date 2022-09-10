package amanda.manager;

import java.util.StringTokenizer;

import amanda.command.*;
import amanda.exception.*;
import amanda.task.Tag;


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
            EmptyDateException, InvalidDescriptionException, InvalidIndexException, InvalidTagException {

        TaskMaker taskMaker = new TaskMaker();
        String type = getType(input);

        switch (type) {
        case "task":
            return new AddCommand(taskMaker.make(input));
        case "list":
            return new ListCommand();
        case "tag" :
            return new TagCommand(TaskList.getList().get(getIndex(input) - 1), getTag(input), getIndex(input));
        case "listtag" :
            return new ListTagCommand(TaskList.getList().get(getIndex(input) - 1), getIndex(input));
        case "find":
            return new FindCommand(input);
        case "mark":
            return new MarkCommand(TaskList.getList().get(getIndex(input) - 1), getIndex(input));
        case "unmark":
            return new UnMarkCommand(TaskList.getList().get(getIndex(input) - 1), getIndex(input));
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
    public static String getType(String input) throws InvalidDescriptionException, InvalidCommandException {
        if (input.isEmpty()) {
            throw new InvalidDescriptionException();
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
        assert !input.isEmpty() : "Input is empty.";
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

    public static Tag getTag(String input) throws InvalidIndexException, InvalidTagException {
        assert !input.isEmpty() : "Input is empty.";
        StringTokenizer tokens = new StringTokenizer(input, " ");
        tokens.nextToken();
        if (!tokens.hasMoreTokens()) {
            throw new InvalidIndexException();
        }
        tokens.nextToken();
        if (!tokens.hasMoreTokens()) {
            throw new InvalidTagException();
        }
        String tag = tokens.nextToken();
        if (tokens.hasMoreTokens()) {
            throw new InvalidTagException();
        } else {
            return new Tag(tag);
        }
    }
}
