package duke.command;

import duke.task.Task;
import duke.main.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Checker;
import java.util.ArrayList;

public class FindCommand extends Command {

    private String string;
    public FindCommand(String string) {
        this.string = string;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> arr = new ArrayList<>();
        for (Task task: tasks.getArr()) {
            if (Checker.contains(task, this.string)) {
                arr.add(task);
            }
        }
        ui.sayMatching(arr);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
