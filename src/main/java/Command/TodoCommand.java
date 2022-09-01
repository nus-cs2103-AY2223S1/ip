package Command;

import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;
import Tasks.Todo;

/**
 * Creates a new todo task
 */
public class TodoCommand extends Command {
    private String desc;

    /**
     * Constructor that creates a new todo command with the taskname given
     *
     * @param desc
     */
    public TodoCommand(String desc) {
        super();
        this.desc = desc;
    }

    /**
     * Creates a new todo task and adds it into the task list,
     * and save it into the file.
     *
     * @param taskList which contains the current tasklist
     * @param ui which handles the user interface
     * @param storage which handles the saving and loading of file
     * @return string that will be printed in the UI
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Todo todo = new Todo(this.desc);
        taskList.addTask(todo);
        storage.writeFile(taskList.tasksToString());
        return ui.printAddTask(todo, taskList.getSize());
    }
}
