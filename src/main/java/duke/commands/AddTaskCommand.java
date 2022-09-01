package duke.commands;

import duke.exceptions.EmptyTaskDescException;
import duke.exceptions.EmptyTaskTimeException;
import duke.tasks.*;
import duke.ui.Ui;
import duke.utils.Storage;

public class AddTaskCommand extends Command {

    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    private TaskType type;

    private String taskString;

    public AddTaskCommand(Storage storage, Ui ui, TaskList tasks, TaskType type, String taskString) {
        this.storage = storage;
        this.ui = ui;
        this.tasks = tasks;
        this.type = type;
        this.taskString = taskString;
    }

    @Override
    public boolean execute() {
        try {
            if ("".equals(taskString)) {
                throw new EmptyTaskDescException();
            }

            String[] taskInfo;
            Task newTask;

            switch (type) {
                case TODO:
                    newTask = new Todo(taskString);
                    break;
                case EVENT:
                    taskInfo = taskString.split("/at");
                    if (taskInfo.length < 2) {
                        throw new EmptyTaskTimeException();
                    }
                    newTask = new Event(taskInfo[0].trim(), taskInfo[1].trim());
                    break;
                case DEADLINE:
                    taskInfo = taskString.split("/by");
                    if (taskInfo.length < 2) {
                        throw new EmptyTaskTimeException();
                    }
                    newTask = new Deadline(taskInfo[0].trim(), taskInfo[1].trim());
                    break;
                default:
                    return false;
            }

            tasks.addTask(newTask);
            ui.showAddTaskResponse(newTask, tasks);
            storage.saveToFile(tasks.getList());
        } catch (EmptyTaskDescException | EmptyTaskTimeException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
        return true;
    }
}
