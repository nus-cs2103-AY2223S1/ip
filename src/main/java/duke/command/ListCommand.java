package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Command to list every task in the TaskList.
 */
public class ListCommand extends Command {

    /**
     * Constructor for ListCommand.
     *
     * @param info Type of command
     */
    public ListCommand(String info) {
        super(info);
    }

    /**
     * Execute the list command by going through
     * the TaskList one by one and passing it
     * through UI to show the details of tasks.
     *
     * @param ui Ui to show List operation messages
     * @param taskList TaskList to execute List command
     */
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
