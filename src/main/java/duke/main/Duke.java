package duke.main;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Main class of Duke.
 */
public class Duke {

    /* Storage object handling saving and reading from save file */
    private Storage storage;
    /* List of tasks */
    private TaskList tasks;
    /* Ui object handling output to user */
    private Ui ui;

    /**
     * Constructor for Duke class.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.readTaskListFromFile());
        } catch (DukeException e) {
            ui.printErrorMessage(e);
            tasks = new TaskList(new ArrayList<Task>());
        }
    }

    /**
     * Executes the Duke chatbot logic.
     */
    public String getResponse(String input) {
        // Helper Fields
        boolean isExit = false;

        while (!isExit) {

            // Parse Input
            try {
                Parser.parse(input);
            } catch (DukeException de) {
                return ui.printErrorMessage(de);
            }

            // Valid Input
            Keyword command = Parser.getCommand();
            String argument = Parser.getArgument();
            try {
                switch (command) {
                case TODO: {
                    Task task = new ToDo(argument);
                    tasks.addTask(task);
                    return ui.displayTaskAddedMessage(task, tasks.size());
                }
                case DEADLINE: {
                    String[] taskTokens = argument.split(" /by ");
                    String taskName = taskTokens[0];
                    String deadline = taskTokens[1];
                    LocalDateTime deadlineDate = DateTimeFormatUtils.parseDate(deadline);
                    Task task = new Deadline(taskName, deadlineDate);
                    tasks.addTask(task);
                    return ui.displayTaskAddedMessage(task, tasks.size());
                }
                case EVENT: {
                    String[] taskTokens = argument.split(" /at ");
                    String taskName = taskTokens[0];
                    String duration = taskTokens[1];
                    LocalDateTime[] eventDuration = DateTimeFormatUtils.parseDuration(duration);
                    Task task = new Event(taskName, eventDuration[0], eventDuration[1]);
                    tasks.addTask(task);
                    return ui.displayTaskAddedMessage(task, tasks.size());
                }
                case LIST: {
                    return tasks.displayTaskList();
                }
                case FIND: {
                    return tasks.searchTaskList(argument);
                }
                case DELETE: {
                    Task deletedTask = tasks.getTask(argument);
                    tasks.deleteTask(argument);
                    return ui.displayTaskDeletedMessage(deletedTask, tasks.size());
                }
                case MARK:
                case UNMARK: {
                    tasks.markUnmarkTask(argument, command);
                    Task task = tasks.getTask(argument);
                    return ui.displayTaskMarkUnmarkMessage(task, command);
                }
                case BYE: {
                    isExit = true;
                    return ui.sayGoodbye();
                }
                default: {
                    System.out.print("Unexpected Error in Run");
                    break;
                }
                }
                // Update Save File
                storage.saveTaskListToFile(tasks);

            } catch (DukeException de) {
                return ui.printErrorMessage(de);
            }
        }
        return "";
    }

}


