package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command{
    public ListCommand() {}

    /**
     * Method that executes the mark command to mark the specified task done or not done.
     *
     * @param tasks Task list containing the task to be marked.
     * @param ui Ui that will output messages to the user.
     * @param storage Storage on hard disk that stores the task list.
     * @return String listing the tasks in the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.printList(tasks);
    }
}
