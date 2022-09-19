package duke.command;

import duke.TaskList;
import duke.Storage;
import duke.Ui;
import duke.DukeException;
import duke.Task;

/*
Creates a new task and adds it to the taskList.
 */
public class AddCommand extends Command {

    String taskName;
    String taskType;
    String date;

    public AddCommand(String taskName, String taskType) {
        this.taskName = taskName;
        this.taskType = taskType;
    }

    public AddCommand(String taskName, String date, String taskType) {
        this.taskName = taskName;
        this.taskType = taskType;
        this.date = date;
    }

    /**
     * Executes the add command.
     * @param taskList List that contains existing tasks.
     * @param storage Storage that stores tasks into a txt file.
     * @param ui Ui that generates response messages.
     * @return The response of Dukie.
     * @throws DukeException Exception that is specific to duke.
     */
    public String exec(TaskList taskList, Storage storage , Ui ui) throws DukeException {
        Task task = new Task(this.taskName, this.taskType, this.date);
        if (this.taskName.length() == 0) {
            throw new DukeException("the description of a task cannot be empty.");
        }
        taskList.addTask(task);
        assert(ui != null);
        return ui.showTaskAddedMessage(task, taskList.getTaskListSize());
    }

}
