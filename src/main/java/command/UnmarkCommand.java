package command;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import task.Task;

public class UnmarkCommand extends Command {

    private String str;

    public UnmarkCommand(String str) {
        this.str = str;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            int index = Integer.parseInt(str.substring(7));
            if (index <= tasks.size() && index > 0) {
                StringBuilder output = new StringBuilder();
                Task task = tasks.getTasks().get(index - 1);
                if (task.isDone()) {
                    task.toggleDoneness();
                    storage.saveLocalData(tasks.getTasks());
                    output.append("Task shall be marked as undone.\n");
                    output.append(task.toString());
                } else {
                    output.append("This task has already been marked undone.\n");
                    output.append(task.toString());
                }
                return output.toString();
            } else {
                throw new DukeException("Index invalid, no such task exists.");
            }
        } catch (NumberFormatException e) {
            throw new DukeException("Index invalid, please enter an integer.");
        }
    }
}
