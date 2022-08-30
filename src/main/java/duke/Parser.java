package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * Represents a parser.
 */
public class Parser {

    private static final String EXIT_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String DELETE_COMMAND = "delete";
    private static final String FIND_COMMAND = "find";

    /**
     * Parses user's input to be a recognisable command.
     *
     * @param response User's input.
     * @return command.
     * @throws DukeException if User's input is in the wrong format.
     */
    protected static Command parse(String response) throws DukeException {
        String[] cmdDescp = response.split(" ");
        String command = cmdDescp[0];
        if (command.equals(EXIT_COMMAND) || command.equals(LIST_COMMAND)) {
            switch (command) {
            case EXIT_COMMAND:
                return new ExitCommand();
                //Fallthrough
            case LIST_COMMAND:
                return new ListCommand();
                //Fallthrough
            default:
                break;
            }
        } else if (command.equals(FIND_COMMAND)) {
            if (cmdDescp.length < 2) {
                throw new DukeException("Keyword is missing");
            }
            String keyword = String.join(" ", Arrays.copyOfRange(cmdDescp, 1, cmdDescp.length));
            return new FindCommand(keyword);
        } else if (command.equals(MARK_COMMAND) || command.equals(UNMARK_COMMAND) || command.equals(DELETE_COMMAND)) {
            if (cmdDescp.length < 2) {
                throw new DukeException("Index is missing");
            }
            int ind;
            try {
                ind = Integer.parseInt(cmdDescp[1]);
            } catch (NumberFormatException e) {
                throw new DukeException("Index given must be a number");
            }
            switch (command) {
            case MARK_COMMAND:
                return new MarkCommand(ind, true);
                //Fallthrough
            case UNMARK_COMMAND:
                return new MarkCommand(ind, false);
                //Fallthrough
            case DELETE_COMMAND:
                return new DeleteCommand(ind);
                //Fallthrough
            default:
                break;
            }
        } else if (command.equals(TODO_COMMAND)) {
            if (cmdDescp.length < 2) {
                throw new DukeException("Task description is missing");
            }
            String taskDescription = String.join(" ", Arrays.copyOfRange(cmdDescp, 1, cmdDescp.length));
            return new AddCommand(new Todo(taskDescription));
        } else if (command.equals(DEADLINE_COMMAND) || command.equals(EVENT_COMMAND)) {
            String[] splitSlash = response.split("/");
            if (splitSlash.length < 2) {
                switch (command) {
                case DEADLINE_COMMAND:
                    throw new DukeException("/by is missing");
                    //Fallthrough
                case EVENT_COMMAND:
                    throw new DukeException("/at is missing");
                    //Fallthrough
                default:
                    break;
                }
            }
            String[] details = splitSlash[1].split(" ");
            String action = details[0];
            switch (command) {
            case DEADLINE_COMMAND:
                if (!action.equals("by")) {
                    throw new DukeException("/by is missing");
                }
                break;
            case EVENT_COMMAND:
                if (!action.equals("at")) {
                    throw new DukeException("/at is missing");
                }
                break;
            default:
                break;
            }
            if (details.length < 2) {
                throw new DukeException("date in yyyy-mm-dd format is missing");
            }
            LocalDate date;
            try {
                String time = details[1];
                date = LocalDate.parse(time);
            } catch (DateTimeParseException e) {
                throw new DukeException("date not in yyyy-mm-dd format");
            }
            cmdDescp = splitSlash[0].split(" ");
            if (cmdDescp.length < 2) {
                throw new DukeException("Task description is missing");
            }
            String taskDescription = String.join(" ", Arrays.copyOfRange(cmdDescp, 1, cmdDescp.length));
            switch (command) {
            case DEADLINE_COMMAND:
                return new AddCommand(new Deadline(taskDescription, date));
                //Fallthrough
            case EVENT_COMMAND:
                return new AddCommand(new Event(taskDescription, date));
                //Fallthrough
            default:
                break;
            }
        }
        throw new DukeException("Invalid command given");
    }
}
