package main.java.amanda.manager;

import java.util.StringTokenizer;

import main.java.amanda.command.AddCommand;
import main.java.amanda.command.Command;
import main.java.amanda.command.DeleteCommand;
import main.java.amanda.command.ExitCommand;
import main.java.amanda.command.ListCommand;
import main.java.amanda.command.MarkCommand;
import main.java.amanda.command.UnmarkCommand;
import main.java.amanda.exception.AmandaException;

/**
 * QueryInterpreter is interprets/parse the user input.
 */
public class QueryInterpreter {

    /**
     * Constructor for QueryInterpreter class.
     */
    public QueryInterpreter() {
    }

    /**
     * Interprets the user input.
     * @param input one line of the user input command.
     * @return command object that can execute what is interpreted from the user input.
     * @throws AmandaException throw an exception when the user input is not in the right format
     *      and cannot be interpreted by the method.
     */
    public static Command interpret(String input) throws AmandaException {

        TaskMaker generator = new TaskMaker();
        String type = getType(input);

        switch (type) {
        case "not a command":
            throw new AmandaException("\nI have no idea what you just said.\n");
        case "":
            throw new AmandaException("\nDon't call me up and say nothing!\n");
        case "task":
            return new AddCommand(generator.make(input));
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(Integer.parseInt(getIndex(input)));
        case "unmark":
            return new UnmarkCommand(Integer.parseInt(getIndex(input)));
        case "delete":
            return new DeleteCommand(Integer.parseInt(getIndex(input)));
        default:
            return new ExitCommand();
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
        } else if (type.equals("list") | type.equals("mark") | type.equals("unmark")
                | type.equals("delete") | type.equals("bye")) {
            return type;
        } else {
            return "not a command";
        }
    }

    /**
     * Get the index of the task in the current task list that the user wants to operate on.
     * @param input one line of the user input.
     * @return the index of the task that the user wants to operate on.
     */
    public static String getIndex(String input) {
        StringTokenizer tokens = new StringTokenizer(input, " ");
        tokens.nextToken(); // skips the first word of the user input
        return tokens.nextToken(); // return the number followed by the command word.
    }
}
