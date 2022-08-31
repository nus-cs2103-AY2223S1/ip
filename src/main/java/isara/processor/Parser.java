package isara.processor;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import isara.command.ByeCommand;
import isara.command.Command;
import isara.command.DeleteCommand;
import isara.command.FindCommand;
import isara.command.ListCommand;
import isara.command.MarkCommand;
import isara.command.UnmarkCommand;
import isara.exception.IsaraException;
import isara.task.Deadline;
import isara.task.Event;
import isara.task.Task;
import isara.task.ToDo;

/**
 * Class that represents a parser to parse the user input.
 *
 * @author Melissa Anastasia Harijanto
 */
public class Parser {
    /** Enum class for the available command lines. */
    enum CommandLine {
        BYE,
        MARK,
        UNMARK,
        DELETE,
        LIST,
        FIND,
    }

    /** Enum class for the available types of tasks. */
    enum TypeOfTask {
        DEADLINE,
        TODO,
        EVENT,
    }

    /**
     * Determines whether the user input is a task.
     *
     * @param input The String that the user has inputted.
     * @return A boolean value; returns true if the input is a task, false otherwise.
     */
    public static boolean isTask(String input) {
        return input.equals("deadline") || input.equals("todo")
                || input.equals("event");
    }

    /**
     * Determines whether a command line inputted by
     * the user is a valid command.
     *
     * @param input duke.command.Command line that is inputted by the user.
     * @return A boolean value that states whether the command exists or not.
     */
    public static boolean isCommand(String input) {
        return input.equals("bye") || input.equals("mark")
                || input.equals("unmark") || input.equals("delete")
                || input.equals("list") || input.equals("find");
    }

    /**
     * Parses the user input if it is categorized as a command.
     *
     * @param input The user input.
     * @return An instance of Command.
     * @throws IsaraException An exception will be thrown if the command is
     *     not one of the available commands.
     */
    public static Command parseCommand(String input) throws IsaraException {
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
        case FIND:
            String keyword = input.split(" ")[1];
            return new FindCommand(keyword);
        default:
            throw new IsaraException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Parses the user input if it is categorized as a task.
     *
     * @param input The user input.
     * @return An instance of Task.
     * @throws IsaraException An exception will be thrown if the task type is
     *     not one of the available tasks.
     */
    public static Task parseTask(String input) throws IsaraException {
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
                throw new IsaraException("☹ OOPS!!! Please specify what you want to do!");
            }
            break;
        case EVENT:
            try {
                String taskName = input.substring(input.indexOf(" ") + 1, input.indexOf("/") - 1);
                String at = input.split("/at")[1].trim();
                return new Event(taskName, at);
            } catch (Exception e) {
                throw new IsaraException("☹ OOPS!!! The description of an event cannot be empty.");
            }
        case DEADLINE:
            try {
                String taskName = input.substring(input.indexOf(" ") + 1, input.indexOf("/") - 1);
                String by = input.split("/by")[1].trim();
                LocalDate date = LocalDate.parse(by);
                return new Deadline(taskName, date);
            } catch (DateTimeParseException e) {
                throw new IsaraException("☹ OOPS!! Date is invalid! Hint: Make it YYYY-MM-DD.");
            } catch (Exception e) {
                throw new IsaraException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }

        default:
            throw new IsaraException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return null;
    }
}
