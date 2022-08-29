public class AddTaskCommand extends Command {
    private String taskInput;

    public AddTaskCommand(String taskInput) {
        this.taskInput = taskInput;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task newTask = Task.of(this.taskInput, false);
            tasks.add(newTask);
            ui.sayTaskAdded(newTask);
        } catch (IanaException e) {
            ui.echo(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
