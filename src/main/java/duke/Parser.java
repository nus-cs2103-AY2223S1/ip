package duke;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.EventCommand;
import duke.commands.HelpCommand;
import duke.commands.InvalidCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.TodoCommand;
import duke.commands.UnmarkCommand;
import duke.common.Messages;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

	// commandWord arguments (. - matches any character, * - zero or more times)
	private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
	// any character, one or more times
	private static final Pattern TASK_INDEX_ARGS_FORMAT = Pattern.compile("(?<targetIndex>.+)");
	private static final Pattern DESCRIPTION_FORMAT = Pattern.compile("(?<description>.+)");
	private static final Pattern DEADLINE_FORMAT = Pattern.compile("(?<description>.+\\S+)/by\\S+(?<dateTime>.+)");
	private static final Pattern EVENT_FORMAT = Pattern.compile("(?<description>.+\\S+)/at\\S+(?<dateTime>.+)");

	public Command parseCommand(String userInput) {
		final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
		if (!matcher.matches()) {
			return new InvalidCommand(Messages.MESSAGE_INVALID_COMMAND_FORMAT);
		}

		final String command = matcher.group("commandWord").toLowerCase();
		final String arguments = matcher.group("arguments");

		switch (command) {
		case ListCommand.COMMAND_WORD:
			return new ListCommand();
		case ByeCommand.COMMAND_WORD:
			return new ByeCommand();
		case TodoCommand.COMMAND_WORD:
			return prepareTodo(arguments);
		case DeadlineCommand.COMMAND_WORD:
			return prepareDeadline(arguments);
		case EventCommand.COMMAND_WORD:
			return prepareEvent(arguments);
		case MarkCommand.COMMAND_WORD:
			return prepareMark(arguments);
		case UnmarkCommand.COMMAND_WORD:
			return prepareUnmark(arguments);
		case DeleteCommand.COMMAND_WORD:
			return prepareDelete(arguments);
		case HelpCommand.COMMAND_WORD: // Fallthrough
		default:
			return new HelpCommand();
		}
	}

	private Command prepareTodo(String args) {
		try {
			final String description = parseArgsAsDescription(args);
			return new TodoCommand(description);
		} catch (DukeException e) {
			return new InvalidCommand(e.getMessage());
		}
	}

	private Command prepareDeadline(String args) {
		try {
			final String[] desTime = parseArgsAsDescriptionAndDateTime("DEADLINE", args);
			return new DeadlineCommand(desTime[0], desTime[1]);
		} catch (DukeException e) {
			return new InvalidCommand(e.getMessage());
		}
	}

	private Command prepareEvent(String args) {
		try {
			final String[] desTime = parseArgsAsDescriptionAndDateTime("EVENT", args);
			return new EventCommand(desTime[0], desTime[1]);
		} catch (DukeException e) {
			return new InvalidCommand(e.getMessage());
		}
	}

	private Command prepareDelete(String args) {
		return prepareTargetIndex("DELETE", args);
	}

	private Command prepareMark(String args) {
		return prepareTargetIndex("MARK", args);
	}

	private Command prepareUnmark(String args) {
		return prepareTargetIndex("UNMARK", args);
	}

	private Command prepareTargetIndex(String commandWord, String args) {
		try {
			final int targetIndex = parseArgsAsDisplayedIndex(args);
			switch (commandWord.toUpperCase()) {
			case "MARK":
				return new MarkCommand(targetIndex);
			case "UNMARK":
				return new UnmarkCommand(targetIndex);
			case "DELETE":
				return new DeleteCommand(targetIndex);
			}
			return new InvalidCommand(Messages.ERROR_INVALID_ARGUMENTS);
		} catch (DukeException e) {
			return new InvalidCommand(e.getMessage());
		}
	}

	private int parseArgsAsDisplayedIndex(String args) throws DukeException {
		final Matcher matcher = TASK_INDEX_ARGS_FORMAT.matcher(args.trim());
		if (!matcher.matches()) {
			throw new DukeException(Messages.MESSAGE_TASK_NOT_SPECIFIED);
		}

		final int targetIndex;
		try {
			targetIndex = Integer.parseInt(matcher.group("targetIndex"));
		} catch (NumberFormatException e) {
			throw new DukeException(Messages.MESSAGE_TASK_NOT_SPECIFIED);
		}

		return targetIndex;
	}

	private String parseArgsAsDescription(String args) throws DukeException {
		final Matcher matcher = DESCRIPTION_FORMAT.matcher(args.trim());
		if (!matcher.matches()) {
			throw new DukeException(Messages.MESSAGE_EMPTY_DESCRIPTION);
		}
		return args;
	}

	private String[] parseArgsAsDescriptionAndDateTime(String taskType, String args) throws DukeException {
		final Matcher matcher = taskType.equalsIgnoreCase("deadline")
				? DEADLINE_FORMAT.matcher(args.trim())
				: EVENT_FORMAT.matcher(args.trim());
		if (!matcher.matches()) {
			throw new DukeException(Messages.ERROR_EMPTY_DESCRIPTION_TIME);
		}

		String[] descriptionTimeArray = new String[2];
		descriptionTimeArray[0] = matcher.group("description").trim();
		descriptionTimeArray[1] = matcher.group("dateTime").trim();
		return descriptionTimeArray;
	}
}
