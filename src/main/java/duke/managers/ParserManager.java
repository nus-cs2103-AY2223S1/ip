package duke.managers;

import duke.commands.AddDeadlineTaskCommand;
import duke.commands.AddEventTaskCommand;
import duke.commands.AddToDoTaskCommand;
import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeleteTaskCommand;
import duke.commands.IncorrectCommand;
import duke.commands.ListTasksCommand;
import duke.commands.MarkTaskCommand;
import duke.commands.UnmarkTaskCommand;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserManager {
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)?");

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
