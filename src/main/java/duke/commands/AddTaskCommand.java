package duke.commands;

import duke.exceptions.*;
import duke.tasks.*;
import duke.utils.Storage;
import duke.utils.TaskParser;

public class AddTaskCommand extends Command {

    private Storage storage;
    private TaskList tasks;
    private TaskType type;
    private String taskString;
    private boolean hasExecutedSuccessfully;

    public AddTaskCommand(Storage storage, TaskList tasks, TaskType type, String taskString) {
        this.storage = storage;
        this.tasks = tasks;
        this.type = type;
        this.taskString = taskString;
        hasExecutedSuccessfully = false;
    }

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
