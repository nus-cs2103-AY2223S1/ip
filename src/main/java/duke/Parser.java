package duke;

import duke.task.EventTask;
import duke.task.Task;
import duke.task.DeadlineTask;
import duke.task.TodoTask;

import java.time.format.DateTimeParseException;

/**
 * Handles parsing and execution of command strings.
 */
public class Parser {
    private TaskList tasks;
    private Duke duke;
    private Ui ui;

    public Parser(TaskList taskList, Duke dukeInstance, Ui uiInstance) {
        tasks = taskList;
        duke = dukeInstance;
        ui = uiInstance;
    }

    public void parse(String command, boolean verbose) throws DukeException {
        ui.setVerbose(verbose);
        if (command.startsWith("deadline") || command.startsWith("event") || command.startsWith("todo")) {
            parseTask(command);
        } else if (command.startsWith("mark") || command.startsWith("unmark") || command.startsWith("delete")) {
            parseTwo(command);
        } else if (command.startsWith("list") || command.startsWith("bye")) {
            parseOne(command);
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

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

    public void parseOne(String command) {
        if (command.equals("bye")) {
            tasks.saveTasks();
            duke.terminate();
        } else if (command.equals("list")) {
            tasks.generateList();
        }
    }
}
