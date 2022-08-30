package duke.commands;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.ui.Ui;

import java.util.ArrayList;

/**
 * This class encapsulates a Tasks Command
 */
public class TasksCommand extends Command {

    private String date;

    /**
     * Constructs a new Task Command
     * @param date Date of the tasks
     */
    public TasksCommand(String date) {
        this.date = date;
    }

    /**
     * Checks if the command is an Exit Command
     * @return True if it is an Exit Command
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the Command
     * @param ui Prints the messages based on the type of Command
     * @param storage Saves the modified list of tasks
     * @param taskList List of tasks
     * @throws DukeException if invalid inputs are provided
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList) {
        ArrayList<Task> list = taskList.getTasks(this.date);
        return ui.printTasks(list, date);
    }
}
