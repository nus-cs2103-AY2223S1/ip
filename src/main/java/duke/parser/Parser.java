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
            arguments = userInput.trim().split(" ", 2)[1];
            task = new ToDo(arguments);
            return new AddCommand(task);

        case AddCommand.ADD_DEADLINE:
            arguments = userInput.trim().split(" ", 2)[1];
            String deadline = arguments.split(" /by ")[0];
            String by = arguments.split(" /by ")[1];
            LocalDate date = LocalDate.parse(by);
            task = new Deadline(deadline, date);
            return new AddCommand(task);

        case AddCommand.ADD_EVENT:
            arguments = userInput.trim().split(" ", 2)[1];
            String event = arguments.split(" /at ")[0];
            String at = arguments.split(" /at ")[1];
            task = new Event(event, at);
            return new AddCommand(task);

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

        default:
            return new ListCommand();
        }
    }
}
