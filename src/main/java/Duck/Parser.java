package Duck;

import Command.Commands;
import Command.ExitCommand;
import Command.ListCommand;
import Command.AddCommand;
import Command.MarkCommand;
import Command.UnmarkCommand;
import Command.DeleteCommand;
import Command.ErrorCommand;
import Command.FindCommand;
import Command.UpdateCommand;

import Models.Todo;
import Models.Event;
import Models.Deadline;
import Quackceptions.InvalidObjectClass;
import Quackceptions.UnallowedCharacterException;
import UI.UI;

import java.text.ParseException;
import java.util.Date;


public class Parser {
    /**
     * Parser is a static class that primarily
     * serves to understand the user's input
     * the parseText function splits the user input
     * into 2 using space as a divider
     * into two variables command and argument
     * then it splits the arguments using
     * /by or /at depending on the command
     *
     * @param input input string to be parsed
     * @return returns a type of command corresponding to the input given
     */
    public static Commands parseText(String input, UI ui)  {
        try {
            if (input.contains(";")) {
                throw new UnallowedCharacterException(";");
            } else if (input.toUpperCase().equals("BYE")) {
                return new ExitCommand();
            } else if (input.toUpperCase().equals("LIST")) {
                return new ListCommand();
            } else {
                return commandsWithArguments(input, ui);
            }
        } catch (ArrayIndexOutOfBoundsException a) {
            if (input.toUpperCase().contains("TODO") ||
                    input.toUpperCase().contains("DEADLINE") ||
                    input.toUpperCase().contains("EVENT")) {
                ui.sendTextToUi("Quack!!!!! " + input.toUpperCase() + " Arguments are missing!");
            } else {
                ui.sendTextToUi("Speak properly! Quack!");
            }
        } catch (NumberFormatException n) {
            ui.sendTextToUi("Wrong arguments! Quack!");
        } catch (UnallowedCharacterException e) {
            ui.sendTextToUi("Character: " + e.getMessage() + " is not allowed! Quack!!");
        } catch (ParseException e) {
            ui.sendTextToUi("Wrong date time format! Quack! (use: dd/MM/yyyy HHmm) ");
        } catch (InvalidObjectClass e) {
            ui.sendTextToUi(e.getMessage());
        }
        return new ErrorCommand();
    }

    /**
     * Abstracted function to handle for commands with arguments
     * @param input input string to be parsed
     * @param ui UI object to be used
     * @return returns a type of command corresponding to the input given
     */
    private static Commands commandsWithArguments(String input, UI ui) throws ParseException, InvalidObjectClass {
        String[] arr = input.split(" ", 2);
        String command = arr[0];
        String arguments = arr[1];
        switch (command.toUpperCase()) {
        case "TODO":
            Todo newTodo = new Todo(arguments, false);
            return new AddCommand(newTodo);
        case "DEADLINE":
            String[] deadlineArgs = arguments.split("/by");
            Deadline newDeadline = new Deadline(deadlineArgs[0].trim(), false,
                    Duck.dateStorageConverter(deadlineArgs[1].trim()));
            return new AddCommand(newDeadline);
        case "EVENT":
            String[] eventArgs = arguments.split("/at");
            Event newEvent = new Event(eventArgs[0].trim(), false,
                    Duck.dateStorageConverter(eventArgs[1].trim()));
            return new AddCommand(newEvent);
        case "MARK":
            return new MarkCommand(Integer.parseInt(arguments) - 1);
        case "UNMARK":
            return new UnmarkCommand(Integer.parseInt(arguments) - 1);
        case "DELETE":
            return new DeleteCommand(Integer.parseInt(arguments) - 1);
        case "FIND":
            return new FindCommand(arguments);
        case "UPDATE": //update 1 /time 10/10/1010 10:10
            return parseUpdateCommand(arguments);
        default:
            ui.sendTextToUi("Quack! What does that even mean ?!?!?");
            break;
        }
        return new ErrorCommand();
    }

    /**
     * Function specifically to handle the UpdateCommand
     * @param arguments the arguments inputted by the user
     * @return returns a new UpdateCommand
     * @throws ParseException thrown when numbers cannot be parsed
     * @throws InvalidObjectClass thrown when arguments given are invalid
     */
    private static UpdateCommand parseUpdateCommand(String arguments) throws ParseException, InvalidObjectClass {
        String[] updateArgs;
        Date updatedDate;
        String updatedTitle;

        if (arguments.toUpperCase().contains("/title".toUpperCase())) {
            updateArgs = arguments.split("/title");
            int index = Integer.parseInt(updateArgs[0].trim()) - 1;
            updatedTitle = updateArgs[1].trim();
            return new UpdateCommand(index, updatedTitle);
        } else if (arguments.toUpperCase().contains("/time".toUpperCase())) {
            updateArgs = arguments.split("/time");
            int index = Integer.parseInt(updateArgs[0].trim()) - 1;
            updatedDate = Duck.dateStorageConverter(updateArgs[1].trim());
            return new UpdateCommand(index, updatedDate);
        } else {
            throw new InvalidObjectClass("Arguments are wrong! Quack!");
        }

    }
}
