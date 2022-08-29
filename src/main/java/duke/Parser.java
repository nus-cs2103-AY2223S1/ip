package duke;

import duke.task.EventTask;
import duke.task.Task;
import duke.task.DeadlineTask;
import duke.task.TodoTask;

import java.time.format.DateTimeParseException;

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
     * @param verbose Boolean to indicate verbosity of Ui.
     * @throws DukeException If command cannot be parsed or is invalid.
     */
    public void parse(String command, boolean verbose) throws DukeException {
        ui.setVerbose(verbose);
        if (command.startsWith("deadline") || command.startsWith("event") || command.startsWith("todo")) {
            parseTask(command);
        } else if (command.startsWith("mark") || command.startsWith("unmark") || command.startsWith("delete")) {
            parseTwo(command);
        } else if (command.startsWith("find")) {
            parseFind(command);
        } else if (command.startsWith("list") || command.startsWith("bye")) {
            parseOne(command);
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Parses and executes task commands for Duke Bot.
     * Task commands are in the format "task description separator time".
     * 
     * @param command Task command string.
     * @throws DukeException If command cannot be parsed or is invalid.
     */
    public void parseTask(String command) throws DukeException {
        Task t = null;
        try {
            if (command.startsWith("deadline")) {
                t = new DeadlineTask(command);
            } else if (command.startsWith("event")) {
                t = new EventTask(command);
            } else if (command.startsWith("todo")) {
                t = new TodoTask(command);
            }
            tasks.addTask(t);
        } catch (DateTimeParseException e) {
            System.out.println("Unable to parse date: " + e);
        }
    }

    /**
     * Parses and executes commands that are two parts long for Duke Bot.
     * Two part long commands contains one whitespace after command.
     * Two part long commands are in the format "command description".
     * 
     * @param command Two part long command.
     * @throws DukeException If command cannot be parsed or is invalid.
     */
    public void parseTwo(String command) throws DukeException {
        String[] split = command.split(" ", 2);
        String commandName = split[0];
        int id = tasks.getSize();
        if (split.length > 1){
            try {
                id = Integer.parseInt(split[1]);
            } catch (NumberFormatException e) {
                throw new DukeException("Please input a valid number.");
            }
        } else if (id == 0) {
            throw new DukeException("Task list is empty.");
        }

        if (commandName.equals("delete")) {
            tasks.deleteTask(id - 1);
        } else if (commandName.equals("mark")) {
            tasks.markTask(id - 1);
        } else if (commandName.equals("unmark")) {
            tasks.unmarkTask(id - 1);
        }
    }

    /**
     * Parses and executes find command for Duke Bot.
     * 
     * @param command Find command.
     * @throws DukeException If command cannot be parsed or is invalid.
     */
    public void parseFind(String command) throws DukeException {
        String[] split = command.split(" ", 2);
        if (split.length < 2) {
            throw new DukeException("Please input string for find.");
        }
        tasks.findTask(split[1]);
    }
    
    /**
     * Parses and executes commands that are one part long for Duke Bot.
     * One part long commands are in the format "command".
     *
     * @param command One part long command.
     * @throws DukeException If command cannot be parsed or is invalid.
     */
    public void parseOne(String command) throws DukeException {
        if (command.equals("bye")) {
            tasks.saveTasks();
            duke.terminate();
        } else if (command.equals("list")) {
            tasks.generateList();
        } else {
            throw new DukeException("Invalid command format.");
        }
    }
}
