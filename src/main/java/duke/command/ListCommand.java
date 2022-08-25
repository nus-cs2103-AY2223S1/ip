package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class ListCommand extends Command {

    public ListCommand(String info) {
        super(info);
    }

    @Override
    public void execute(Ui ui, TaskList taskList) {
        ui.showList();

        for (int i = 0; i < taskList.getSize(); i++) {
            Task task = taskList.getTask(i);

            if (task == null) {
                break;
            }

            int index = i + 1;
            ui.showTask(index, task);
        }
    }
}
