package duke.command;
import duke.exception.DukeException;
import duke.exception.InvalidInputException;
import duke.Storage;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;


public class UnmarkCommand extends Command{
    public String index;

    public UnmarkCommand(String index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        try {
            int i = Integer.parseInt(this.index);
            Task task = tasks.get(i-1);
            task.undo();
            System.out.println("Okay, I have marked this task as not yet done:");
            System.out.println(task);
        } catch (IndexOutOfBoundsException e) {

            throw new InvalidInputException(this.index, "unmark");
        }
    }
}
