package duke.instruction;

import duke.functions.TaskList;
import duke.support.DukeException;
import duke.tasks.Task;

/**
 * SnoozeInstruction class to initiate a snooze command as inputted by the user.
 *
 * @author lauralee
 */
public class SnoozeInstruction implements Instruction {

    private TaskList taskList;
    private String newTime;
    private int taskPos;
    private Task snoozedTask;

    /**
     * Constructor for the SnoozeInstruction class.
     * Example of a user input for snooze: snooze 1 /to 2022-09-14
     *
     * @param taskList The TaskList storing the tasks added by this user.
     * @param userInput The description for the task that was just added by this user.
     */
    public SnoozeInstruction(TaskList taskList, String userInput) {
        this.taskList = taskList;
        char b = userInput.charAt(7);
        this.taskPos = Character.getNumericValue(b);
        this.newTime = userInput.substring(userInput.lastIndexOf("/to") + 4);
        this.snoozedTask = this.taskList.getTaskArr()[this.taskPos];
    }

    @Override
    public String execute() throws DukeException.SnoozeException {
        this.snoozedTask.snoozeTask(newTime);
        this.taskList.getTaskArr()[this.taskPos] = this.snoozedTask;
        return this.snoozedTask.snoozeTask(newTime);
    }
}
