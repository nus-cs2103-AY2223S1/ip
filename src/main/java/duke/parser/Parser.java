package duke.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.commands.*;
import duke.common.Messages;

import duke.exceptions.EmptyBodyException;
import duke.exceptions.InvalidInputException;

/**
 * Parses user input.
 */
public class Parser {

    // commandWord arguments (. - matches any character, * - zero or more times)
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final Pattern DATE_FORMAT = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
    private static final Pattern NUMBER_FORMAT = Pattern.compile("\\d+");

    /**
     * Parses single user input into command for execution.
     *
     * @param userInput user input string
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
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        default:
            return new InvalidCommand(Messages.MESSAGE_INVALID_COMMAND);
        }
    }

    /**
     * Parses two user inputs into command for execution.
     * Specifically for Todo, Mark, Delete and Find commands.
     *
     * @param userInput command word
     * @param secondInput description or index of task
     * @return the command based on the user input
     */
    public Command parseTwoArgsCommand(String userInput, String secondInput) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            return new InvalidCommand(Messages.MESSAGE_INVALID_COMMAND);
        }

        final String command = matcher.group("commandWord").toLowerCase();

        switch (command) {
        case TodoCommand.COMMAND_WORD:
            return prepareTodoTask(secondInput);
        case MarkCommand.COMMAND_WORD:
            return prepareMark(secondInput);
        case DeleteCommand.COMMAND_WORD:
            return prepareDelete(secondInput);
        case FindCommand.COMMAND_WORD:
            return prepareFindTask(secondInput);
        default:
            return new InvalidCommand(Messages.MESSAGE_INVALID_COMMAND);
        }
    }

    /**
     * Parses three user inputs into command for execution.
     * Specifically for Event and Deadline commands.
     *
     * @param userInput command word
     * @param secondInput description of task
     * @param thirdInput date of task
     * @return the command based on the user input
     */
    public Command parseThreeArgsCommand(String userInput, String secondInput, String thirdInput) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            return new InvalidCommand(Messages.MESSAGE_INVALID_COMMAND);
        }

        final String command = matcher.group("commandWord").toLowerCase();

        switch (command) {
        case EventCommand.COMMAND_WORD:
            return prepareEventTask(secondInput, thirdInput);
        case DeadlineCommand.COMMAND_WORD:
            return prepareDeadlineTask(secondInput, thirdInput);
        default:
            return new InvalidCommand(Messages.MESSAGE_INVALID_COMMAND);
        }
    }

    private Command prepareTodoTask(String taskDescription) {
        try {
            if (taskDescription.equals("")) {
                throw new EmptyBodyException();
            }
            return new TodoCommand(taskDescription);
        } catch (EmptyBodyException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    private Command prepareEventTask(String taskDescription, String taskDate) {
        try {
            if (taskDescription.equals("")) {
                throw new EmptyBodyException();
            }
            if (!taskDate.matches(String.valueOf(DATE_FORMAT))) {
                throw new InvalidInputException(Messages.MESSAGE_INVALID_DATE_INPUT);
            }
            return new EventCommand(taskDescription, taskDate);
        } catch (EmptyBodyException | InvalidInputException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    private Command prepareDeadlineTask(String taskDescription, String taskDate) {
        try {
            if (taskDescription.equals("")) {
                throw new EmptyBodyException();
            }

            if (!taskDate.matches(String.valueOf(DATE_FORMAT))) {
                throw new InvalidInputException(Messages.MESSAGE_INVALID_DATE_INPUT);
            }

            return new DeadlineCommand(taskDescription, taskDate);
        } catch (EmptyBodyException | InvalidInputException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    private Command prepareMark(String taskIndex) {
        try {
            if (!taskIndex.matches(String.valueOf(NUMBER_FORMAT))) {
                throw new InvalidInputException(Messages.MESSAGE_INVALID_TASK_NUMBER);
            }
            return new MarkCommand(Integer.parseInt(taskIndex));
        } catch (Exception e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    private Command prepareFindTask(String taskSubstring) {
        try {
            if (taskSubstring.equals("")) {
                throw new EmptyBodyException();
            }
            return new FindCommand(taskSubstring);
        } catch (EmptyBodyException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    private Command prepareDelete(String taskIndex) {
        try {
            if (!taskIndex.matches(String.valueOf(NUMBER_FORMAT))) {
                throw new InvalidInputException(Messages.MESSAGE_INVALID_TASK_NUMBER);
            }
            return new DeleteCommand(Integer.parseInt(taskIndex));
        } catch (Exception e) {
            return new InvalidCommand(e.getMessage());
        }
    }
}
