public class MarkCommand extends Command {

    private int num;
    public MarkCommand(String cmd, int num) {
        super(cmd);
        this.num = num;
    }
    @Override
    void execute(Ui ui, TaskList taskList) throws DukeException {
        Task task = taskList.getTask(num);
        task.markT();
        ui.showMarkMsg(task);
    }
}
