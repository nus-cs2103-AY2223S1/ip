package Command;
import Duke.Deadline;
import Duke.DukeUi;
import Duke.DukeException;
import Duke.Storage;
import Duke.Task;
import Duke.TaskList;
import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
* Command that adds a Deadline to the TaskList when executed.
*/
public class DeadlineCommand extends Command {
    boolean isExit;
    private String userAction;
    public DeadlineCommand(String userAction) {
        this.isExit = false;
        this.userAction = userAction;
    }

    @Override
    public String execute(TaskList tasks, DukeUi ui, Storage storage) throws DukeException {
        try {
            int initialSize = tasks.getTaskListSize();
            String[] deadlineString = userAction.split("/by ", 2);
            Task newDeadline = new Deadline(deadlineString[0], deadlineString[1]);
            tasks.addTask(newDeadline);
            assert tasks.getTaskListSize() == initialSize + 1 : DukeUi.ADD_TASK_ERROR;
            storage.save();
            return ui.sendMessage(" Got it. I've added this task:\n" + "   " + newDeadline.toString()
                    + "\n Now you have " + tasks.getTaskListSize() + " tasks in the list.");
        } catch (DateTimeParseException e1) {
            throw new DukeException(DukeUi.INVALID_DATE);
        } catch (ArrayIndexOutOfBoundsException e2) {
            throw new DukeException(DukeUi.INVALID_DEADLINE);
        } catch (ClassCastException e3) {
            throw new DukeException(DukeUi.CLASS_CAST_ERROR);
        } catch (IOException e4) {
            throw new DukeException(e4.getMessage());
        } catch (DukeException e5) {
            return e5.toString();
        }
    }

    @Override
    public String toString() {
        return "this is a deadline command : deadline " + userAction;
    }
}
