package duke.command;

//import util
import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Storage;

//import exception
import duke.exception.NoTaskFoundExcpetion;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    private static final String TASK_LIST = "Here are the tasks in your list:";

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NoTaskFoundExcpetion {
        if (tasks.isEmpty()) {
            throw new NoTaskFoundExcpetion();
        }
        displayCommand(ui, TASK_LIST, tasks, tasks.getStatus());
    }
}
