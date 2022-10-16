package duke.main;

import java.time.LocalDateTime;
import java.util.ArrayList;

import duke.commandword.CommandWord;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.Todo;



/**
 * Main Duke class.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor for Duke class.
     */
    public Duke() {
        // Initialising the field variables
        storage = new Storage();
        ui = new Ui();

        try {
            taskList = new TaskList(storage.loadTaskList());
            ui.successLoadMessage();
        } catch (DukeException de) {
            ui.printErrorMessage(de);
            taskList = new TaskList(new ArrayList<Task>());
        }
    }

    /**
     * Executes the logic flow of Duke.
     */
    String getResponse(String input) {
        ui.greetUser();
        boolean isExit = false;
        while (!isExit) {
            // If input is invalid, then print error message
            try {
                Parser.parse(input);
            } catch (DukeException de) {
                return ui.printErrorMessage(de);
            }

            // If input is valid
            CommandWord command = Parser.getCommand();
            String description = Parser.getDescription();

            try {
                switch (command) {
                case BYE: {
                    isExit = true;
                    return ui.exitJukebox();
                }
                case LIST: {
                    return taskList.printList();
                }
                case MARK:
                case UNMARK: {
                    taskList.markUnmarkTask(command, description);
                    Task markedTask = taskList.getTask(description);
                    storage.saveTaskList(taskList); // Save to file
                    return ui.markUnmarkTaskMessage(markedTask, command);
                }
                case DELETE: {
                    Task deletedTask = taskList.deleteTask(description);
                    storage.saveTaskList(taskList); // Save to file
                    return ui.deleteTaskMessage(deletedTask, taskList);
                }
                case FIND: {
                    return taskList.findTask(description);
                }
                case TODO: {
                    Task newTask = new Todo(description);
                    taskList.addTask(newTask);
                    storage.saveTaskList(taskList); // Save to file
                    return ui.addTaskMessage(newTask, taskList);
                }
                case DEADLINE: {
                    String[] descriptionArr = description.split(" /by ");
                    LocalDateTime dateTime = DateTime.parseDate(descriptionArr[1]);
                    String taskDescription = descriptionArr[0];
                    Task newTask = new Deadline(taskDescription, dateTime);
                    taskList.addTask(newTask);
                    storage.saveTaskList(taskList); // Save to file
                    return ui.addTaskMessage(newTask, taskList);
                }
                case EVENT: {
                    String[] descriptionArr = description.split(" /at ");
                    LocalDateTime dateTime = DateTime.parseDate(descriptionArr[1]);
                    String taskDescription = descriptionArr[0];
                    Task newTask = new Deadline(taskDescription, dateTime);
                    taskList.addTask(newTask);
                    storage.saveTaskList(taskList); // Save to file
                    return ui.addTaskMessage(newTask, taskList);
                }
                default: {
                    return "Oops... This is a rare error!";
                }
                }
            } catch (DukeException de) {
                return de.getMessage();
            }
        }
        return "Unhandled error";
    }
}

