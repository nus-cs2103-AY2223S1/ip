package duke.command;

import duke.list.TaskList;
import duke.storage.ListLoader;
import duke.ui.Ui;

/**
 * Represents command to Duke to find tasks in list.
 *
 * @author WR3nd3
 */
public class FindCommand extends Command {
    private final String content;

    /**
     * Constructs FindCommand for a tasks containing the String content.
     *
     * @param content String representing description of task.
     */
    public FindCommand(String content) {
        this.content = content;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, Ui ui, ListLoader storage) {
        ui.showFindList(tasks.giveFindList(content));
    }
}
