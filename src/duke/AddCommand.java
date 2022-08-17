package duke;

public class AddCommand extends Command{

    private Task task;

    public AddCommand(String task) {
        this.task = new ToDo(task);
    }
    @Override
    public void execute(Ui ui, TaskList taskList) {
        taskList.addTask(task);
        ui.addSuccess(task);
    }
}
