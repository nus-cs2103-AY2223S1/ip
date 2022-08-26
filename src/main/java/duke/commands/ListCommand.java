package duke.commands;
import duke.TaskList;
import duke.Storage;
import duke.DukeException;
public class ListCommand implements Command {

    public ListCommand() {}

    @Override
    public void execute(TaskList tasks, Storage storage) throws DukeException {
        for (int i = 0; i < tasks.getSize(); i++) {
            int index = i+ 1;
            System.out.println(index + ". " + tasks.get(i));
        }
    }
}
