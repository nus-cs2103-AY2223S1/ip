public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        Task task = taskList.markItem(index);
        ui.showOutput("Nice! I've marked this task as done:\n  " + task + "\n");
        storage.save(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
