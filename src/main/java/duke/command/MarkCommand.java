package duke.command;
import duke.exception.DukeException;
import java.lang.IndexOutOfBoundsException;
import duke.exception.InvalidInputException;
import duke.Storage;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;


public class MarkCommand extends Command{
    private String index;

    public MarkCommand(String index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        try {
            int i = Integer.parseInt(this.index);
            Task task = tasks.get(i-1);
            task.complete();
            System.out.println("Okay, I have marked this task as done:");
            System.out.println(task);
        } catch (IndexOutOfBoundsException e) {

            throw new InvalidInputException(this.index, "mark");
        }
    }
}
