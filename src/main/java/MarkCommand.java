package main.java;

public class MarkCommand extends Command {

    private int integer;

    public MarkCommand(int integer) {
        this.integer = integer;
    }
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.markAsDone(integer);
        ui.showMarkAsDone(taskList, integer);
        storage.saveFile(taskList);
    }
}
