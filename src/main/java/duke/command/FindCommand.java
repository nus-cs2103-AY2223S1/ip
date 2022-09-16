package duke.command;

import duke.Ui;
import duke.task.TaskList;

/**
 * Class which inherits the Command class for a FindCommand.
 *
 * @author kaij77
 * @version 0.1
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Public constructor for a FindCommand.
     *
     * @param keyword the keyword being searched for
     */
    public FindCommand(String keyword) {
        this.keyword =keyword;
    }

    /**
     * Searches the TaskList for matching Tasks and prints the TaskList containing these Tasks.
     *
     * @param taskList A list of tasks
     * @param ui An ui responsible for printing output to the user
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.printSearchedList(taskList.searchTasks(this.keyword).toString());
    }
}
