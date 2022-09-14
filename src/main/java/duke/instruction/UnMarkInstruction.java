package duke.instruction;

import duke.functions.TaskList;

/**
 * UnMarkInstruction class to initiate an UnMark command inputted by the user.
 *
 * @author lauralee
 */
public class UnMarkInstruction implements Instruction{

    TaskList taskList;
    String taskDescription;
    int taskPos;

    /**
     * Constructor for the UnMarkInstruction class.
     *
     * @param taskList The TaskList storing the tasks added by this user.
     * @param taskDescription The description for the task that was just added by this user.
     */
    public UnMarkInstruction(TaskList taskList, String taskDescription) {
        this.taskList = taskList;
        this.taskDescription = taskDescription;
        char b = taskDescription.charAt(5);
        this.taskPos = Character.getNumericValue(b);
    }

    @Override
    public String execute() {
        return this.taskList.markIncomplete(taskPos);
    }
}
