package duke.parser;

import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidCommandException;
import duke.tasks.TaskList;



public class CommandParser {
    enum Command {
        LIST,
        DELETE,
        DONE,
        UNDONE,
        BEFORE,
        FIND,
        TODO,
        EVENT,
        DEADLINE
    }

    private static final Pattern COMMAND_PATTERN =
            Pattern.compile("^([a-zA-Z]+)(?: ([^/]*))?(?: /([a-zA-Z]+))?(?: (.*))?$");
    private final TaskList taskList;

    /**
     * Constructor specifying tasklist to parse commands into.
     *
     * @param tasks tasklist to store tasks in
     */
    public CommandParser(TaskList tasks) {
        this.taskList = tasks;
    }

    private Command generator(String action) throws InvalidCommandException {
        try {
            return Command.valueOf(action.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException();
        }
    }

    /**
     * Parses user commands.
     *
     * @param command command input by user
     */
    public String handle(String command) {
        String response;

        try {
            // Identify groups based on command pattern
            Matcher matcher = COMMAND_PATTERN.matcher(command);
            matcher.find();
            String action = matcher.group(1);
            String desc = matcher.group(2);
            String time = matcher.group(4);
            Command cmd = generator(action);

            // Call methods according to command
            switch (cmd) {
            case LIST:
                response = taskList.printList();
                break;
            case DONE:
                response = taskList.markDone(desc);
                break;
            case UNDONE:
                response = taskList.markUndone(desc);
                break;
            case BEFORE:
                response = taskList.printDeadline(time);
                break;
            case FIND:
                response = taskList.find(desc);
                break;
            case DELETE:
                response = taskList.delete(desc);
                break;
            case TODO:
                response = taskList.addToDo(desc);
                break;
            case EVENT:
                response = taskList.addEvent(desc, time);
                break;
            case DEADLINE:
                response = taskList.addDeadline(desc, time);
                break;
            default:
                throw new InvalidCommandException();
            }
        } catch (DukeException e) {
            return e.toString();
        } catch (DateTimeParseException e) {
            return "Please enter the correct due date format d/mm/yyyy [HHmm]";
        }
        return response;
    }
}
