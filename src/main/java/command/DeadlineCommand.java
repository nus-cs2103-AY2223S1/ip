package command;

import duke.TaskList;
import duke.Ui;
import task.Deadline;
import java.time.LocalDate;

/**
 *  A class which encapsulates the deadline command of Duke.
 *  @author  Chen Guanzhou
 *  @version v2
 */
public class DeadlineCommand extends Command {
    String deadlineInput;
    TaskList currList;

    public DeadlineCommand(String deadlineInput, TaskList currList) {
        this.deadlineInput = deadlineInput;
        this.currList = currList;
    }

    /**
     * Executes the deadline command and returns Duke's response to be shown
     * @return Duke's response which is the deadline task to be passed into the Dialog Box.
     */
    @Override
    public String execute() {
        String[] temp = deadlineInput.split(" /by ", 2);
        String by = temp[1];
        String deadlineDesc = temp[0].split("deadline ")[1];
        Deadline deadlineTask = new Deadline(deadlineDesc, LocalDate.parse(by));
        currList.addTask(deadlineTask, false);
        String result = Ui.addTaskMessage(deadlineTask);
        result += Ui.getTaskNumberMessage(currList);
        return result;
    }
}
