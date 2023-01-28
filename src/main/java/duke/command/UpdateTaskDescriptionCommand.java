package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Storage;
import duke.UI;
import duke.Task;

/**
 * UpdateTaskDescriptionCommand class to represent an instruction to update
 * the description of a task.
 *
 * @author Sheryl Kong (A0240686Y)
 */

public class UpdateTaskDescriptionCommand extends Command {
    private int taskNo;
    private String updatedField;

    /**
     * Constructor for UpdateTaskDescriptionCommand class
     *
     * @param taskNo int
     * @param updatedField String
     */

    public UpdateTaskDescriptionCommand(int taskNo, String updatedField) {
        this.taskNo = taskNo;
        this.updatedField = updatedField;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        String prevTask = taskList.getTask(this.taskNo).toString();
        taskList.updateTaskDescription(this.taskNo, this.updatedField);
        Task newTask = taskList.getTask(this.taskNo);
        UI.updateTaskDesc(newTask, prevTask);
        response = UI.updateTaskDescResponse(newTask, prevTask);
        storage.saveData(taskList);
    }
}
