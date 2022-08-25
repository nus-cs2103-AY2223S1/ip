package command;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
public class DeleteCommand extends Command {

    String str;

    public DeleteCommand(String str) {
        this.str = str;
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int index = Integer.parseInt(str.substring(7));
        if (index <= tasks.size() && index > 0) {
            System.out.println("Noted. I've removed this task:");
            System.out.println(tasks.removeTask(index - 1));
            storage.saveLocalData(tasks.getTasks());
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } else {
            throw new DukeException("Index invalid, no such task exists.");
        }
    }
}
