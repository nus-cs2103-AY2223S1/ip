package duke.instruction;

import duke.functions.TaskList;
import duke.functions.Ui;
import duke.tasks.Event;
import duke.tasks.Task;

public class SnoozeInstruction implements Instruction {

    TaskList taskList;
    String userInput;
    int taskPos;
    Task snoozedTask;

    /**
     * Constructor for the SnoozeInstruction class.
     * Example of a user input for snooze: snooze 1 /to 2022-09-14
     *
     * @param taskList The TaskList storing the tasks added by this user.
     * @param userInput The description for the task that was just added by this user.
     */
    public SnoozeInstruction(TaskList taskList, String userInput) {
        this.taskList = taskList;
        this.userInput = userInput;
        char b = userInput.charAt(7);
        this.taskPos = Character.getNumericValue(b);
        String newTime = userInput.substring(userInput.lastIndexOf("/to") + 4);
        this.snoozedTask = this.taskList.getTaskArr()[this.taskPos];
        this.snoozedTask.snoozeTask(newTime);
    }

    @Override
    public String execute() {
        this.taskList.getTaskArr()[this.taskPos] = this.snoozedTask;
        if (this.snoozedTask instanceof Event) {
            return Ui.printSnoozeEvent(this.snoozedTask);
        } else {
            return Ui.printSnoozeDeadline(this.snoozedTask);
        }
    }
}
