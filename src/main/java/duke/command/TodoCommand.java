/**
 * Project Duke CS2103
 * Done by Hong Jin.
 */
package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Todo;

/**
 * public class TodoCommand to handle tasks to be done.
 */
public class TodoCommand extends Command{
    private String event;

    /**
     * public constructor TodoCommand that takes in name of Task.
     * @param event name of Task.
     */
    public TodoCommand(String event) {
        super();
        this.event = event;
    }

    /**
     * public method execute to execute command.
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Todo e = new Todo(this.event);
        tasks.addTask(e);
        storage.saveToFile(tasks.saveList());
        return ui.printAddTask(e, tasks.getSize());
    }
}
