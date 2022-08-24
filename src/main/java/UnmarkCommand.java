public class UnmarkCommand extends Command{

    private int taskID;
    private Task unmarkedTask;

    public UnmarkCommand(int taskID) {
        this.taskID = taskID;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        this.unmarkedTask = tasks.unmarkTask(this.taskID);
        storage.writeListToFile(tasks);
        ui.showResponse(String.format("%s\nNow still you have %d tasks in the list.", 
                this.toString(), tasks.getNumOfTasks()));
    }

    @Override public String toString() {
        return String.format("Ok. I've unmarked this task:\n   %s", 
                this.unmarkedTask.toString());
    }
}

