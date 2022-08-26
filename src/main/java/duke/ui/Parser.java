package duke.ui;

import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {

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
                default:
                    return new IncorrectCommand("Sorry, I don't quite understand what you mean...");
            }

        } catch (NumberFormatException e) {
            return new IncorrectCommand("Task Index must be an integer...");
        } catch (IndexOutOfBoundsException e) {
            return new IncorrectCommand("The description of the " + commandInput + " is incorrect...");
        } catch (DateTimeParseException e) {
            return new IncorrectCommand("Deadline format is incorrect");
        }
        return command;
    }
}
    

