package command;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import task.Task;

public class MarkCommand extends Command {

    String str;

    public MarkCommand(String str) {
        this.str = str;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int index = Integer.parseInt(str.substring(5));
        if (index <= tasks.size() && index > 0) {
            Task task = tasks.getTasks().get(index - 1);
            if (!task.isDone()) {
                task.toggleDoneness();
                storage.saveLocalData(tasks.getTasks());
                System.out.println("Good job for doing this task!");
                System.out.println(task);
            } else {
                System.out.println("This task has already been marked done.");
                System.out.println(task);
            }
        } else {
            throw new DukeException("Index invalid, no such task exists.");
        }
    }
}
