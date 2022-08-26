import java.util.ArrayList;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> list = taskList.getTaskArrayList();
        if ((index > 0) && (list.get(index - 1) != null)) {
            Task t = list.get(index - 1);
            taskList.mark(this.index);
            ui.showMark(t);
        } else {
            String s = "☹ OOPS!!! The index of the task to be marked/unmarked/deleted must be valid/within range.";
            throw new DukeException(s);
        }
    }
}
