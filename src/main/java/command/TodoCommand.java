package command;

import java.io.IOException;

import wagwan.Storage;
import wagwan.Task;
import wagwan.TaskList;
import wagwan.Todo;
import wagwan.WagwanException;
import wagwan.WagwanUi;

/**
* Command that adds a To-Do to the TaskList when executed.
*/
public class TodoCommand extends Command {

    private boolean isExit;
    private String userAction;

    /**
     * Constructor for a to-do command.
     * @param userAction the to-do description.
     */
    public TodoCommand(String userAction) {
        this.isExit = false;
        this.userAction = userAction;
    }

    @Override
    public String execute(TaskList tasks, WagwanUi ui, Storage storage) throws WagwanException {
        try {
            int initialSize = tasks.getTaskListSize();
            Task newTodo = new Todo(userAction);
            tasks.addTask(newTodo);
            assert tasks.getTaskListSize() == initialSize + 1 : WagwanUi.ADD_TASK_ERROR;
            storage.save();
            return ui.sendMessage(" Got it. I've added this task:\n" + "   " + newTodo.toString()
                    + "\n Now you have " + tasks.getTaskListSize() + " tasks in the list.");
        } catch (IOException e1) {
            throw new WagwanException(e1.getMessage());
        } catch (ClassCastException e2) {
            throw new WagwanException(WagwanUi.CLASS_CAST_ERROR);
        } catch (WagwanException e3) {
            return e3.toString();
        }
    }

    @Override
    public String toString() {
        return "this is a todo command : todo " + userAction;
    }
}
