package commands;

import duke.Storage;
import duke.Ui;
import tasks.Task;
import tasks.TaskList;
import tasks.Todos;

/**
 * TodoCommand creates and adds a new Todos task to the task list.
 */
public class TodoCommand extends Command {
    String descript;
    boolean isDone;

    /**
     * Constructor for TodoCommand.
     *
     * @param descript Description of todo.
     */
    public TodoCommand(String descript) {
        this.descript = descript;
        this.isDone = false;
    }

    /**
     * Executes TodoCommand by creating and adding the new Todos task to the task list.
     * @param taskList Task list to which the new Todos should be added to.
     * @param ui Ui to retrieve return string from.
     * @param s Storage containing the list of tasks or where tasks should be saved.
     * @return Add string to be displayed by program.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage s) {
        Task toAdd = new Todos(this.descript);
        taskList.addTask(toAdd);
        return Ui.printAddStatement(toAdd.toString(), taskList.getSize());
    }
}
