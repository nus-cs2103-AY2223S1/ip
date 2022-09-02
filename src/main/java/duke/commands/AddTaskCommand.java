package duke.commands;

import duke.exceptions.EmptyTaskDescException;
import duke.exceptions.EmptyTaskDateException;
import duke.exceptions.NoSuchTaskTypeException;
import duke.exceptions.UnrecognisedDateException;
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
        } catch (EmptyTaskDescException | EmptyTaskDateException | NoSuchTaskTypeException |
                 UnrecognisedDateException e) {
            ui.showError(e);
            return false;
        }
        return true;
    }
}
