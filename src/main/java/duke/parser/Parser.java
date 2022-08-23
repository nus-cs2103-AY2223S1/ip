package duke.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.EventCommand;
import duke.commands.ExitCommand;
import duke.commands.HelpCommand;
import duke.commands.InvalidCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.TodoCommand;
import duke.common.Messages;
import duke.ui.Ui;

public class Parser {

    // commandWord arguments (. - matches any character, * - zero or more times)
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     */
    public Command parseCommand(String userInput) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            return new InvalidCommand(Messages.MESSAGE_INVALID_COMMAND);
        }

        final String command = matcher.group("commandWord").toLowerCase();

        switch (command) {
            case HelpCommand.COMMAND_WORD:
                return new HelpCommand();
            case TodoCommand.COMMAND_WORD:
                return prepareTodoTask();
            case DeadlineCommand.COMMAND_WORD:
                return prepareDeadlineTask();
            case EventCommand.COMMAND_WORD:
                return prepareEventTask();
            case ListCommand.COMMAND_WORD:
                return new ListCommand();
            case MarkCommand.COMMAND_WORD:
                return prepareMark();
            case DeleteCommand.COMMAND_WORD:
                return prepareDelete();
            case ExitCommand.COMMAND_WORD:
                return new ExitCommand();
            default:
                return new InvalidCommand(Messages.MESSAGE_INVALID_COMMAND);
        }
    }

    private Command prepareTodoTask() {
        try {
            System.out.println(Messages.MESSAGE_TASK_DESCRIPTION);
            final String description = Ui.readNextLine();
            return new TodoCommand(description);
        } catch (Exception e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    private Command prepareEventTask() {
        try {
            System.out.println(Messages.MESSAGE_TASK_DESCRIPTION);
            final String description = Ui.readNextLine();
            System.out.println(Messages.MESSAGE_EVENT);
            final String eventDate = Ui.readNextLine();
            return new EventCommand(description, eventDate);
        } catch (Exception e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    private Command prepareDeadlineTask() {
        try {
            System.out.println(Messages.MESSAGE_TASK_DESCRIPTION);
            final String description = Ui.readNextLine();
            System.out.println(Messages.MESSAGE_DEADLINE);
            final String deadline = Ui.readNextLine();
            return new DeadlineCommand(description, deadline);
        } catch (Exception e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    private Command prepareMark() {
        try {
            System.out.println(Messages.MESSAGE_MARK_TASK);
            final int taskNumber = Ui.readNextInt();
            return new MarkCommand(taskNumber);
        } catch (Exception e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    private Command prepareDelete() {
        try {
            System.out.println(Messages.MESSAGE_TASK_REMOVE);
            final int taskNumber = Ui.readNextInt();
            return new DeleteCommand(taskNumber);
        } catch (Exception e) {
            return new InvalidCommand(e.getMessage());
        }
    }
}
