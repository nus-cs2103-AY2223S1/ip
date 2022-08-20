public class CommandUnmarkTask extends Command {

    private int index;

    public CommandUnmarkTask(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {

        taskList.get(this.index).setDone(false);
        taskList.mutatedTask();
        ui.showText("Your task " + taskList.get(this.index) + " has been un-completed.");

    }
}