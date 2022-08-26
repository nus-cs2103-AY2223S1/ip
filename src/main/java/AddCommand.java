public class AddCommand extends Command{

    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.insertTask(this.task);
        storage.writeListToFile(tasks);
        ui.showResponse(String.format("%s\nNow you have %d tasks in the list.", 
                this.toString(), tasks.getNumOfTasks()));
    }

    @Override public String toString() {
        return String.format("Got it. I've added this task:\n    %s", this.task.toString());
    }
}
