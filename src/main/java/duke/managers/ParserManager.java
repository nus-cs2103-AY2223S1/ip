package duke.managers;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.commands.AddDeadlineTaskCommand;
import duke.commands.AddEventTaskCommand;
import duke.commands.AddToDoTaskCommand;
import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeleteTaskCommand;
import duke.commands.FindTaskCommand;
import duke.commands.IncorrectCommand;
import duke.commands.ListTasksCommand;
import duke.commands.MarkTaskCommand;
import duke.commands.UnmarkTaskCommand;

/**
 * Encapsulates the logic for parsing commands and delegating the corresponding {@link Command} handler responsible for
 * handling the command.
 *
 * @author Emily Ong Hui Qi
 */
public class ParserManager {
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)?");

    /**
     * Returns the map of all commands and their descriptions in the application.
     *
     * @return Map of all commands in the application.
     */
    public static Map<String, String> getAllCommands() {
        return new LinkedHashMap<>() {
            {
                put(AddDeadlineTaskCommand.COMMAND_WORD, AddDeadlineTaskCommand.COMMAND_DESCRIPTION);
            }

            {
                put(AddEventTaskCommand.COMMAND_WORD, AddEventTaskCommand.COMMAND_DESCRIPTION);
            }

            {
                put(AddToDoTaskCommand.COMMAND_WORD, AddToDoTaskCommand.COMMAND_DESCRIPTION);
            }

            {
                put(MarkTaskCommand.COMMAND_WORD, MarkTaskCommand.COMMAND_DESCRIPTION);
            }

            {
                put(UnmarkTaskCommand.COMMAND_WORD, UnmarkTaskCommand.COMMAND_DESCRIPTION);
            }

            {
                put(DeleteTaskCommand.COMMAND_WORD, DeleteTaskCommand.COMMAND_DESCRIPTION);
            }

            {
                put(FindTaskCommand.COMMAND_WORD, FindTaskCommand.COMMAND_DESCRIPTION);
            }

            {
                put(ListTasksCommand.COMMAND_WORD, ListTasksCommand.COMMAND_DESCRIPTION);
            }

            {
                put(ByeCommand.COMMAND_WORD, ByeCommand.COMMAND_DESCRIPTION);
            }
        };
    }

    /**
     * Parses the given command string and returns a {@link Command} instance that corresponds to the command string.
     *
     * @param fullCommand The full command string supplied by the user from keyboard input.
     *
     * @return The {@link Command} instance responsible for handling the corresponding command.
     */
    public Command parseCommand(String fullCommand) {
        Matcher matcher = ParserManager.BASIC_COMMAND_FORMAT.matcher(fullCommand);
        if (!matcher.matches()) {
            return new IncorrectCommand();
        }
        String command = matcher.group("commandWord").strip();
        String arguments = matcher.group("arguments").strip();
        switch (command) {
        // List all tasks in the task manager
        case ListTasksCommand.COMMAND_WORD:
            return new ListTasksCommand(arguments);
        case FindTaskCommand.COMMAND_WORD:
            return new FindTaskCommand(arguments.split(" "));

        // Modifying operations on a task
        case MarkTaskCommand.COMMAND_WORD:
            return new MarkTaskCommand(arguments);
        case UnmarkTaskCommand.COMMAND_WORD:
            return new UnmarkTaskCommand(arguments);
        case DeleteTaskCommand.COMMAND_WORD:
            return new DeleteTaskCommand(arguments);

        // Creating operations on a task
        case AddToDoTaskCommand.COMMAND_WORD:
            return new AddToDoTaskCommand(arguments);
        case AddDeadlineTaskCommand.COMMAND_WORD:
            return new AddDeadlineTaskCommand(arguments);
        case AddEventTaskCommand.COMMAND_WORD:
            return new AddEventTaskCommand(arguments);

        // Termination of program
        case ByeCommand.COMMAND_WORD:
            return new ByeCommand();

        default:
            return new IncorrectCommand();
        }
    }
}
