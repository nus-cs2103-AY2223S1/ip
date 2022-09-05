/**
 * Project Duke CS2103
 * Done by Hong Jin.
 */
package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * public class PriorityCommand that prioritizes command based on index user input.
 */
public class PriorityCommand extends Command {
    private int index;
    private int priorityLevel;

    /**
     * public constructor for PriorityCommand.
     * @param index of task in list to prioritize.
     */
    public PriorityCommand(int priorityLevel, int index) {
        super();
        assert index >= 0 : "index should be not negative";
        this.priorityLevel = priorityLevel;
        this.index = index;
    }

    /**
     * public method execute to execute the prioritization command.
     * @param tasks
     * @param ui
     * @param storage
     * @return
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (index > tasks.getSize() || index <= 0) {
            return ui.printMessage("Index is out of range!");
        }
        storage.saveToFile(tasks.saveList());
        return ui.printPriority(priorityLevel, tasks.get(index));
    }
}
