package command;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import wagwan.Deadline;
import wagwan.Storage;
import wagwan.Task;
import wagwan.TaskList;
import wagwan.WagwanException;
import wagwan.WagwanUi;

/**
* Command that adds a Deadline to the TaskList when executed.
*/
public class DeadlineCommand extends Command {
    private boolean isExit;
    private String userAction;

    /**
     * Constructor for a deadline command.
     * @param userAction the deadline description.
     */
    public DeadlineCommand(String userAction) {
        this.isExit = false;
        this.userAction = userAction;
    }

    @Override
    public String execute(TaskList tasks, WagwanUi ui, Storage storage) throws WagwanException {
        try {
            int initialSize = tasks.getTaskListSize();
            String[] deadlineString = userAction.split("/by ", 2);
            Task newDeadline = new Deadline(deadlineString[0], deadlineString[1]);
            tasks.addTask(newDeadline);
            assert tasks.getTaskListSize() == initialSize + 1 : WagwanUi.ADD_TASK_ERROR;
            storage.save();
            return ui.sendMessage(" Got it. I've added this task:\n" + "   " + newDeadline.toString()
                    + "\n Now you have " + tasks.getTaskListSize() + " tasks in the list.");
        } catch (DateTimeParseException e1) {
            throw new WagwanException(WagwanUi.INVALID_DATE);
        } catch (ArrayIndexOutOfBoundsException e2) {
            throw new WagwanException(WagwanUi.INVALID_DEADLINE);
        } catch (ClassCastException e3) {
            throw new WagwanException(WagwanUi.CLASS_CAST_ERROR);
        } catch (IOException e4) {
            throw new WagwanException(e4.getMessage());
        } catch (WagwanException e5) {
            return e5.toString();
        }
    }

    @Override
    public String toString() {
        return "this is a deadline command : deadline " + userAction;
    }
}
