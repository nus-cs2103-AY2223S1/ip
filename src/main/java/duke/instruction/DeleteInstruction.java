package duke.instruction;

import duke.functions.TaskList;
import duke.tasks.Task;

/**
 * DeleteInstruction class to initiate a delete command inputted by the user.
 *
 * @author lauralee
 */
public class DeleteInstruction implements Instruction{

    TaskList taskList;
    String userInput;
    int taskPos;
    Task deletedTask;

    /**
     * Constructor for the DeleteInstruction class.
     *
     * @param taskList The TaskList storing the tasks added by this user.
     * @param userInput The description for the task that was just added by this user.
     */
    public DeleteInstruction(TaskList taskList, String userInput) {
        this.taskList = taskList;
        this.userInput = userInput;
        char b = userInput.charAt(7);
        this.taskPos = Character.getNumericValue(b);
        this.deletedTask = this.taskList.getTaskArr()[this.taskPos];
    }

    @Override
    public String execute() {
        return this.taskList.deleteTask(this.taskPos, this.deletedTask);
    }
}