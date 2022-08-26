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
    boolean exit;
    private String userAction;
    public TodoCommand(String userAction) {
        this.exit = false;
        this.userAction = userAction;
    }

    @Override
    public void execute(TaskList tasks, DukeUi ui, Storage storage) throws DukeException {
        try {
            Task newTodo = new Todo(userAction);
            if (this.userAction.equals("")) {
                throw new DukeException("The description of a todo cannot be empty.");
            } else{
                tasks.addTask(newTodo);
                storage.save();
                ui.sendMessage(" Got it. I've added this task:\n" + "   " + newTodo.toString()
                        + "\n Now you have " + tasks.getTaskListSize() + " tasks in the list.");
            }
        } catch (DukeException | IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "this is a todo command : todo " + userAction;
    }
}
