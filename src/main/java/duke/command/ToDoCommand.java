package duke.command;

import java.io.IOException;

import duke.Ui;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

public class ToDoCommand extends Command {
    public static final String COMMAND_ID = "TODO";
    private final Task task;

    /** Constructor for ToDoCommand
     */
    public ToDoCommand(Task task) {
        this.task = task;
    }

    /**
     * Returns a string of the to do task that had just been executed
     * @param taskList
     * @param ui
     * @param storage
     * @return a result of the current to do task execution
     * @see duke.task.ToDo
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        String result = taskList.addTaskToList(task);
        storage.writeDataToFile(taskList);
        return result;
    }
}
