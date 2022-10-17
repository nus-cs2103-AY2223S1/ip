package command;

import java.io.IOException;

import wagwan.Event;
import wagwan.Storage;
import wagwan.Task;
import wagwan.TaskList;
import wagwan.WagwanException;
import wagwan.WagwanUi;

/**
* Command that adds an Event to the TaskList when executed.
*/
public class EventCommand extends Command {
    private boolean isExit;
    private String userAction;

    /**
     * Constructor for an event command.
     * @param userAction the event description.
     */
    public EventCommand(String userAction) {
        this.isExit = false;
        this.userAction = userAction;
    }

    @Override
    public String execute(TaskList tasks, WagwanUi ui, Storage storage) throws WagwanException {
        try {
            int initialSize = tasks.getTaskListSize();
            String[] eventString = userAction.split("/at ");
            Task newEvent = new Event(eventString[0], eventString[1]);
            tasks.addTask(newEvent);
            assert tasks.getTaskListSize() == initialSize + 1 : WagwanUi.ADD_TASK_ERROR;
            storage.save();
            return ui.sendMessage(" Got it. I've added this task:\n" + "   " + newEvent.toString()
                    + "\n Now you have " + tasks.getTaskListSize() + " tasks in the list.");
        } catch (ArrayIndexOutOfBoundsException e1) {
            throw new WagwanException(WagwanUi.INVALID_EVENT);
        } catch (ClassCastException e2) {
            throw new WagwanException(WagwanUi.CLASS_CAST_ERROR);
        } catch (IOException e3) {
            throw new WagwanException(e3.getMessage());
        } catch (WagwanException e4) {
            return e4.toString();
        }
    }

    @Override
    public String toString() {
        return "this is an event command : event " + userAction;
    }
}
