package duke.command;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;

/**
 * The UnMarkCommand that helps the user unmark tasks.
 *
 * @author Leong Jia Hao Daniel
 */
public class MassUnMarkCommand extends Command {

    private Commands type;

    /**
     * Constructs the mass UnMarkCommand.
     *
     * @param command The type of task to be unmarked.
     */
    public MassUnMarkCommand(Commands command) {
        this.type = command;
    }

    /**
     * Executes the unmark command.
     *
     * @param ui The ui class which handles the user interface.
     * @param storage The storage class which deals with the file.
     * @param taskList The tasklist that stores the tasks.
     * @return The String that duke says.
     * @throws DukeException Throws if there is an error.
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        Task taskType = null;
        switch(type) {
        case Deadline:
            taskType = new Deadline();
            break;
        case Event:
            taskType = new Event();
            break;
        case ToDo:
            taskType = new ToDo();
            break;
        default:
            break;
        }
        ArrayList<Integer> indexes = taskList.getIndexOfTasks(taskType);
        String tasks = "";
        for (Integer i : indexes) {
            Task tempTask = taskList.getTask(i);
            if (tempTask.getStatusIcon().equals("X")) {
                tempTask.markAsIncomplete();
                tasks += tempTask + "\n";
            }
        }
        storage.saveFile(taskList);
        return ui.formatMessage("Nice! I've unmarked the following tasks:\n" + tasks);
    }

    /**
     * Returns true if the command is an ExitCommand.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
