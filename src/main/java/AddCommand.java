import java.io.IOException;

public class AddCommand extends Command {

    protected Task task;
    private final String MESSAGE = "\tGot it. I just added the " +
            "task: ";

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(task);
        storage.saveTaskList(taskList);
        String text = MESSAGE + "\n\t" + task.toString() + "\n" +
                taskList.displayNumTasks();
        ui.displayMessage(text);

    }

    @Override
    public boolean isExit() {
        return false;
    }
}