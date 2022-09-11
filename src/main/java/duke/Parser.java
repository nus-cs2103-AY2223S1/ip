package duke;

import java.time.format.DateTimeParseException;

import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TodoTask;

/**
 * Class that handles parsing and execution of command strings for Duke Bot commands.
 */
public class Parser {
    private TaskList tasks;
    private Duke duke;
    private Ui ui;

    /**
     * Class constructor for Parser.
     *
     * @param taskList TaskList instance tracking tasks in Duke Bot.
     * @param dukeInstance Duke instance representing Duke Bot.
     * @param uiInstance Ui instance representing user interface for Duke Bot.
     */
    public Parser(TaskList taskList, Duke dukeInstance, Ui uiInstance) {
        tasks = taskList;
        duke = dukeInstance;
        ui = uiInstance;
    }

    /**
     * Parses and executes a command for Duke Bot.
     * Verbose argument can be used to control output of Ui.
     *
     * @param command Command string for parsing and execution.
     * @param isVerbose Boolean to indicate verbosity of Ui.
     * @return Response string from Duke Bot.
     * @throws DukeException If command cannot be parsed or is invalid.
     */
    public String parse(String command, boolean isVerbose) throws DukeException {
        ui.setVerbose(isVerbose);
        if (command.startsWith("deadline") || command.startsWith("event") || command.startsWith("todo")) {
            return parseTask(command);
        } else if (command.startsWith("mark") || command.startsWith("unmark") || command.startsWith("delete")) {
            return parseTwo(command);
        } else if (command.startsWith("find")) {
            return parseFind(command);
        } else if (command.startsWith("list") || command.startsWith("bye")) {
            return parseOne(command);
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Parses and executes task commands for Duke Bot.
     * Task commands are in the format "task description separator time".
     *
     * @param command Task command string.
     * @return Response string from Duke Bot.
     * @throws DukeException If command cannot be parsed or is invalid.
     */
    public String parseTask(String command) throws DukeException {
        Task t = null;
        try {
            if (command.startsWith("deadline")) {
                t = new DeadlineTask(command);
            } else if (command.startsWith("event")) {
                t = new EventTask(command);
            } else if (command.startsWith("todo")) {
                t = new TodoTask(command);
            }
            assert t != null;
            return tasks.addTask(t);
        } catch (DateTimeParseException e) {
            System.out.println("Unable to parse date: " + e);
            return "Unable to parse date: " + e;
        }
    }

    /**
     * Parses and executes commands that are two parts long for Duke Bot.
     * Two part long commands contains one whitespace after command.
     * Two part long commands are in the format "command description".
     *
     * @param command Two part long command.
     * @return Response string from Duke Bot.
     * @throws DukeException If command cannot be parsed or is invalid.
     */
    public String parseTwo(String command) throws DukeException {
        String[] split = command.split(" ", 2);
        String commandName = split[0];
        int id = tasks.getSize();
        if (split.length > 1) {
            try {
                id = Integer.parseInt(split[1]);
            } catch (NumberFormatException e) {
                throw new DukeException("Please input a valid number.");
            }
        } else if (id == 0) {
            throw new DukeException("Task list is empty.");
        }

        if (commandName.equals("delete")) {
            return tasks.deleteTask(id - 1);
        } else if (commandName.equals("mark")) {
            return tasks.markTask(id - 1);
        } else if (commandName.equals("unmark")) {
            return tasks.unmarkTask(id - 1);
        } else {
            throw new DukeException("Unknown command");
        }
    }

    /**
     * Parses and executes find command for Duke Bot.
     *
     * @param command Find command.
     * @return Response string from Duke Bot.
     * @throws DukeException If command cannot be parsed or is invalid.
     */
    public String parseFind(String command) throws DukeException {
        String[] split = command.split(" ", 2);
        if (split.length < 2) {
            throw new DukeException("Please input string for find.");
        }
        return tasks.findTask(split[1]);
    }

    /**
     * Parses and executes commands that are one part long for Duke Bot.
     * One part long commands are in the format "command".
     *
     * @param command One part long command.
     * @return Response string from Duke Bot.
     * @throws DukeException If command cannot be parsed or is invalid.
     */
    public String parseOne(String command) throws DukeException {
        if (command.equals("bye")) {
            String response = tasks.saveTasks();
            duke.terminate();
            return response;
        } else if (command.equals("list")) {
            return tasks.generateList();
        } else {
            throw new DukeException("Invalid command format.");
        }
    }
}
