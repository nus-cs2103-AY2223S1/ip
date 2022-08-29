package duke.command;

//import util
import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Storage;

//import exception
import duke.exception.TaskMarkException;
import duke.exception.TaskNotFoundException;

//import task
import duke.task.Task;

public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    private static final String TASK_ADD = "Good Job! I will mark this task as done: ";
    public int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TaskMarkException, TaskNotFoundException {
        Task task = tasks.markTask(index);
        storage.update(tasks, ui);
        displayCommand(ui, TASK_ADD, task, tasks.getStatus());
    }
}
