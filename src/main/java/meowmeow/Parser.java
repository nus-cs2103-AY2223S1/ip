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
            try {
                int taskNum = Integer.parseInt(splitUserInput[1]);
                return new MarkCommand(true, taskNum);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                return new DefaultCommand("=0w0= Meowmeow needs a number for the task you want to mark!");
            }

        case "unmark":
            try {
                int taskNum = Integer.parseInt(splitUserInput[1]);
                return new MarkCommand(false, taskNum);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                return new DefaultCommand("=0w0= Meowmeow needs a number for the task you want to unmark!");
            }

        case "todo":
            try {
                String taskName = splitUserInput[1];
                return new AddCommand('T', taskName);
            } catch (ArrayIndexOutOfBoundsException e) {
                return new DefaultCommand("=0w0= Meowmeow needs a name for the todo you want to add!");
            }

        case "deadline":
            try {
                String nameAndLocalDateTime = splitUserInput[1];
                return new AddCommand('D', nameAndLocalDateTime);
            } catch (ArrayIndexOutOfBoundsException e) {
                return new DefaultCommand("=0w0= To add a deadline type it in in this format: "
                        + "deadline (name) /by (YYYY-MM-DDTHH:MM:SS)");
            }

        case "event":
            try {
                String nameAndTime = splitUserInput[1];
                return new AddCommand('E', nameAndTime);
            } catch (ArrayIndexOutOfBoundsException e) {
                return new DefaultCommand("=0w0= To add an event type it in in this format: event (name) /at (time)");
            }

        case "delete":
            try {
                int taskToDelete = Integer.parseInt(splitUserInput[1]);
                return new DeleteCommand(taskToDelete);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                return new DefaultCommand("=0w0= Meowmeow needs a number for the task you want to delete!");
            }

        case "find":
            try {
                String taskToFind = splitUserInput[1];
                return new FindCommand(taskToFind);
            } catch (ArrayIndexOutOfBoundsException e) {
                return new DefaultCommand("=0w0= Meowmeow needs a name for the task you want to find!");
            }

        default:
            return new DefaultCommand("Sowwie meowmeow doesn't understand what you said uwu ♥ \n"
                    + "Try typing something else! owo");
        }
    }
}
