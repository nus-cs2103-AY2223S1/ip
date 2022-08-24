public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(this.task);
        ui.botReply("Got it. I've added this task:\n " + this.task +
                "\nNow you have " + tasks.length() + " tasks in the list.");
        storage.saveTasks(tasks);
    }
}