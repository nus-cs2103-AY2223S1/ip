package duke.parser;

import java.util.Arrays;
import java.util.List;

import duke.command.Command;
import duke.exceptions.DukeException;

/**
 * Deals with making sense of the user's commands.
 *
 * @author ish1506
 */
public class Parser {
    private final String input;
    private Command command;
    private int taskIndex = -1;
    private String[] args;

    /**
     * Constructs the <code>Parser</code> object given an input string.
     *
     * @param input the user input.
     */
    public Parser(String input) {
        this.input = input;
    }

    public Command getCommand() {
        return command;
    }

    public int getTaskIndex() {
        return taskIndex;
    }

    public String[] getArgs() {
        return args;
    }

    /**
     * Parses the user input to get the command and its arguments.
     *
     * @throws DukeException if the input cannot be parsed.
     */
    public void parse() throws DukeException {
        List<String> listOfInputs = Arrays.asList(input.split(" "));
        if (input.isEmpty() || listOfInputs.isEmpty()) {
            throw new DukeException("Please enter a command!");
        }
        int numArgs = listOfInputs.size();
        String firstArg = listOfInputs.get(0);

        switch (firstArg) {
        case "bye":
            command = Command.BYE;
            break;
        case "list":
            command = Command.LIST;
            break;
        case "mark":
            if (numArgs != 2) {
                throw new DukeException("Input format: mark (task number)");
            }
            command = Command.MARK;
            taskIndex = integerParser(listOfInputs.get(1), "mark") - 1;
            break;
        case "unmark":
            if (numArgs != 2) {
                throw new DukeException("Input format: unmark (task number)");
            }
            command = Command.UNMARK;
            taskIndex = integerParser(listOfInputs.get(1), "unmark") - 1;
            break;
        case "delete":
            if (numArgs != 2) {
                throw new DukeException("Input format: delete (task number)");
            }
            command = Command.DELETE;
            taskIndex = integerParser(listOfInputs.get(1), "delete") - 1;
            break;
        case "find":
            if (numArgs != 2) {
                throw new DukeException("Input format: find (keyword)");
            }
            command = Command.FIND;
            args = new String[]{listOfInputs.get(1)};
            break;
        case "todo":
            if (numArgs == 1) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }
            command = Command.TODO;
            String todoName = input.split("todo ")[1];
            args = new String[]{todoName};
            break;
        case "event": {
            if (numArgs < 4 || !listOfInputs.contains("\\at")) {
                throw new DukeException("Input format: event (event name) \\at (event date/time)");
            }
            command = Command.EVENT;
            int index = input.indexOf("\\at ");
            String eventName = input.substring(6, index);
            String eventTime = input.substring(index + 4);
            args = new String[]{eventName, eventTime};
            break;
        }
        case "deadline": {
            if (numArgs < 4 || !listOfInputs.contains("\\by")) {
                throw new DukeException("Input format: deadline (deadline name) \\by (YYYY-MM-DD)");
            }
            command = Command.DEADLINE;
            int index = input.indexOf("\\by ");
            String deadlineName = input.substring(9, index);
            String deadlineTime = input.substring(index + 4);
            args = new String[]{deadlineName, deadlineTime};
            break;
        }
        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Parses a string input for commands with a list index as an argument.
     *
     * @param arg         the argument string.
     * @param commandName the name of the command.
     * @return the int representing the list index.
     * @throws DukeException if the string is of the wrong format.
     */
    private int integerParser(String arg, String commandName) throws DukeException {
        try {
            return Integer.parseInt(arg);
        } catch (NumberFormatException e) {
            throw new DukeException("Input format: " + commandName + " (task number)");
        }
    }
}
