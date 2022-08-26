import java.io.IOException;

/**
 * Marks a task as undone in the tasklist
 */
public class UnmarkCommand extends Command {
    boolean exit;
    private String userAction;
    public UnmarkCommand(String userAction) {
        this.exit = false;
        this.userAction = userAction;
    }
    public boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public void execute(TaskList tasks, DukeUi ui, Storage storage) throws DukeException {
        try {
            if (!isNumeric(userAction)) {
                throw new DukeException("I'm sorry, the input you provided is not a number!");
            } else {
                int index = Integer.parseInt(userAction) - 1;
                if (index >= tasks.getTaskListSize() || index < 0) {
                    throw new DukeException("I'm sorry, but the index you provided is out of range :-(");
                } else {
                    Task task = tasks.getTasks().get(index);
                    task.markAsUndone();
                    storage.save();
                    DukeUi.sendMessage(" Nice! I've marked this task as undone:\n" + "   " + task.toString());
                }
            }
        } catch (DukeException | IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

}

