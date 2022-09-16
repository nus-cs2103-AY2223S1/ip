package duke.commands;

import duke.exceptions.*;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.TaskType;
import duke.utils.Storage;
import duke.utils.TaskParser;

public class AddTaskCommand extends Command {

    private Storage storage;
    private TaskList tasks;
    private TaskType type;
    private String taskString;
    private boolean hasExecutedSuccessfully;

    /**
     * Constructs a command that adds a task corresponding to the specified
     * task string and type to the list of tasks.
     * @param storage Storage object for storing the task list.
     * @param tasks TaskList object in use by the app.
     * @param type The type of the task to be added.
     * @param taskString The string representing the task to be added.
     */
    public AddTaskCommand(Storage storage, TaskList tasks, TaskType type, String taskString) {
        this.storage = storage;
        this.tasks = tasks;
        this.type = type;
        this.taskString = taskString;
        hasExecutedSuccessfully = false;
    }

    /**
     * Adds the Task to the list of Tasks.
     * @return Response message.
     */
    @Override
    public String execute() {
        try {
            Task newTask = TaskParser.stringToTask(type, taskString);
            tasks.addTask(newTask);
            storage.saveToFile(tasks.getList());

            String response = String.format(
                    "Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.",
                    newTask, tasks.getSize());
            hasExecutedSuccessfully = true;
            return response;
        } catch (EmptyTaskDescException | EmptyTaskDateException | NoSuchTaskTypeException |
                 UnrecognisedDateException e) {
            String response = String.format("Oops! %s", e.getMessage());
            return response;
        }
    }

    /**
     * Removes the Task from the list of Tasks.
     * @return Response message.
     */
    @Override
    public String undo() {
        if (!hasExecutedSuccessfully) {
            return "Oops! Your previous add task command did not complete successfully, " +
                    "so there is nothing to undo.";
        }

        try {
            Task deletedTask = tasks.deleteTask(tasks.getSize() - 1);
            storage.saveToFile(tasks.getList());

            String response = String.format(
                    "Got it. I've removed the task you added previously:\n  %s",
                    deletedTask);
            return response;
        } catch (TaskNotFoundException e) {
            String response = String.format("Oops! %s", e.getMessage());
            return response;
        }
    }

}
