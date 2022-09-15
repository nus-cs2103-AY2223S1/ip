package bob.commands;

import bob.Storage;
import bob.TaskList;
import bob.ToDo;
import bob.Ui;

/**
 * TodoCommand class to handle "todo" keyword
 */
public class TodoCommand extends Command {

    private String taskName;

    /**
     * Constructor for TodoCommand
     *
     * @param taskName name of Todo task
     */
    public TodoCommand(String taskName) {
        super();
        this.taskName = taskName;
    }

    @Override
    public String executeCommand(TaskList taskList, Storage storage, Ui ui) {
        ToDo newTask = new ToDo(this.taskName);
        taskList.addTask(newTask);
        storage.save(taskList);
        return ui.displayAddedTask(taskList, newTask);
    }
}
