public class UnmarkCommand extends Command {
    private int num;
    public UnmarkCommand(String cmd, int num) {
        super(cmd);
        this.num = num;
    }

    @Override
    void execute(Ui ui, TaskList taskList) throws DukeException {
        Task task = taskList.getTask(num);
        task.unmarkT();
        ui.showUnmarkMsg(task);
    }
}
