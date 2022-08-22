public class MarkTaskCommand extends Command {
    private boolean isDone;
    private int index;

    public MarkTaskCommand(boolean isDone, int index) {
        this.isDone = isDone;
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (!taskList.isValidIndex(this.index)) {
            ui.showInvalidIndex();
            return;
        }
        if (this.isDone == taskList.getIsDone(index)) {
            ui.showAlreadyMarked(isDone);
            return;
        }
        ui.showStatusChange(taskList.changeStatus(index, this.isDone), this.isDone);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
