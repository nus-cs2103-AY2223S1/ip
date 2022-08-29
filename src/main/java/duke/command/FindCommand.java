package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class FindCommand extends Command{
    private String search;

    public FindCommand(String search) {
        this.search = search;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.notifyFound();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.isDescriptionAt(i, this.search)) {
                Task task = tasks.get(i);
                ui.printTask(task);
            }
        }
    }
}
