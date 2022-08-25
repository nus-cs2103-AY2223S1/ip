package duke.main;

import duke.command.Command;
import duke.command.FindCommand;
import duke.command.ExitCommand;
import duke.command.EventCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.DateCommand;
import duke.command.DeadlineCommand;
import duke.command.UnmarkCommand;
import duke.command.IncomprehensibleCommand;
import duke.command.ToDoCommand;
import duke.command.DeleteCommand;
import java.time.DateTimeException;

public class Parser {

    /**
     * Interprets a string of user input to be use to
     * instruct a particular intended command.
     *
     * @param input a user input to be interpreted
     * @return a Command
     */
    public static Command parse(String input) throws DukeException{
        String[] userInput = input.trim().split(" ", 2);
        switch (userInput[0]) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "mark":
                try {
                    return new MarkCommand(Integer.parseInt(userInput[1]));
                } catch (IndexOutOfBoundsException e){
                    throw new DukeException("Which one to mark??");
                } catch (NumberFormatException e) {
                    throw new DukeException("Can you type a valid number? Don't try to be funny.");
                }
            case "unmark":
                try {
                    return new UnmarkCommand(Integer.parseInt(userInput[1]));
                } catch (IndexOutOfBoundsException e){
                    throw new DukeException("Which one to unmark??");
                } catch (NumberFormatException e) {
                    throw new DukeException("Can you type a whole number? Don't try to be funny.");
                }
            case "todo":
                try {
                    return new ToDoCommand(userInput[1]);
                } catch(IndexOutOfBoundsException e) {
                    throw new DukeException("The description of a todo cannot be empty!");
                }
            case "deadline":
                try {
                    String[] thingAndDate = userInput[1].split(" /by ");
                    return new DeadlineCommand(thingAndDate[0], thingAndDate[1]);
                } catch(IndexOutOfBoundsException e) {
                    throw new DukeException("Invalid input for deadline!");
                }
            case "event":
                try {
                    String[] thingsAndDate = userInput[1].split(" /at ");
                    return new EventCommand(thingsAndDate[0], thingsAndDate[1]);
                } catch(IndexOutOfBoundsException e) {
                    throw new DukeException("Invalid input for event!");
                }
            case "delete":
                try {
                    return new DeleteCommand(Integer.parseInt(userInput[1]));
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("Delete... delete what??");
                } catch (NumberFormatException e) {
                    throw new DukeException("Can you type a valid number?");
                }
            case "date":
                try {
                    return new DateCommand(userInput[1]);
                } catch(DateTimeException e) {
                    throw new DukeException("Key in a valid date!");
                }
            case "find":
                try {
                    return new FindCommand(userInput[1]);
                } catch(ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("What do you want me to find for you...?");
                }

            default:
                return new IncomprehensibleCommand();
        }
    }
}