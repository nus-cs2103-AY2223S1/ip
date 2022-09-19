import java.io.IOException;

public class DeleteCommand extends Command {

    protected int index;
    private final String MESSAGE = "\tNoted. I've remove this task: ";

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = taskList.deleteTask(index);
        try {
            storage.saveTaskList(taskList);
        } catch (IOException e) {
            System.out.println("Error while saving the text");
        }
        String text = MESSAGE + "\n\t" + task.toString() +
                "\n" + taskList.displayNumTasks();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}