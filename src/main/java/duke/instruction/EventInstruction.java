package duke.instruction;

import duke.functions.TaskList;
import duke.functions.Ui;
import duke.tasks.Event;

/**
 * EventInstruction class to initiate a Event command inputted by the user.
 *
 * @author lauralee
 */

public class EventInstruction implements Instruction {

    private TaskList taskList;
    private String userInput;
    private Event newTask;

    /**
     * Constructor for the EventInstruction class.
     *
     * @param taskList The TaskList storing the tasks added by this user.
     * @param userInput The description for the task that was just added by this user.
     */
    public EventInstruction(TaskList taskList, String userInput) {
        this.taskList = taskList;
        this.userInput = userInput;
        String description = userInput.substring(6, userInput.lastIndexOf("/") - 1);
        String time = userInput.substring(userInput.lastIndexOf("/at") + 4);
        this.newTask = new Event(description, time);
    }

    @Override
    public String execute() {
        this.taskList.addTask(this.newTask);
        return Ui.printEvent(this.newTask);
    }
}
