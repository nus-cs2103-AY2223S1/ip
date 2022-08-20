package duke.parser;


import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnMarkCommand;
import duke.common.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

import java.time.LocalDate;
import java.util.Arrays;

public abstract class Parser {
    public static Command parse(String input) throws DukeException {
        String[] args = input.trim().split("\\s+");
        Command command;
        switch (args[0]) {
        case "bye":
            command = new ExitCommand();
            break;
        case "list":
            command = new ListCommand();
            break;
        case "mark":
            try {
                command = new MarkCommand(Integer.parseInt(args[1]) - 1);
            } catch (IndexOutOfBoundsException exception) {
                throw new DukeException("OOPS!!! No task index is specified :(");
            } catch (NumberFormatException exception) {
                throw new DukeException("OOPS!!! You didn't give a valid index :(");
            }
            break;
        case "unmark":
            try {
                command = new UnMarkCommand(Integer.parseInt(args[1]) - 1);
            } catch (IndexOutOfBoundsException exception) {
                throw new DukeException("OOPS!!! No task index is specified :(");
            } catch (NumberFormatException exception) {
                throw new DukeException("OOPS!!! You didn't give a valid index :(");
            }
            break;
        case "delete":
            try {
                command = new DeleteCommand(Integer.parseInt(args[1]) - 1);
            } catch (IndexOutOfBoundsException exception) {
                throw new DukeException("OOPS!!! No task index is specified :(");
            } catch (NumberFormatException exception) {
                throw new DukeException("OOPS!!! You didn't give a valid index :(");
            }
            break;
        case "todo":
            command = new AddCommand(new ToDo(
                    Arrays.stream(args).skip(1).reduce("", (x, y) -> x + " " + y).trim(),
                    false));
            break;
        case "event":
            try {
                String description = Arrays.stream(args)
                        .takeWhile(x -> !x.equals("/at")).skip(1)
                        .reduce("", (x, y) -> x + " " + y).trim();
                String[] timeArgument = Arrays.stream(args)
                        .dropWhile(x -> !x.equals("/at")).toArray(String[]::new);
                if (timeArgument.length <= 1) {
                    throw new DukeException("OOPS!!! There is no /at argument for event :(");
                }
                command = new AddCommand(new Event(description, false, LocalDate.parse(timeArgument[1])));
            } catch (java.time.format.DateTimeParseException exception) {
                throw new DukeException(
                        "OOPS!!! Can't recognize the date :(. Please input the date in yyyy-mm-dd format.");
            }
            break;
        case "deadline":
            try {
                String description = Arrays.stream(args).takeWhile(x -> !x.equals("/by"))
                        .skip(1).reduce("", (x, y) -> x + " " + y).trim();
                String[] timeArgument = Arrays.stream(args).dropWhile(x -> !x.equals("/by"))
                        .toArray(String[]::new);
                if (timeArgument.length <= 1) {
                    throw new DukeException("OOPS!!! There is no /by argument for deadline :(");
                }
                command = new AddCommand(new Deadline(description, false, LocalDate.parse(timeArgument[1])));
            } catch (java.time.format.DateTimeParseException exception) {
                throw new DukeException(
                        "OOPS!!! Can't recognize the date :(. Please input the date in yyyy-mm-dd format.");
            }
            break;
        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :(");
        }
        return command;
    }
}
