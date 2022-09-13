package Command;
import Duke.DukeException;
import Duke.DukeUi;
import Duke.Storage;
import Duke.Task;
import Duke.TaskList;
import Duke.Todo;
import java.io.IOException;

/**
* Command that adds a To-Do to the TaskList when executed.
*/
public class TodoCommand extends Command {
    boolean isExit;
    private String userAction;
    public TodoCommand(String userAction) {
        this.isExit = false;
        this.userAction = userAction;
    }

    @Override
    public String execute(TaskList tasks, DukeUi ui, Storage storage) throws DukeException {
        try {
            int initialSize = tasks.getTaskListSize();
            Task newTodo = new Todo(userAction);
            tasks.addTask(newTodo);
            assert tasks.getTaskListSize() == initialSize + 1 : DukeUi.ADD_TASK_ERROR;
            storage.save();
            return ui.sendMessage(" Got it. I've added this task:\n" + "   " + newTodo.toString()
                    + "\n Now you have " + tasks.getTaskListSize() + " tasks in the list.");
        } catch (IOException e1) {
            throw new DukeException(e1.getMessage());
        } catch (ClassCastException e2) {
            throw new DukeException(DukeUi.CLASS_CAST_ERROR);
        } catch (DukeException e3) {
            return e3.toString();
        }
    }

    @Override
    public String toString() {
        return "this is a todo command : todo " + userAction;
    }
}
