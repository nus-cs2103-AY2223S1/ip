package duke.processor;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;


public class Parser {
    enum CommandLine {
        BYE,
        MARK,
        UNMARK,
        DELETE,
        LIST,
    }

    enum TypeOfTask {
        DEADLINE,
        TODO,
        EVENT,
    }

    public static boolean isTask(String task) {
        return task.equals("deadline") || task.equals("todo")
                || task.equals("event");
    }

    /**
     * Determines whether a command line inputted by
     * the user is a valid command.
     *
     * @param command duke.command.Command line that is inputted by the user.
     * @return A boolean value that states whether the command exists or not.
     */
    public static boolean isCommand(String command) {
        return command.equals("bye") || command.equals("mark")
                || command.equals("unmark") || command.equals("delete")
                || command.equals("list");
    }

    public static Command parseCommand(String input) throws DukeException {
        String command = input.split(" ")[0].trim();
        CommandLine commandLine = CommandLine.valueOf(command.toUpperCase());

        switch(commandLine) {
        case BYE:
            return new ByeCommand();
        case MARK:
            String taskToBeMarked = input.split(" ")[1];
            int taskIndexToBeMarked = Integer.parseInt(taskToBeMarked) - 1;
            return new MarkCommand(taskIndexToBeMarked);
        case DELETE:
            String taskToBeDeleted = input.split(" ")[1];
            int taskIndexToBeDeleted = Integer.parseInt(taskToBeDeleted) - 1;
            return new DeleteCommand(taskIndexToBeDeleted);
        case UNMARK:
            String taskToBeUnmarked = input.split(" ")[1];
            int taskIndexToBeUnmarked = Integer.parseInt(taskToBeUnmarked) - 1;
            return new UnmarkCommand(taskIndexToBeUnmarked);
        case LIST:
            return new ListCommand();
        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static Task parseTask(String input) throws DukeException {
        String type = input.split(" ")[0].trim();
        TypeOfTask typeOfTask = TypeOfTask.valueOf(type.toUpperCase());

        switch (typeOfTask) {
        case TODO:
            try {
                if (input.split(" ")[1] != null) {
                    String taskName = input.substring(input.indexOf(" ") + 1);
                    return new ToDo(taskName);
                }
            } catch (Exception e) {
                throw new DukeException("☹ OOPS!!! Please specify what you want to do!");
            }
        case EVENT:
            try {
                String taskName = input.substring(input.indexOf(" ") + 1, input.indexOf("/") - 1);
                String at = input.split("/at")[1].trim();
                return new Event(taskName, at);
            } catch (Exception e) {
                throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
            }
        case DEADLINE:
            try {
                String taskName = input.substring(input.indexOf(" ") + 1, input.indexOf("/") - 1);
                String by = input.split("/by")[1].trim();
                LocalDate date = LocalDate.parse(by);
                return new Deadline(taskName, date);
            } catch (DateTimeParseException e) {
                throw new DukeException("☹ OOPS!! Date is invalid! Hint: Make it YYYY-MM-DD.");
            } catch (Exception e) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }

        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

    }
}
