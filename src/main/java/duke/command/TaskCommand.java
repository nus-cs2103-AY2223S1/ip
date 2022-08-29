package duke.command;

//import util
import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Storage;

//import task
import duke.task.Task;


public abstract class TaskCommand extends Command {
    public static final String TASK_ADD = "Got it. I've added this task";
    private Task task;

    public TaskCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        storage.update(tasks, ui);
        displayCommand(ui, TASK_ADD, task, tasks.getStatus());
    }
}
