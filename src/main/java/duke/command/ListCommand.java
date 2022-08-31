package duke.command;

import duke.TaskList;
import duke.Ui;

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
    public String execute(Ui ui, TaskList taskList) {
        String output = ui.showList() + "\n";

        return output + ui.showTaskList(taskList);
    }
}
