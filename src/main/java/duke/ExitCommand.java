package duke;

public class ExitCommand extends Command {

    private boolean isExit;

    public ExitCommand() {
        this.isExit = false;
    }

    @Override
    public TaskList execute(TaskList taskList, Ui ui, Storage storage) {
        this.isExit = true;
        ui.showExit();
        return taskList;
    }

    @Override
    public boolean isExit() {
        return this.isExit;
    }
}
