package command;

import duke.TaskList;
import duke.Ui;
import task.Event;

import java.time.LocalDate;

/**
 *  A class which encapsulates the event command of Duke.
 *  @author  Chen Guanzhou
 *  @version v2
 */
public class EventCommand extends Command {
    String eventInput;
    TaskList currList;

    public EventCommand(String eventInput, TaskList currList) {
        this.eventInput = eventInput;
        this.currList = currList;
    }

    /**
     * Executes the event command and returns Duke's response to be shown
     * @return Duke's response which is the event task to be passed into the Dialog Box.
     */
    @Override
    public String execute() {
        String[] temp1 = eventInput.split(" /at ", 2);
        String at = temp1[1];
        String eventDesc = temp1[0].split("event ", 2)[1];
        Event eventTask = new Event(eventDesc, LocalDate.parse(at));
        currList.addTask(eventTask, false);
        String result = Ui.addTaskMessage(eventTask);
        result += Ui.getTaskNumberMessage(currList);
        return result;
    }
}
