package duke;

import command.ByeCommand;
import command.Command;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.EventCommand;
import command.FindCommand;
import command.ListCommand;
import command.MarkCommand;
import command.PriorityCommand;
import command.TodoCommand;
import command.UnmarkCommand;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import exception.DukeException;
import exception.IncorrectInputException;
import exception.IncorrectInputFormatException;

/**
 * A class that encapsulates the Parser object
 * which deals with making sense of the user command
 *
 * @author  Wee Xin Yang, Markus
 * @version 0.1
 * @since   2022-8-24
 */
public class Parser {

    private final Ui ui;
    private SavedTaskHandler storage;

    /**
     * Constructor for Parser Object
     * @param storage a SavedTaskHandler object
     */
    public Parser(SavedTaskHandler storage) {
        this.storage = storage;
        ui = new Ui();

    }

    /**
     * Returns a boolean value representing whether the input is Numeric
     * @param string The string that will be parsed to check if it is Numeric
     *
     * @return A boolean showing if the input string is Numeric
     */
    // adapted from: https://stackabuse.com/java-check-if-string-is-a-number/
    private static boolean isNumeric(String string) {
        int intValue;

        if (string == null || string.equals("")) {
            return false;
        }

        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void checkInput(String input) throws DukeException, IncorrectInputException, IncorrectInputFormatException,
            DateTimeParseException {
        ArrayList<String> commandList = new ArrayList<>();
        commandList.add("bye");
        commandList.add("list");
        commandList.add("mark");
        commandList.add("unmark");
        commandList.add("delete");
        commandList.add("deadline");
        commandList.add("event");
        commandList.add("todo");
        commandList.add("find");
        commandList.add("priority");
        ArrayList<String> priorityList = new ArrayList<>();
        priorityList.add("high");
        priorityList.add("medium");
        priorityList.add("low");
        String[] splitStr = input.split(" ");
        String commandString = splitStr[0];

        if (input.equals("bye") || input.equals("list")) {
            return;
        }

        if (!commandList.contains(commandString)) {
            throw new IncorrectInputException("Your inputted command does not exist...");
        }

        if (commandString.equals("mark") || commandString.equals("unmark") || commandString.equals("delete")) {
            if (splitStr.length != 2) {
                throw new IncorrectInputException("Please input a number after the command");
            }

            if (!isNumeric(splitStr[1])) {
                throw new IncorrectInputFormatException("What you typed following the command is not a number...");
            }

            int index = Integer.parseInt(splitStr[1]);

            if (index < 1 || index > storage.getTaskList().size()) {
                throw new DukeException("Your given index is out of bounds...");
            }

        }

        if (commandString.equals("todo") || commandString.equals("find")) {
            if (splitStr.length < 2) {
                throw new IncorrectInputException("Please input a task name after the command...");
            }
        }

        if (commandString.equals("priority")) {
            if (splitStr.length != 3) {
                throw new IncorrectInputException("Make sure your input is in the format <priority> "
                        + "<task number> <high, medium, low>");
            }

            if (!isNumeric(splitStr[1]) || !priorityList.contains(splitStr[2])) {
                throw new IncorrectInputFormatException("Make sure your input is in the format <priority> "
                        + "<task number> <high, medium, low>");
            }

            int index = Integer.parseInt(splitStr[1]);

            if (index < 1 || index > storage.getTaskList().size()) {
                throw new DukeException("Your given index is out of bounds...");
            }
        }

        if (commandString.equals("event")) {
            if (splitStr.length < 4) {
                throw new IncorrectInputException("Make sure your input is in the format <event> <event name> "
                        + "</at> <date>");
            }

            String[] splitEventStr = input.split("/at ");

            if (splitEventStr.length != 2 || splitStr[1].equals("/at")) {
                throw new IncorrectInputFormatException("Make sure your input is in the format <event> <event name> "
                        + "</at> <date>");
            }

            LocalDate.parse(splitEventStr[1]);
        }

        if (commandString.equals("deadline")) {
            if (splitStr.length < 4) {
                throw new IncorrectInputException("Make sure your input is in the format <event> <event name> "
                        + "</by> <date>");
            }

            String[] splitEventStr = input.split("/by ");

            if (splitEventStr.length != 2 || splitStr[1].equals("/by")) {
                throw new IncorrectInputFormatException("Make sure your input is in the format <event> <event name> "
                        + "</at> <date>");
            }
            LocalDate.parse(splitEventStr[1]);
        }
    }

    /**
     * Parses the user's input and assign different actions according to the input
     * @param input The string that will be parsed
     * @return a Command object representing the type of Command to be executed
     */
    public Command parse(String input) throws ParseException, DukeException, IncorrectInputFormatException,
            IncorrectInputException {
        String[] splitStr = input.split("\\s+");
        TaskList taskList = storage.getTaskList();
        String commandString = splitStr[0];
        checkInput(input);
        switch (commandString) {

            case "bye":
                storage.write(taskList);
                return new ByeCommand();

            case "list":
                return new ListCommand(taskList);

            case "mark":
                int index = Integer.valueOf(splitStr[1]) - 1;
                return new MarkCommand(taskList, index);

            case "unmark":
                int index1 = Integer.valueOf(splitStr[1]) - 1;
                return new UnmarkCommand(taskList, index1);

            case "delete":
                int index2 = Integer.valueOf(splitStr[1]) - 1;
                return new DeleteCommand(taskList, index2);

            case "deadline":
                return new DeadlineCommand(taskList, input, splitStr[0]);

            case "event":
                return new EventCommand(taskList, input, splitStr[0]);

            case "todo":
                return new TodoCommand(taskList, input);

            case "find":
                return new FindCommand(taskList, input);

            case "priority":
                int index3 = Integer.valueOf(splitStr[1]) - 1;
                String priority = splitStr[2];
                return new PriorityCommand(taskList, index3, priority);
            default:
                return null;
        }
    }
}

