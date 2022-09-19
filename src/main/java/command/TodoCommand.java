package command;

import duke.Constants;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import tasks.Todo;

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
        boolean isDuplicated = taskList.isTasksDuplicated(todo);
        if (isDuplicated) {
            return Constants.ERROR_IF_DUPLICATED;
        } else {
            taskList.addTask(todo);
            storage.writeFile(taskList.tasksToString());
            return ui.printAddTask(todo, taskList.getSize());
        }
    }
}
