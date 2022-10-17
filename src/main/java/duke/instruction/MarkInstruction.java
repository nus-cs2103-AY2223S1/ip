package duke.instruction;

import duke.functions.TaskList;
import duke.support.DukeException;
import duke.tasks.Task;

/**
 * MarkInstruction class to initiate a Mark command inputted by the user.
 *
 * @author lauralee
 */
public class MarkInstruction implements Instruction {

    private TaskList taskList;
    private String userInput;
    private int taskPos;

    /**
     * Constructor for the MarkInstruction class.
     *
     * @param taskList The TaskList storing the tasks added by this user.
     * @param userInput The description for the task that was just added by this user.
     */
    public MarkInstruction(TaskList taskList, String userInput) {
        this.taskList = taskList;
        this.userInput = userInput;
        char b = this.userInput.charAt(5);
        this.taskPos = Character.getNumericValue(b);
    }

    @Override
    public String execute() throws DukeException.TaskPosException {
        if (this.taskPos > Task.getNumberTasks() || this.taskPos <= 0) {
            throw new DukeException.TaskPosException();
        }
        assert Task.getNumberTasks() > 0 : "There are no tasks in the list";
        assert this.taskPos <= Task.getNumberTasks() : "There is no task with the index number you have specified";
        return this.taskList.markComplete(taskPos);
    }
}
