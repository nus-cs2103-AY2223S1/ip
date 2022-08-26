import Duke.DukeException;

import java.io.IOException;

/**
 * Handles a to-do command
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
                DukeUi.sendMessage(" Got it. I've added this task:\n" + "   " + newTodo.toString()
                        + "\n Now you have " + tasks.getTaskListSize() + " tasks in the list.");
            }
        } catch (DukeException | IOException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
