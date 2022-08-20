public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        Task task = taskList.deleteItem(index);
        ui.showOutput("Noted. I've removed this task:\n  " + task + "\n");
        storage.save(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
