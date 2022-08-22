public class OnGoingCommand extends Command {
    private final int index;

    public OnGoingCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        taskList.unMarkDone(index);
        String message = "OK, I've marked this task as not done yet:\n\t\t" + taskList.taskToString(this.index);
        ui.printWithDivider(message);
    }
}
