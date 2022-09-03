public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";

    private Todo todoTask;

    public TodoCommand(String description) {
        this.todoTask = new Todo(description);
    }
    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        tasks.addTask(this.todoTask);
        storage.appendTaskToFile(this.todoTask);
        ui.showAddTaskMessage(this.todoTask, tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
