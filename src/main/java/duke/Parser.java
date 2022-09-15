package duke;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.PostponeCommand;
import duke.command.QuackCommand;
import duke.command.UnmarkCommand;
import duke.constants.Constants;
import duke.constants.ErrorMessages;
import duke.models.Deadline;
import duke.models.Event;
import duke.models.Note;
import duke.models.Task;
import duke.models.Todo;


/**
 * Parses the command based on the user input.
 */
public class Parser {

    /**
     * Initialises a Parser object
     */
    public Parser() {
    }

    /**
     * Takes the input given by the user, extracts the verb
     * and translates it into a command
     *
     * @param command
     * @return Command object corresponding to the specified command
     */
    public static Command parse(String command) throws DukeException {
        assert command != null : "Command cannot be null";
        // Idea from : https://stackoverflow.com/questions/70683058/using-startswith-in-switch-case-in-java
        String verb = command.split(Constants.EMPTY_SPACE)[0];
        switch (verb) {
        case Constants.EVENT_STRING:
            Pattern eventPattern = Pattern.compile("(?<=event )(.*)(?= /at)", Pattern.CASE_INSENSITIVE);
            Matcher eventMatcher = eventPattern.matcher(command);
            eventMatcher.find();
            Pattern eventDatePattern = Pattern.compile("(?<=/at ).+", Pattern.CASE_INSENSITIVE);
            Matcher eventDateMatcher = eventDatePattern.matcher(command);
            eventDateMatcher.find();
            try {
                Task event = new Event(eventMatcher.group(1),
                        DateParser.parseDate(eventDateMatcher.group(0)));
                System.out.println(event.getSymbol()

                );
                return new AddCommand(event);
            } catch (Exception e) {
                throw new DukeException(ErrorMessages.INVALID_DATE_MESSAGE);
            }
        case Constants.TODO_STRING:
            try {
                Pattern pattern = Pattern.compile("(?<=todo ).+", Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(command);
                matcher.find();
                Task todo = new Todo(matcher.group(0));
                return new AddCommand(todo);
            } catch (Exception e) {
                throw new DukeException(ErrorMessages.INVALID_TODO_MESSAGE);
            }
        case Constants.DEADLINE_STRING:
            Pattern deadlinePattern = Pattern.compile("(?<=deadline )(.*)(?= /by)", Pattern.CASE_INSENSITIVE);
            Matcher deadlineMatcher = deadlinePattern.matcher(command);
            deadlineMatcher.find();
            Pattern deadlineDatePattern = Pattern.compile("(?<=/by ).+", Pattern.CASE_INSENSITIVE);
            Matcher deadlineDateMatcher = deadlineDatePattern.matcher(command);
            deadlineDateMatcher.find();
            try {
                Task deadline = new Deadline(deadlineMatcher.group(1),
                        DateParser.parseDate(deadlineDateMatcher.group(0)));
                return new AddCommand(deadline);
            } catch (Exception e) {
                throw new DukeException(ErrorMessages.INVALID_DATE_MESSAGE);
            }
        case Constants.DELETE_STRING:
            try {
                int deleteIndex = Integer.parseInt(command.split(Constants.EMPTY_SPACE)[1]);
                if (deleteIndex < 1) {
                    throw new DukeException(ErrorMessages.INVALID_DELETE_MESSAGE);
                }
                return new DeleteCommand(deleteIndex);
            } catch (Exception e) {
                return new InvalidCommand(ErrorMessages.INVALID_DELETE_MESSAGE);
            }
        case Constants.LIST_STRING:
            return new ListCommand();
        case Constants.MARK_STRING:
            try {
                int markIndex = Integer.parseInt(command.split(Constants.EMPTY_SPACE)[1]);
                return new MarkCommand(markIndex);
            } catch (Exception e) {
                throw new DukeException(ErrorMessages.INVALID_MARK_MESSAGE);
            }
        case Constants.UNMARK_STRING:
            int unmarkIndex = Integer.parseInt(command.split(Constants.EMPTY_SPACE)[1]);
            return new UnmarkCommand(unmarkIndex);
        case Constants.FIND_STRING:
            String query = command.split(Constants.EMPTY_SPACE)[0];
            return new FindCommand(query);
        case Constants.QUACK_STRING:
            return new QuackCommand();
        case Constants.POSTPONE:
            int postponeIndex = Integer.parseInt(command.split(Constants.EMPTY_SPACE)[1]);
            return new PostponeCommand(postponeIndex);
        case Constants.HELP_STRING:
            return new HelpCommand();
        case Constants.NOTE:
            System.out.println("hello");
            Pattern notePattern = Pattern.compile("(?<=note ).+", Pattern.CASE_INSENSITIVE);
            Matcher noteMatcher = notePattern.matcher(command);
            noteMatcher.find();
            Note note = new Note(noteMatcher.group(0));
            return new AddCommand(note);
        case Constants.BYE_STRING:
            return new ByeCommand();
        default:
            return new InvalidCommand(ErrorMessages.INVALID_COMMAND_MESSAGE);
        }
    }
}
