package duke;

import java.time.LocalDate;
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
import duke.models.Deadline;
import duke.models.Event;
import duke.models.Task;
import duke.models.Todo;


/**
 * This class returns a command based on the user input.
 */
public class Parser {
    private TaskList taskList;

    /**
     * Initialise a Parser object
     * @param tasks
     */
    public Parser(TaskList tasks) {
        this.taskList = tasks;
    }

    /**
     * Requirement: deals with making sense of the user command
     *
     * Takes the input given by the user, extracts the verb
     * and translates it into a command
     *
     * @param command
     * @return Command object corresponding to the specified command
     */
    public static Command parse(String command) {
        assert command != null : "Command cannot be null";
        // Idea from : https://stackoverflow.com/questions/70683058/using-startswith-in-switch-case-in-java
        String verb = command.split(Constants.EMPTY_SPACE)[0];
        switch (verb) {
        case Constants.EVENT_STRING:
            String eventDescription = command.split(Constants.EMPTY_SPACE)[1];
            LocalDate eventDate = LocalDate.parse(command.split(Constants.EMPTY_SPACE)[3]);
            Task event = new Event(eventDescription, eventDate);
            return new AddCommand(event);
        case Constants.TODO_STRING:
            Pattern pattern = Pattern.compile("(?<=todo ).+", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(command);
            matcher.find();
            Task todo = new Todo(matcher.group(0));
            return new AddCommand(todo);
        case Constants.DEADLINE_STRING:
//            Pattern pattern = Pattern.compile("(?<=deadline)(.*)(?=/by)", Pattern.CASE_INSENSITIVE);
//            Matcher matcher = pattern.matcher(command);
//            matcher.find();
//            System.out.println(matcher.group(1));
            String deadlineDescription = command.split(Constants.EMPTY_SPACE)[1];
            String dateString = command.split(Constants.EMPTY_SPACE)[3];
            LocalDate parsedDate = DateParser.parseDate(dateString);
            Task deadline = new Deadline(deadlineDescription, parsedDate);
            return new AddCommand(deadline);
        case Constants.DELETE_STRING:
            int deleteIndex = Integer.parseInt(command.split(Constants.EMPTY_SPACE)[1]);
            return new DeleteCommand(deleteIndex);
        case Constants.LIST_STRING:
            return new ListCommand();
        case Constants.MARK_STRING:
            int markIndex = Integer.parseInt(command.split(Constants.EMPTY_SPACE)[1]);
            return new MarkCommand(markIndex);
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
        case Constants.BYE_STRING:
            return new ByeCommand();
        default:
            return new InvalidCommand();
        }
    }
}
