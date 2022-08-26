package commands;

import duke.Ui;
import tasks.Deadlines;
import tasks.Events;
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

    @Override
    /**
     * Executes TodoCommand by creating and adding the new Todos task to the task list.
     *
     * @param taskList Task list to which the new Todos should be added to.
     */
    public void run(TaskList taskList) {
        Task toAdd = new Todos(this.descript);
        taskList.addTask(toAdd);
        Ui.printAddStatement(toAdd.toString(), taskList.getSize());
    }
}
