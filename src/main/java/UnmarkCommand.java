public class UnmarkCommand extends Command {
    private int num;
    public UnmarkCommand(int num) {
        this.num = num;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            tasks.arr.get(this.num - 1).unmark();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("How to mark something that is not inside??");
        }
        ui.sayUnmarked(this.num, tasks.arr);
        storage.overwrite();
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
