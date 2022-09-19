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
        try {
            storage.saveTaskList(taskList);
        } catch (IOException e) {
            System.out.println("Error while saving the text");
        }
        String text = MESSAGE + "\n\t" + task.toString() + "\n" +
                taskList.displayNumTasks();
        ui.displayMessage(text);

    }

    @Override
    public boolean isExit() {
        return false;
    }
}