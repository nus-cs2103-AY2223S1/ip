package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.ToDo;

/**
 * A Command which makes a ToDo object and adds it to a TaskList object when executed.
 */
public class MakeToDoCommand extends Command {
    private String description;

    /**
     * Constructs a MakeToDoCommand object which will make a ToDo object with description and time and add
     * to a TaskList object when executed.
     * @param description Description for the ToDo object to be created.
     */
    public MakeToDoCommand(String description) {
        this.description = description;
    }

    /**
     * Makes a new ToDo object with description from the constructor and adds it to taskList.
     * @param taskList TaskList object of the Duke object for the Command object to use.
     * @param ui Ui object of the Duke object for the Command object to use.
     * @param storage Storage object of the Duke object for the Command object to use.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ToDo newTask = new ToDo(this.description);
        taskList.addTask(newTask);
        ui.showTaskAddedOrDeleted(newTask, taskList.getTaskListLength(), true);
    }

    /**
     * Whether the MakeToDoCommand should end the Duke object.
     * It should not.
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
