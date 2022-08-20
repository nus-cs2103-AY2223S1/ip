public class CommandMarkTask extends Command {

    private int index;

    public CommandMarkTask(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {

        taskList.get(this.index).setDone(true);
        taskList.mutatedTask();
        ui.showText("Your task " + taskList.get(this.index) + " has been completed.");

    }
}