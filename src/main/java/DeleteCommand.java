package main.java;

public class DeleteCommand extends Command {

    private int integer;

    public DeleteCommand(int integer) {
        this.integer = integer;
    }

    @Override
    public boolean isExit() {
        return false;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showTaskDeleted(taskList, integer - 1);
        taskList.remove(integer);
        storage.saveFile(taskList);

    }
}
