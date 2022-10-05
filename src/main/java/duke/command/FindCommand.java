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

    /**
     * Method that executes the find command to find tasks with descriptions containing the search input.
     *
     * @param tasks Task list containing the task to be marked.
     * @param ui Ui that will output messages to the user.
     * @param storage Storage on hard disk that stores the task list.
     * @return String that shows all tasks with matching descriptions.
     */
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
