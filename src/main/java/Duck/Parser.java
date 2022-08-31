package Duck;

import Commands.Commands;
import Commands.ExitCommand;
import Commands.ListCommand;
import Commands.AddCommand;
import Commands.MarkCommand;
import Commands.UnmarkCommand;
import Commands.DeleteCommand;
import Commands.ErrorCommand;
import Commands.FindCommand;

import Models.Todo;
import Models.Event;
import Models.Deadline;
import Quackceptions.UnallowedCharacterException;
import UI.UI;

import java.text.ParseException;


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
            if (input.contains(";")) throw new UnallowedCharacterException(";");
            else if (input.toUpperCase().equals("BYE")) return new ExitCommand();
            else if (input.toUpperCase().equals("LIST")) return new ListCommand();
            else {
                String[] arr = input.split(" ", 2);
                String command = arr[0];
                String arguments = arr[1];
                switch (command.toUpperCase()) {
                    case "TODO":
                        Todo newTodo = new Todo(arguments, false);
                        return new AddCommand(newTodo);
                    case "DEADLINE":
                        String[] deadlineArgs = arguments.split("/by");
                        Deadline newDeadline = new Deadline(deadlineArgs[0].trim(), false, Duck.dateStorageConverter(deadlineArgs[1].trim()));
                        return new AddCommand(newDeadline);
                    case "EVENT":
                        String[] eventArgs = arguments.split("/at");
                        Event newEvent = new Event(eventArgs[0].trim(), false, Duck.dateStorageConverter(eventArgs[1].trim()));
                        return new AddCommand(newEvent);
                    case "MARK":
                        return new MarkCommand(Integer.parseInt(arguments) - 1);
                    case "UNMARK":
                        return new UnmarkCommand(Integer.parseInt(arguments) - 1);
                    case "DELETE":
                        return new DeleteCommand(Integer.parseInt(arguments) - 1);
                    case "FIND":
                        return new FindCommand(arguments);
                    default:
                        ui.sendTextToUi("Quack! What does that even mean ?!?!?");
                        break;
                }
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
            ui.sendTextToUi("Invalid Arguments! Dummy!");
        } catch (UnallowedCharacterException e) {
            ui.sendTextToUi("Character: " + e.getMessage() + " is not allowed! Quack!!");
        } catch (ParseException e) {
            ui.sendTextToUi("Wrong date time format! Quack! (use: dd/MM/yyyy HHmm) ");
        }
        return new ErrorCommand();
    }

}
