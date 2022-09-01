/**
 * Creates a new todo task
 */
package Command;

import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;
import Tasks.Todo;

public class TodoCommand extends Command{
    private String desc;

    public TodoCommand(String desc) {
        super();
        this.desc = desc;
    }

    /**
     * Creates a new todo task and adds it into the tasklist,
     * and save it into the file.
     *
     * @param taskList which contains the current tasklist
     * @param ui which handles the user interface
     * @param storage which handles the saving and loading of file
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Todo todo = new Todo(this.desc);
        taskList.addTask(todo);
        storage.writeFile(taskList.tasksToString());
        ui.printAddTask(todo, taskList.getSize());
    }
}
