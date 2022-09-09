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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ui.notifyFound();
        TaskList foundList = new TaskList(tasks.size());
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.isDescriptionAt(i, this.search)) {
                Task task = tasks.get(i);
                foundList.add(task);
                ui.printTask(task);
            }
        }

        return ui.printList(foundList);
    }
}
