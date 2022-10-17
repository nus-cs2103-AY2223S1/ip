package duke.instruction;

import duke.functions.TaskList;
import duke.support.DukeException;
import duke.tasks.Task;

/**
 * UnMarkInstruction class to initiate an UnMark command inputted by the user.
 *
 * @author lauralee
 */
public class UnMarkInstruction implements Instruction {

    private TaskList taskList;
    private int taskPos;

    /**
     * Constructor for the UnMarkInstruction class.
     *
     * @param taskList The TaskList storing the tasks added by this user.
     * @param taskDescription The description for the task that was just added by this user.
     */
    public UnMarkInstruction(TaskList taskList, String taskDescription) {
        this.taskList = taskList;
        char b = taskDescription.charAt(7);
        this.taskPos = Character.getNumericValue(b);
    }

    @Override
    public String execute() throws DukeException.TaskPosException {
        if (this.taskPos > Task.getNumberTasks() || this.taskPos <= 0) {
            throw new DukeException.TaskPosException();
        }
        assert Task.getNumberTasks() > 0 : "There are no tasks in the list";
        assert this.taskPos <= Task.getNumberTasks() : "There is no task with the index number you have specified";
        return this.taskList.markIncomplete(taskPos);
    }
}
