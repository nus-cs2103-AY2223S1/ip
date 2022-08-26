package duke.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.commands.*;
import duke.common.Messages;

import duke.exceptions.EmptyBodyException;
import duke.exceptions.InvalidInputException;
import duke.ui.Ui;

/**
 * Parses user input.
 */
public class Parser {

    // commandWord arguments (. - matches any character, * - zero or more times)
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final Pattern DATE_FORMAT = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
    private static final Pattern NUMBER_FORMAT = Pattern.compile("\\d+");

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
        case FindCommand.COMMAND_WORD:
            return prepareFindTask();
        default:
            return new InvalidCommand(Messages.MESSAGE_INVALID_COMMAND);
        }
    }

    private Command prepareTodoTask() {
        try {
            System.out.println(Messages.MESSAGE_TASK_DESCRIPTION);
            final String description = Ui.readNextLine();
            if (description.equals("")) {
                throw new EmptyBodyException();
            }
            return new TodoCommand(description);
        } catch (EmptyBodyException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    private Command prepareEventTask() {
        try {
            System.out.println(Messages.MESSAGE_TASK_DESCRIPTION);
            final String description = Ui.readNextLine();
            if (description.equals("")) {
                throw new EmptyBodyException();
            }
            System.out.println(Messages.MESSAGE_EVENT);
            final String eventDate = Ui.readNextLine();
            if (!eventDate.matches(String.valueOf(DATE_FORMAT))) {
                throw new InvalidInputException(Messages.MESSAGE_INVALID_DATE_INPUT);
            }
            return new EventCommand(description, eventDate);
        } catch (EmptyBodyException | InvalidInputException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    private Command prepareDeadlineTask() {
        try {
            System.out.println(Messages.MESSAGE_TASK_DESCRIPTION);
            final String description = Ui.readNextLine();
            if (description.equals("")) {
                throw new EmptyBodyException();
            }
            System.out.println(Messages.MESSAGE_DEADLINE);
            final String deadline = Ui.readNextLine();
            if (!deadline.matches(String.valueOf(DATE_FORMAT))) {
                throw new InvalidInputException(Messages.MESSAGE_INVALID_DATE_INPUT);
            }
            return new DeadlineCommand(description, deadline);
        } catch (EmptyBodyException | InvalidInputException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    private Command prepareMark() {
        try {
            System.out.println(Messages.MESSAGE_MARK_TASK);
            final String taskNumber = Ui.readNextLine();
            if (!taskNumber.matches(String.valueOf(NUMBER_FORMAT))) {
                throw new InvalidInputException(Messages.MESSAGE_INVALID_TASK_NUMBER);
            }
            return new MarkCommand(Integer.parseInt(taskNumber));
        } catch (Exception e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    private Command prepareFindTask() {
        try {
            System.out.println(Messages.MESSAGE_FIND_TASK);
            final String taskSubstring = Ui.readNextLine();
            if (taskSubstring.equals("")) {
                throw new EmptyBodyException();
            }
            return new FindCommand(taskSubstring);
        } catch (EmptyBodyException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    private Command prepareDelete() {
        try {
            System.out.println(Messages.MESSAGE_TASK_REMOVE);
            final String taskNumber = Ui.readNextLine();
            if (!taskNumber.matches(String.valueOf(NUMBER_FORMAT))) {
                throw new InvalidInputException(Messages.MESSAGE_INVALID_TASK_NUMBER);
            }
            return new DeleteCommand(Integer.parseInt(taskNumber));
        } catch (Exception e) {
            return new InvalidCommand(e.getMessage());
        }
    }
}
