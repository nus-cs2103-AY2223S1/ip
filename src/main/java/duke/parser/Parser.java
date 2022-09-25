package duke.parser;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import duke.commands.AddCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.UnmarkCommand;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Parses user input.
 */
public class Parser {

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     */
    public static Command parse(String userInput) throws DukeException {
        String commandWord = userInput.trim().split(" ", 2)[0];
        switch (commandWord) {
        case AddCommand.ADD_TODO:
            return parseToDo(userInput);
        case AddCommand.ADD_DEADLINE:
            return parseDeadline(userInput);
        case AddCommand.ADD_EVENT:
            return parseEvent(userInput);
        case DeleteCommand.DELETE_COMMAND:
            return parseDeleteTask(userInput);
        case MarkCommand.MARK_COMMAND:
            return parseMarkTask(userInput);
        case UnmarkCommand.UNMARK_COMMAND:
            return parseUnmarkTask(userInput);
        case FindCommand.FIND_COMMAND:
            return parseFindTask(userInput);
        case ExitCommand.EXIT_COMMAND:
            return new ExitCommand();
        case ListCommand.LIST_COMMAND:
            return new ListCommand();
        default:
            throw new DukeException("Hey! Take me seriously!! What are you trying to say?");
        }
    }

    private static Command parseToDo(String userInput) throws DukeException {
        try {
            String arguments = userInput.trim().split(" ", 2)[1];
            Task task = new ToDo(arguments);
            Set<String> toDoCommand = new HashSet<>();
            toDoCommand.add(arguments);
            return new AddCommand(task, toDoCommand);
        } catch (Exception e) {
            throw new DukeException("Need more description for your todo! Do what??");
        }
    }

    private static Command parseDeadline(String userInput) throws DukeException {
        try {
            String arguments = userInput.trim().split(" ", 2)[1];
            String deadline = arguments.split(" /by ")[0];
            String by = arguments.split(" /by ")[1];
            LocalDate date = LocalDate.parse(by);
            Task task = new Deadline(deadline, date);
            Set<String> deadlineCommand = new HashSet<>();
            deadlineCommand.add(deadline);
            return new AddCommand(task, deadlineCommand);
        } catch (Exception e) {
            throw new DukeException("Eh I don't understand :( "
                    + "You sure you type the deadline format correctly?");
        }
    }

    private static Command parseEvent(String userInput) throws DukeException {
        try {
            String arguments = userInput.trim().split(" ", 2)[1];
            String event = arguments.split(" /at ")[0];
            String at = arguments.split(" /at ")[1];
            Task task = new Event(event, at);
            Set<String> eventCommand = new HashSet<>();
            eventCommand.add(event);
            return new AddCommand(task, eventCommand);
        } catch (Exception e) {
            throw new DukeException("Hmm seems like your event format is wrong?");
        }
    }

    private static Command parseDeleteTask(String userInput) throws DukeException {
        try {
            String arguments = userInput.trim().split(" ", 2)[1];
            int taskNumber = Integer.parseInt(arguments);
            return new DeleteCommand(taskNumber - 1);
        } catch (Exception e) {
            throw new DukeException("What is it you want to delete?");
        }
    }

    private static Command parseMarkTask(String userInput) throws DukeException {
        try {
            String arguments = userInput.trim().split(" ", 2)[1];
            int taskNumber = Integer.parseInt(arguments);
            return new MarkCommand(taskNumber - 1);
        } catch (Exception e) {
            throw new DukeException("Mark what? Definitely not Mark NCT right? HAHA!");
        }
    }

    private static Command parseUnmarkTask(String userInput) throws DukeException {
        try {
            String arguments = userInput.trim().split(" ", 2)[1];
            int taskNumber = Integer.parseInt(arguments);
            return new UnmarkCommand(taskNumber - 1);
        } catch (Exception e) {
            throw new DukeException("Unmark what? Still not sure no need to unmark ;)");
        }
    }

    private static Command parseFindTask(String userInput) throws DukeException {
        try {
            String arguments = userInput.trim().split(" ", 2)[1];
            Set<String> keywords = new HashSet<>();
            keywords.add(arguments);
            return new FindCommand(keywords);
        } catch (Exception e) {
            throw new DukeException("Cannot find if you don't tell me what to find :'(");
        }
    }
}
