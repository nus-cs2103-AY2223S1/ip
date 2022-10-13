package meowmeow;

import meowmeow.commands.AddCommand;
import meowmeow.commands.Command;
import meowmeow.commands.DefaultCommand;
import meowmeow.commands.DeleteCommand;
import meowmeow.commands.ExitCommand;
import meowmeow.commands.FindCommand;
import meowmeow.commands.HiCommand;
import meowmeow.commands.ListCommand;
import meowmeow.commands.MarkCommand;
import meowmeow.commands.UndoCommand;

/**
 * Class Parser is a class that parses the user's input and translates it into a Command.
 */
public class Parser {

    /**
     * Parses the user's input and returns a Command.
     *
     * @param userInput the user's input.
     * @return a Command that corresponds to the user's input.
     */
    public static Command parse(String userInput) {

        userInput = userInput.trim();
        String[] splitUI = userInput.split(" ");
        assert splitUI.length > 0 : "splitUI should not be empty";
        switch (splitUI[0]) {

        case "hi":
            return new HiCommand();

        case "list":
            return new ListCommand();

        case "bye":
            return new ExitCommand();

        case "undo":
            return new UndoCommand();

        default:
            break;
        }

        String[] splitUserInput = userInput.split(" ", 2);

        String cmdWord = splitUserInput[0];

        switch (cmdWord) {
        case "mark":
            return parseMark(splitUserInput[1]);

        case "unmark":
            return parseUnmark(splitUserInput[1]);

        case "todo":
            return parseToDo(splitUserInput[1]);

        case "deadline":
            return parseDeadline(splitUserInput[1]);

        case "event":
            return parseEvent(splitUserInput[1]);

        case "delete":
            return parseDelete(splitUserInput[1]);

        case "find":
            return parseFind(splitUserInput[1]);

        default:
            return new DefaultCommand("Sowwie meowmeow doesn't understand what you said uwu ♥ \n"
                    + "Try typing something else! owo");
        }
    }

    /**
     * Parses the user's input for the mark command.
     *
     * @param userInput the user's input.
     * @return a MarkCommand that corresponds to the user's input.
     */
    public static Command parseMark(String userInput) {
        try {
            int taskNum = Integer.parseInt(userInput);
            return new MarkCommand(true, taskNum);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return new DefaultCommand("=0w0= Meowmeow needs a number for the task you want to mark!");
        }
    }

    /**
     * Parses the user's input for the unmark command.
     *
     * @param userInput the user's input.
     * @return a MarkCommand that corresponds to the user's input.
     */
    public static Command parseUnmark(String userInput) {
        try {
            int taskNum = Integer.parseInt(userInput);
            return new MarkCommand(false, taskNum);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return new DefaultCommand("=0w0= Meowmeow needs a number for the task you want to unmark!");
        }
    }

    /**
     * Parses the user's input for the todo command.
     *
     * @param userInput the user's input.
     * @return an AddCommand that corresponds to the user's input.
     */
    public static Command parseToDo(String userInput) {
        try {
            String taskName = userInput;
            return new AddCommand('T', taskName);
        } catch (ArrayIndexOutOfBoundsException e) {
            return new DefaultCommand("=0w0= Meowmeow needs a name for the todo you want to add!");
        }
    }

    /**
     * Parses the user's input for the deadline command.
     *
     * @param userInput the user's input.
     * @return an AddCommand that corresponds to the user's input.
     */
    public static Command parseDeadline(String userInput) {
        try {
            String nameAndLocalDateTime = userInput;
            return new AddCommand('D', nameAndLocalDateTime);
        } catch (ArrayIndexOutOfBoundsException e) {
            return new DefaultCommand("=0w0= To add a deadline type it in in this format: "
                    + "deadline (name) /by (YYYY-MM-DDTHH:MM:SS)");
        }
    }

    /**
     * Parses the user's input for the event command.
     *
     * @param userInput the user's input.
     * @return an AddCommand that corresponds to the user's input.
     */
    public static Command parseEvent(String userInput) {
        try {
            String nameAndTime = userInput;
            return new AddCommand('E', nameAndTime);
        } catch (ArrayIndexOutOfBoundsException e) {
            return new DefaultCommand("=0w0= To add an event type it in in this format: event (name) /at (time)");
        }
    }

    /**
     * Parses the user's input for the delete command.
     *
     * @param userInput the user's input.
     * @return a DeleteCommand that corresponds to the user's input.
     */
    public static Command parseDelete(String userInput) {
        try {
            int taskToDelete = Integer.parseInt(userInput);
            return new DeleteCommand(taskToDelete);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return new DefaultCommand("=0w0= Meowmeow needs a number for the task you want to delete!");
        }
    }

    /**
     * Parses the user's input for the find command.
     *
     * @param userInput the user's input.
     * @return a FindCommand that corresponds to the user's input.
     */
    public static Command parseFind(String userInput) {
        try {
            String taskToFind = userInput;
            return new FindCommand(taskToFind);
        } catch (ArrayIndexOutOfBoundsException e) {
            return new DefaultCommand("=0w0= Meowmeow needs a name for the task you want to find!");
        }
    }

}
