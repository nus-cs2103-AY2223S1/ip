package duke;

import duke.command.Command;
import duke.command.ByeCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.TodoCommand;
import duke.command.DeadlineCommand;
import duke.command.EventCommand;
import duke.command.DeleteCommand;

import java.util.Arrays;

public class Parser {
    public static Command parse(String input) throws DukeException {
        String[] inputSplit = input.split(" ");
        switch(inputSplit[0]) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "mark":
        case "unmark":
            // Error handling
            if (inputSplit.length < 2) {
                throw new DukeException(
                    "You did not specify what task number to mark as done. Unable to mark task.");
            } else if (!isNumeric(inputSplit[1])) {
                throw new DukeException(
                        String.format("Invalid task number provided: %s. Unable to mark task.",
                        inputSplit[1]));
            }
            // Return the command
            return new MarkCommand(Integer.parseInt(inputSplit[1]) - 1,
                    inputSplit[0].equals("mark") ? true : false);
        case "todo":
            // Error handling
            if (inputSplit.length < 2) {
                throw new DukeException("Please provide a description for your todo.");
            }
            // Return the command
            return new TodoCommand(input.substring(5));
        case "deadline":
        case "event":
            String[] inputSlashSplit = input.split("/");
            // Error handling
            if (inputSlashSplit.length < 2 || inputSlashSplit[1].split(" ").length < 2) {
                throw new DukeException(String.format(
                        "Please specify a time for your %s.", inputSplit[0]));
            }
            if (inputSlashSplit[0].split(" ").length < 2) {
                throw new DukeException(String.format(
                        "Please provide a description for your %s.", inputSplit[0]));
            }
            // Return the commands
            String date = input.split("/")[1];
            String[] description = inputSlashSplit[0].split(" ");
            if (inputSplit[0].equals("deadline")) {
                return new DeadlineCommand(String.join(
                        " ", Arrays.copyOfRange(description, 1, description.length)), date);
            } else {
                return new EventCommand(String.join(
                        " ", Arrays.copyOfRange(description, 1, description.length)), date);
            }
        case "delete":
            // Error handling
            if (inputSplit.length < 2) {
                throw new DukeException("You did not specify what task number to delete.");
            } else if (!isNumeric(inputSplit[1])) {
                throw new DukeException(String.format(
                        "Invalid task number provided: %s. Unable to delete task.", inputSplit[1]));
            }
            // Return the command
            return new DeleteCommand(Integer.parseInt(inputSplit[1]) - 1);
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private static boolean isNumeric(String str) {
        return str.matches("\\d+(\\.\\d+)?");
    }
}
