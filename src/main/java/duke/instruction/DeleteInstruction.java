package duke.instruction;

import duke.functions.TaskList;
import duke.functions.Ui;
import duke.support.DukeException;
import duke.tasks.Task;

/**
 * DeleteInstruction class to initiate a delete command inputted by the user.
 *
 * @author lauralee
 */
public class DeleteInstruction implements Instruction {

    private TaskList taskList;
    private String userInput;
    private int taskPos;
    private Task deletedTask;

    /**
     * Constructor for the DeleteInstruction class.
     *
     * @param taskList The TaskList storing the tasks added by this user.
     * @param userInput The description for the task that was just added by this user.
     */
    public DeleteInstruction(TaskList taskList, String userInput) {
        this.taskList = taskList;
        this.userInput = userInput;
        char b = this.userInput.charAt(7);
        this.taskPos = Character.getNumericValue(b);
        this.deletedTask = this.taskList.getTaskArr()[this.taskPos];
    }

    @Override
    public String execute() throws DukeException.TaskPosException {
        if (this.taskPos > Task.getNumberTasks() || this.taskPos <= 0) {
            throw new DukeException.TaskPosException();
        }
        this.taskList.deleteTask(this.taskPos);
        return Ui.printDelete(this.deletedTask, Task.getNumberTasks());
    }
}
