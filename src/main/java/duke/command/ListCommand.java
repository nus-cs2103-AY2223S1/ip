package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

/**
 * Command to list every task in the TaskList.
 */
public class ListCommand extends Command {

    /**
     * Constructor for ListCommand.
     *
     * @param cmd Type of command
     */
    public ListCommand(String cmd) {
        super(cmd);
    }

    /**
     * Execute the list command by going through
     * the TaskList one by one and passing it
     * through UI to show the details of tasks.
     *
     * @param ui Ui to show List operation messages
     * @param taskList TaskList to execute List command
     * @throws DukeException If invalid commands or arguments
     */
    @Override
    public void execute(Ui ui, TaskList taskList) throws DukeException {
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
