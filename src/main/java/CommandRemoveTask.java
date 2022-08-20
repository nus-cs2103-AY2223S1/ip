public class CommandRemoveTask extends Command {

    private int index;

    public CommandRemoveTask(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {

        String taskStr = taskList.get(this.index).toString();
        taskList.remove(this.index);
        ui.showText("Your task " + taskStr + " has been deleted.");

    }
}