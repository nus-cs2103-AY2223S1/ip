package duke;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import duke.exception.DukeException;
import duke.exception.DukeFormatCommandException;
import duke.exception.DukeIndexErrorException;
import duke.exception.DukeInvalidCommandException;
import duke.task.Activity;
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
    /** Commands accepted by Duke */
    public enum Command {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, ACTIVITY, EVENT, DELETE, SAVE, FIND;

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
    private final ZoneId timeZone = ZoneId.of("GMT+00:00");

    private final Storage storage;
    private final Ui ui;
    private final Parser parser;
    private final TaskList tasks;

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
            case ACTIVITY:
                return newActivity(fullCommand);
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

        assert tasks != null;

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

        assert tasks != null;

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

        assert tasks != null;

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

        assert tasks != null;

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

        assert tasks != null;

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

        assert tasks != null;

        try {
            String[] newDeadline = fullCommand[1].split(" /by ", 2);
            ZonedDateTime by = parser.parseDateTime(newDeadline[1], timeZone);
            Deadline deadline = new Deadline(newDeadline[0], by);
            tasks.add(deadline);
            return ui.getNewTaskMessage(deadline, tasks.size());
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            e.printStackTrace();
            throw new DukeFormatCommandException("deadline", "/by", "\"dd/MM/yyyy\" or \"dd/MM/yyyy HH:mm\"");
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

        assert tasks != null;

        try {
            String[] newEvent = fullCommand[1].split(" /at ", 2);
            ZonedDateTime at = parser.parseDateTime(newEvent[1], timeZone);
            Event event = new Event(newEvent[0], at);
            tasks.add(event);
            return ui.getNewTaskMessage(event, tasks.size());
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new DukeFormatCommandException("event", "/at", "\"dd/MM/yyyy\" or \"dd/MM/yyyy HH:mm\"");
        }
    }

    /**
     * Adds a new {@code Activity} task to the task list.
     *
     * @param fullCommand The full input from the user containing the task's description and duration.
     * @throws DukeException If the input is invalid.
     */
    protected String newActivity(String[] fullCommand) throws DukeException {
        if (fullCommand.length < 2 || fullCommand[1].equals("")) {
            throw new DukeFormatCommandException("activity");
        }

        assert tasks != null;

        try {
            String[] newActivity = fullCommand[1].split(" /for ", 2);
            Duration duration = parser.parseDuration(newActivity[1]);
            Activity activity = new Activity(newActivity[0], duration);
            tasks.add(activity);
            return ui.getNewTaskMessage(activity, tasks.size());
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeFormatCommandException("activity", "/for", "\"n\" hours where n is an integer");
        }
    }
}
