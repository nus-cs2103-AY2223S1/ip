package isara.processor;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import isara.command.ByeCommand;
import isara.command.Command;
import isara.command.DeleteCommand;
import isara.command.FindCommand;
import isara.command.ListCommand;
import isara.command.MarkCommand;
import isara.command.RescheduleCommand;
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
        RESCHEDULE,
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
        boolean isInputDeadline = input.equals("deadline");
        boolean isInputToDo = input.equals("todo");
        boolean isInputEvent = input.equals("event");
        return isInputDeadline || isInputToDo
                || isInputEvent;
    }

    /**
     * Determines whether a command line inputted by
     * the user is a valid command.
     *
     * @param input duke.command.Command line that is inputted by the user.
     * @return A boolean value that states whether the command exists or not.
     */
    public static boolean isCommand(String input) {
        boolean isInputBye = input.equals("bye");
        boolean isInputMark = input.equals("mark");
        boolean isInputUnmark = input.equals("unmark");
        boolean isInputDelete = input.equals("delete");
        boolean isInputList = input.equals("list");
        boolean isInputFind = input.equals("find");
        boolean isInputReschedule = input.equals("reschedule");
        return isInputBye || isInputMark || isInputUnmark
                || isInputDelete || isInputList
                || isInputFind || isInputReschedule;
    }

    /**
     * Parses commands that are followed by only a task index.
     *
     * @param input String that is inputted by the user.
     * @param command The command line inputted by the user.
     * @return Command that is inputted by the user. Only one command
     *     of type Mark, Unmark, or Delete would be returned.
     */
    public static Command parseCommandsWithOnlyTaskIndex(String input, CommandLine command) {
        String taskToBeUpdated = input.split(" ")[1];
        int taskIndex = Integer.parseInt(taskToBeUpdated) - 1;
        if (command.equals(CommandLine.MARK)) {
            return new MarkCommand(taskIndex);
        } else if (command.equals(CommandLine.UNMARK)) {
            return new UnmarkCommand(taskIndex);
        } else if (command.equals(CommandLine.DELETE)) {
            return new DeleteCommand(taskIndex);
        }
        return null;
    }

    /**
     * Parses one-word commands.
     *
     * @param command The command line inputted by the user.
     * @return Command that is inputted by user. Only returns one command of type List or Bye.
     */
    public static Command parseOneWordCommands(CommandLine command) {
        if (command.equals(CommandLine.LIST)) {
            return new ListCommand();
        } else if (command.equals(CommandLine.BYE)) {
            return new ByeCommand();
        }
        return null;
    }

    /**
     * Parses the Find command into a keyword.
     *
     * @param input String inputted by the user.
     * @return An instance of the Find command.
     */
    public static Command parseFindCommand(String input) {
        String keyword = input.split(" ")[1];
        return new FindCommand(keyword);
    }

    /**
     * Parses the Reschedule command into a task index, and a LocalDate object.
     *
     * @param input String inputted by the user.
     * @return An instance of the Reschedule command.
     */
    public static Command parseRescheduleCommand(String input) {
        String[] content = input.split(" ");
        String taskToBeRescheduled = content[1].trim();
        int taskIndexToBeRescheduled = Integer.parseInt(taskToBeRescheduled) - 1;
        String by = content[3].trim();
        LocalDate date = LocalDate.parse(by);
        return new RescheduleCommand(taskIndexToBeRescheduled, date);
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
        case LIST:
            return parseOneWordCommands(commandLine);
        case MARK:
        case DELETE:
        case UNMARK:
            try {
                return parseCommandsWithOnlyTaskIndex(input, commandLine);
            } catch (NumberFormatException e) {
                throw new IsaraException("☹ OOPS!! Please enter the task number after the command!");
            }
        case FIND:
            return parseFindCommand(input);
        case RESCHEDULE:
            try {
                return parseRescheduleCommand(input);
            } catch (DateTimeParseException e) {
                throw new IsaraException("☹ OOPS!! Date is invalid! Hint: Make it YYYY-MM-DD.");
            } catch (Exception e) {
                throw new IsaraException("☹ OOPS!! I don't know when you are rescheduling this to.");
            }
        default:
            throw new IsaraException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }


    /**
     * Parses the input into taskName and date;
     * returns a new Deadline task with those details.
     *
     * @param input The user input.
     * @return The Deadline task.
     */
    public static Task parseDeadline(String input) {
        String taskName = input.substring(input.indexOf(" ") + 1, input.indexOf("/") - 1);
        String by = input.split("/by")[1].trim();
        LocalDate date = LocalDate.parse(by);
        return new Deadline(taskName, date);
    }

    /**
     * Parses the input into a taskName and the event details;
     * returns a new Event task with those details.
     *
     * @param input The user input.
     * @return The Event task.
     */
    public static Task parseEvent(String input) {
        String taskName = input.substring(input.indexOf(" ") + 1, input.indexOf("/") - 1);
        String at = input.split("/at")[1].trim();
        return new Event(taskName, at);
    }

    /**
     * Parses the ToDo task to a task name and returns
     * the ToDo event with those details.
     *
     * @param input The user input.
     * @return The ToDo event.
     */
    public static Task parseToDo(String input) {
        if (input.split(" ")[1] != null) {
            String taskName = input.substring(input.indexOf(" ") + 1);
            return new ToDo(taskName);
        } else {
            return null;
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
                return parseToDo(input);
            } catch (Exception e) {
                throw new IsaraException("☹ OOPS!!! Please specify what you want to do!");
            }
        case EVENT:
            try {
                return parseEvent(input);
            } catch (Exception e) {
                throw new IsaraException("☹ OOPS!!! The description of an event cannot be empty.");
            }
        case DEADLINE:
            try {
                return parseDeadline(input);
            } catch (DateTimeParseException e) {
                throw new IsaraException("☹ OOPS!! Date is invalid! Hint: Make it YYYY-MM-DD.");
            } catch (Exception e) {
                throw new IsaraException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
        default:
            throw new IsaraException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
