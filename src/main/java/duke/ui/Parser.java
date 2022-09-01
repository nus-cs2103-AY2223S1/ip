package duke.ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.IncorrectCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * Parser class to deal with making sense of the user command
 */
public class Parser {
    /**
     * Returns the corresponding Command object based on user input.
     * If user input is incorrect, an IncorrectCommand object is returned.
     *
     * @param fullCommand Standard input read from user
     * @return Command Object
     */
    public static Command parse(String fullCommand) {
        String[] input = fullCommand.split(" ", 2);
        String commandInput = input[0].toLowerCase();
        Command command;
        try {
            switch (commandInput) {
            case "bye":
                command = new ExitCommand();
                break;
            case "list":
                command = new ListCommand();
                break;
            case "mark":
            case "unmark":
                int taskToMark = Integer.parseInt(input[1]);
                command = new MarkCommand(commandInput, taskToMark);
                break;
            case "delete":
                int taskIndexToDelete = Integer.parseInt(input[1]);
                command = new DeleteCommand(taskIndexToDelete);
                break;
            case "todo":
                command = new AddCommand(new Todo(input[1]));
                break;
            case "deadline":
                String[] str = input[1].split(" /by ", 2);
                command = new AddCommand(new Deadline(str[0], LocalDate.parse(str[1])));
                break;
            case "event":
                String[] str2 = input[1].split(" /at ", 2);
                command = new AddCommand(new Event(str2[0], str2[1]));
                break;
            case "find":
                String keyword = input[1];
                command = new FindCommand(keyword);
                break;
            default:
                return new IncorrectCommand("Sorry, I don't quite understand what you mean...");
            }

        } catch (NumberFormatException e) {
            return new IncorrectCommand("Task Index should be an integer...");
        } catch (IndexOutOfBoundsException e) {
            return new IncorrectCommand("The description of the " + commandInput + " is incorrect...");
        } catch (DateTimeParseException e) {
            return new IncorrectCommand("Deadline format is incorrect");
        }
        return command;
    }
}
