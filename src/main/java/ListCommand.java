public class ListCommand extends Command {

    public ListCommand(String cmd) {
        super(cmd);
    }

    @Override
    void execute(Ui ui, TaskList taskList) throws DukeException {
        ui.showList();
        for (int i = 0; i < taskList.getSize(); i ++) {
            Task task = taskList.getTask(i);
            if (task == null) {
                break;
            }
            int index = i + 1;
            ui.showTask(index, task);
        }
    }
}
