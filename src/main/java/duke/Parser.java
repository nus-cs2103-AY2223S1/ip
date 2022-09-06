package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.FriendlyCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnfriendlyCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents a parser.
 */
public class Parser {

    public static final String EXIT_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String DELETE_COMMAND = "delete";
    private static final String FIND_COMMAND = "find";
    private static final String FRIENDLY_COMMAND = "friendly";
    private static final String UNFRIENDLY_COMMAND = "unfriendly";
    private static final String[] ARRAY_ORIGINAL_COMMANDS = new String[]{EXIT_COMMAND, LIST_COMMAND, MARK_COMMAND,
            UNMARK_COMMAND, TODO_COMMAND, DEADLINE_COMMAND, EVENT_COMMAND, DELETE_COMMAND, FIND_COMMAND,
            FRIENDLY_COMMAND, UNFRIENDLY_COMMAND};
    private static final ArrayList<String> LIST_ORIGINAL_COMMANDS = new ArrayList<>(Arrays.
            asList(ARRAY_ORIGINAL_COMMANDS));
    private final ArrayList<String> newCommands;
    private final HashMap<String, String> commands;

    private static final String MISSING_KEYWORD_MESSAGE = "Keyword is missing";
    private static final String MISSING_INDEX_MESSAGE = "Index is missing";
    private static final String MISSING_TASK_DESCRIPTION_MESSAGE = "Task description is missing";
    private static final String MISSING_DEADLINE_CONNECTOR_MESSAGE = "/" + Deadline.CONNECTOR + " is missing";
    private static final String MISSING_EVENT_CONNECTOR_MESSAGE = "/" + Event.CONNECTOR + " is missing";
    private static final String MISSING_DATE_MESSAGE = "date in " + Task.SAVE_DATE_FORMAT + " format is missing";
    private static final String INVALID_DATE_FORMAT = "date not in " + Task.SAVE_DATE_FORMAT + " format";
    private static final String INDEX_NOT_NUMBER_MESSAGE = "Index given must be a number";
    private static final String INVALID_COMMAND_MESSAGE = "Invalid command given";
    private static final String ALIAS_EXIST_MESSAGE = "Alias already exist";
    private static final String INVALID_ORIGINAL_COMMAND_MESSAGE = "Invalid original command given";
    private static final String ALIAS_DOES_NOT_EXIST_MESSAGE = "Alias does not exist";

    Parser() {
        this.newCommands = new ArrayList<>();
        this.commands = new HashMap<>();
        for (String command : LIST_ORIGINAL_COMMANDS) {
            this.commands.put(command, command);
        }
    }



    /**
     * Parses user's input to be a recognisable command.
     *
     * @param response User's input.
     * @return command.
     * @throws DukeException if User's input is in the wrong format.
     */
    protected Command parse(String response) throws DukeException {
        String[] cmdDescp = response.split(" ");
        String commandInput = cmdDescp[0];
        if (!this.commands.containsKey(commandInput)) {
            throw new DukeException(INVALID_COMMAND_MESSAGE);
        }
        String command = this.commands.get(commandInput);
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
                throw new DukeException(MISSING_KEYWORD_MESSAGE);
            }
            String keyword = String.join(" ", Arrays.copyOfRange(cmdDescp, 1, cmdDescp.length));
            return new FindCommand(keyword);
        } else if (command.equals(MARK_COMMAND) || command.equals(UNMARK_COMMAND) || command.equals(DELETE_COMMAND)) {
            if (cmdDescp.length < 2) {
                throw new DukeException(MISSING_INDEX_MESSAGE);
            }
            int ind;
            try {
                ind = Integer.parseInt(cmdDescp[1]);
            } catch (NumberFormatException e) {
                throw new DukeException(INDEX_NOT_NUMBER_MESSAGE);
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
                throw new DukeException(MISSING_TASK_DESCRIPTION_MESSAGE);
            }
            String taskDescription = String.join(" ", Arrays.copyOfRange(cmdDescp, 1, cmdDescp.length));
            return new AddCommand(new Todo(taskDescription));
        } else if (command.equals(DEADLINE_COMMAND) || command.equals(EVENT_COMMAND)) {
            String[] splitSlash = response.split("/");
            if (splitSlash.length < 2) {
                switch (command) {
                case DEADLINE_COMMAND:
                    throw new DukeException(MISSING_DEADLINE_CONNECTOR_MESSAGE);
                    //Fallthrough
                case EVENT_COMMAND:
                    throw new DukeException(MISSING_EVENT_CONNECTOR_MESSAGE);
                    //Fallthrough
                default:
                    break;
                }
            }
            String[] details = splitSlash[1].split(" ");
            String action = details[0];
            switch (command) {
            case DEADLINE_COMMAND:
                if (!action.equals(Deadline.CONNECTOR)) {
                    throw new DukeException(MISSING_DEADLINE_CONNECTOR_MESSAGE);
                }
                break;
            case EVENT_COMMAND:
                if (!action.equals(Event.CONNECTOR)) {
                    throw new DukeException(MISSING_EVENT_CONNECTOR_MESSAGE);
                }
                break;
            default:
                break;
            }
            if (details.length < 2) {
                throw new DukeException(MISSING_DATE_MESSAGE);
            }
            LocalDate date;
            try {
                String time = details[1];
                date = LocalDate.parse(time);
            } catch (DateTimeParseException e) {
                throw new DukeException(INVALID_DATE_FORMAT);
            }
            cmdDescp = splitSlash[0].split(" ");
            if (cmdDescp.length < 2) {
                throw new DukeException(MISSING_TASK_DESCRIPTION_MESSAGE);
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
        } else if (command.equals(FRIENDLY_COMMAND)) {
            String originalCommand = cmdDescp[1];
            String alias = cmdDescp[2];
            if (!LIST_ORIGINAL_COMMANDS.contains(originalCommand)) {
                throw new DukeException(INVALID_ORIGINAL_COMMAND_MESSAGE);
            }
            if (LIST_ORIGINAL_COMMANDS.contains(alias) || this.newCommands.contains(alias)) {
                throw new DukeException(ALIAS_EXIST_MESSAGE);
            }
            return new FriendlyCommand(alias, originalCommand);
        } else if (command.equals(UNFRIENDLY_COMMAND)) {
            String alias = cmdDescp[1];
            if (!this.newCommands.contains(alias)) {
                throw new DukeException(ALIAS_DOES_NOT_EXIST_MESSAGE);
            }
            return new UnfriendlyCommand(alias);
        }
        throw new DukeException(INVALID_COMMAND_MESSAGE);
    }

    public void addAlias(String alias, String originalCommand) {
        this.newCommands.add(alias);
        this.commands.put(alias, originalCommand);
    }

    public void deleteAlias(String alias) {
        this.newCommands.remove(alias);
        this.commands.remove(alias);
    }
}
