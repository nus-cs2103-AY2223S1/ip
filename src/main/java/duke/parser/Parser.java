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
        String arguments;
        Task task;
        int taskNumber;

        switch (commandWord) {

        case AddCommand.ADD_TODO:
            return parseToDo(userInput);

        case AddCommand.ADD_DEADLINE:
            arguments = userInput.trim().split(" ", 2)[1];
            String deadline = arguments.split(" /by ")[0];
            String by = arguments.split(" /by ")[1];
            LocalDate date = LocalDate.parse(by);
            task = new Deadline(deadline, date);
            Set<String> deadlineCommand = new HashSet<>();
            deadlineCommand.add(deadline);
            return new AddCommand(task, deadlineCommand);

        case AddCommand.ADD_EVENT:
            arguments = userInput.trim().split(" ", 2)[1];
            String event = arguments.split(" /at ")[0];
            String at = arguments.split(" /at ")[1];
            task = new Event(event, at);
            Set<String> eventCommand = new HashSet<>();
            eventCommand.add(event);
            return new AddCommand(task, eventCommand);

        case DeleteCommand.DELETE_COMMAND:
            arguments = userInput.trim().split(" ", 2)[1];
            taskNumber = Integer.parseInt(arguments);
            return new DeleteCommand(taskNumber - 1);

        case MarkCommand.MARK_COMMAND:
            arguments = userInput.trim().split(" ", 2)[1];
            taskNumber = Integer.parseInt(arguments);
            return new MarkCommand(taskNumber - 1);

        case UnmarkCommand.UNMARK_COMMAND:
            arguments = userInput.trim().split(" ", 2)[1];
            taskNumber = Integer.parseInt(arguments);
            return new UnmarkCommand(taskNumber - 1);

        case FindCommand.FIND_COMMAND:
            arguments = userInput.trim().split(" ", 2)[1];
            Set<String> keywords = new HashSet<>();
            keywords.add(arguments);
            return new FindCommand(keywords);

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
            throw new DukeException("Need more description! Do what??");
        }
    }
}
