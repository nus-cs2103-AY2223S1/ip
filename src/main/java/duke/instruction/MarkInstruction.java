package duke.instruction;

import duke.functions.TaskList;

/**
 * MarkInstruction class to initiate a Mark command inputted by the user.
 *
 * @author lauralee
 */
public class MarkInstruction implements Instruction{

    TaskList taskList;
    String userInput;
    int taskPos;

    /**
     * Constructor for the MarkInstruction class.
     *
     * @param taskList The TaskList storing the tasks added by this user.
     * @param userInput The description for the task that was just added by this user.
     */
    public MarkInstruction(TaskList taskList, String userInput) {
        this.taskList = taskList;
        this.userInput = userInput;
        char b = userInput.charAt(5);
        this.taskPos = Character.getNumericValue(b);
    }

    @Override
    public String execute() {
        return this.taskList.markComplete(taskPos);
    }
}
