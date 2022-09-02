package duke.commands;

import duke.exceptions.EmptyTaskDescException;
import duke.exceptions.EmptyTaskTimeException;
import duke.exceptions.NoSuchTaskException;
import duke.tasks.*;
import duke.ui.Ui;
import duke.utils.Storage;
import duke.utils.TaskParser;

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
            Task newTask = TaskParser.stringToTask(type, taskString);
            tasks.addTask(newTask);
            ui.showAddTaskResponse(newTask, tasks);
            storage.saveToFile(tasks.getList());
        } catch (EmptyTaskDescException | EmptyTaskTimeException | NoSuchTaskException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
        return true;
    }
}
