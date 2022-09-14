package duke;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import duke.exception.DukeException;
import duke.exception.DukeFormatCommandException;
import duke.exception.DukeIndexErrorException;
import duke.exception.DukeInvalidCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents the main Duke class and runs MakiBot.
 *
 * @author Justin Peng
 */
public class Duke {
    /** Datetime formatter for user input which accepts dd/MM/yyyy or dd/MM/yyyy HH:mm */
    private static final DateTimeFormatter DATE_TIME_FORMATTER = new DateTimeFormatterBuilder()
            .appendPattern("dd/MM/yyyy ")
            .optionalStart()
            .appendPattern("HH:mm ")
            .optionalEnd()
            .appendZoneText(TextStyle.SHORT)
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
            .toFormatter();
    /** Commands accepted by Duke */
    public enum Command {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, SAVE, FIND;

        private static final Set<String> values = new HashSet<>(Command.values().length);

        static {
            for (Command command: Command.values()) {
                values.add(command.name());
            }
        }

        static boolean contains(String value) {
            return values.contains(value);
        }
    }
    /** User timezone */
    private ZoneId timeZone = ZoneId.of("GMT+00:00");

    private final Storage storage;
    private final Ui ui;
    private final Parser parser;
    private TaskList tasks;

    /**
     * Creates a new {@code Duke} object.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        parser = new Parser();
        tasks = new TaskList(storage.loadTasks(timeZone));
    }

    /**
     * Creates a new {@code Duke} object with specified save file.
     *
     * @param saveFile The save file.
     */
    public Duke(String saveFile) {
        ui = new Ui();
        storage = new Storage(saveFile);
        parser = new Parser();
        tasks = new TaskList(storage.loadTasks(timeZone));
    }

    /**
     * Generates an answer to the specified user input.
     *
     * @param input The user input.
     */
    protected String getResponse(String input) {
        try {
            String[] fullCommand = parser.parseFullCommand(input);
            Command command = parser.parseCommand(input);

            // Handle commands
            switch (command) {
            // Exit command
            case BYE:
                storage.updateSaveFile(tasks.getTasks());
                return ui.close();
            case SAVE:
                return storage.updateSaveFile(tasks.getTasks());
            // List all tasks
            case LIST:
                return ui.getAllTasks(tasks.getTasks());
            // Mark task as done
            case MARK:
                return mark(fullCommand);
            // Mark task as undone
            case UNMARK:
                return unmark(fullCommand);
            case DELETE:
                return delete(fullCommand);
            case FIND:
                return find(fullCommand);
            case TODO:
                return newTodo(fullCommand);
            case DEADLINE:
                return newDeadline(fullCommand);
            case EVENT:
                return newEvent(fullCommand);
            default:
                throw new DukeInvalidCommandException();
            }
        } catch (DukeException de) {
            return de.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    /**
     * Marks a task from the task list as done.
     *
     * @param fullCommand The full input from the user containing the task's index.
     * @throws DukeException If the input is invalid.
     */
    protected String mark(String[] fullCommand) throws DukeException {
        if (fullCommand.length < 2 || fullCommand[1].equals("")) {
            throw new DukeFormatCommandException("mark");
        }

        try {
            int taskNum = Integer.parseInt(fullCommand[1]) - 1;
            Task t = tasks.get(taskNum);
            t.markAsDone();
            return "Nice! I've marked this task as done:\n" + t;
        } catch (NumberFormatException e) {
            if (tasks.size() == 0) {
                throw new DukeIndexErrorException();
            } else {
                throw new DukeIndexErrorException(tasks.size());
            }
        }
    }

    /**
     * Marks a task from the task list as undone.
     *
     * @param fullCommand The full input from the user containing the task's index.
     * @throws DukeException If the input is invalid.
     */
    protected String unmark(String[] fullCommand) throws DukeException {
        if (fullCommand.length < 2 || fullCommand[1].equals("")) {
            throw new DukeFormatCommandException("unmark");
        }

        try {
            int taskNum = Integer.parseInt(fullCommand[1]) - 1;
            Task t = tasks.get(taskNum);
            t.markAsUndone();
            return "OK, I've marked this task as not done yet:\n" + t;
        } catch (NumberFormatException e) {
            if (tasks.size() == 0) {
                throw new DukeIndexErrorException();
            } else {
                throw new DukeIndexErrorException(tasks.size());
            }
        }
    }

    /**
     * Deletes a task from the task list.
     *
     * @param fullCommand The full input from the user containing the task's index.
     * @throws DukeException If the input is invalid.
     */
    protected String delete(String[] fullCommand) throws DukeException {
        if (fullCommand.length < 2 || fullCommand[1].equals("")) {
            throw new DukeFormatCommandException("delete");
        }

        try {
            int taskNum = Integer.parseInt(fullCommand[1]) - 1;
            Task t = tasks.remove(taskNum);
            return String.format("Noted. I've removed this task:\n"
                            + "\t%s\n"
                            + "Now you have %d tasks in the list.%n",
                    t, tasks.size());
        } catch (NumberFormatException e) {
            if (tasks.size() == 0) {
                throw new DukeIndexErrorException();
            } else {
                throw new DukeIndexErrorException(tasks.size());
            }
        }
    }

    /**
    * Displays tasks containing the keywords in the user input.
    *
    * @param fullCommand The input from the user containing keywords to search for.
    */
    protected String find(String[] fullCommand) throws DukeException {
        if (fullCommand.length < 2 || fullCommand[1].equals("")) {
            throw new DukeFormatCommandException("find");
        }

        ArrayList<Task> results = tasks.find(fullCommand[1].split(" "));
        return ui.getAllTasks(results);
    }

    /**
     * Adds a new {@code Todo} task to the task list.
     *
     * @param fullCommand The full input from the user containing the task's description.
     * @throws DukeException If the input is invalid.
     */
    protected String newTodo(String[] fullCommand) throws DukeException {
        if (fullCommand.length < 2 || fullCommand[1].equals("")) {
            throw new DukeFormatCommandException("todo");
        }

        Todo td = new Todo(fullCommand[1]);
        tasks.add(td);
        return ui.getNewTaskMessage(td, tasks.size());
    }

    /**
     * Adds a new {@code Deadline} task to the task list.
     *
     * @param fullCommand The full input from the user containing the task's description and deadline.
     * @throws DukeException If the input is invalid.
     */
    protected String newDeadline(String[] fullCommand) throws DukeException {
        if (fullCommand.length < 2 || fullCommand[1].equals("")) {
            throw new DukeFormatCommandException("deadline");
        }

        try {
            String[] newDeadline = fullCommand[1].split(" /by ", 2);
            System.out.println(newDeadline[1]);
            ZonedDateTime by = ZonedDateTime.parse(newDeadline[1] + " " + timeZone, DATE_TIME_FORMATTER);
            Deadline dl = new Deadline(newDeadline[0], by);
            tasks.add(dl);
            return ui.getNewTaskMessage(dl, tasks.size());
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            e.printStackTrace();
            throw new DukeFormatCommandException("deadline", "/by");
        }
    }

    /**
     * Adds a new {@code Event} task to the task list.
     *
     * @param fullCommand The full input from the user containing the task's description and datetime.
     * @throws DukeException If the input is invalid.
     */
    protected String newEvent(String[] fullCommand) throws DukeException {
        if (fullCommand.length < 2 || fullCommand[1].equals("")) {
            throw new DukeFormatCommandException("event");
        }

        try {
            String[] newEvent = fullCommand[1].split(" /at ", 2);
            ZonedDateTime at = ZonedDateTime.parse(newEvent[1] + " " + timeZone, DATE_TIME_FORMATTER);
            Event ev = new Event(newEvent[0], at);
            tasks.add(ev);
            return ui.getNewTaskMessage(ev, tasks.size());
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new DukeFormatCommandException("event", "/at");
        }
    }
}
