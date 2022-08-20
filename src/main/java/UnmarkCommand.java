public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = taskList.unmarkTask(index);
        ui.printWithIndent("OK, I've marked this task as not done yet:");
        ui.printWithIndent("  " + task);
        storage.saveFile(taskList);
    }
}
