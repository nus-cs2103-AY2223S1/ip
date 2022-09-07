package duke.command;

import duke.data.Storage;
import duke.data.TaskList;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.TodoTask;
import duke.util.DukeException;
import duke.util.Ui;

/**
 * Executes the command to add a task to the list.
 * @author Jicson Toh
 */
public class AddCommand extends Command {
    private final String action;
    private final String type;

    /**
     * Creates an add command object.
     * @param action user action input.
     * @param type task type of action.
     */
    public AddCommand(String action, String type) {
        this.action = action;
        this.type = type;
    }

    /**
     * Executes the command input.
     *  @param tasks   amends task list if any.
     * @param ui      ui to output feedback.
     * @param storage make changes to storage if any.
     * @return returns the command executed.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            switch (type) {
            case "todoTask":
                tasks.addTask(new TodoTask(action.substring(4).strip()));
                break;
            case "eventTask":
                int i = action.indexOf('/');
                String event = action.substring(i + 3).strip();
                tasks.addTask(new EventTask(action.substring(5, i).strip(), event));
                break;
            case "deadlineTask":
                int j = action.indexOf('/');
                String by = action.substring(j + 3).strip();
                tasks.addTask(new DeadlineTask(action.substring(8, j).strip(), by));
                break;
            default:
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            return ui.showAddedTask(tasks.getTaskString(tasks.getSize() - 1), tasks.getSize());
        } catch (Exception e) {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Returns true if exiting program.
     *
     * @return false if still running.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
