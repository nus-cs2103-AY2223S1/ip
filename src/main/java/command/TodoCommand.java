package command;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import task.Todo;

public class TodoCommand extends Command {

    String str;

    public TodoCommand(String str) {
        this.str = str;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String sub = str.substring(5).trim();
        if (!sub.isEmpty()) {
            tasks.addTask(new Todo(str.substring(5)));
            storage.saveLocalData(tasks.getTasks());
            System.out.println("Got it, I've added this task:");
            System.out.println(tasks.getTasks().get(tasks.size() - 1));
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } else {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }
}
