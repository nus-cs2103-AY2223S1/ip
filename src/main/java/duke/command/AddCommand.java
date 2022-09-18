package duke.command;

import duke.TaskList;
import duke.Storage;
import duke.Ui;
import duke.DukeException;
import duke.Task;

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

    public String exec(TaskList taskList, Storage storage , Ui ui) throws DukeException {
        Task task = new Task(this.taskName, this.taskType, this.date);
        if (this.taskName.length() == 0) {
            throw new DukeException("the description of a task cannot be empty.");
        }
        taskList.addTask(task);
        return ui.showTaskAddedMessage(task, taskList.getTaskListSize());
    }

}
