package manmeowth.command;

import manmeowth.list.TaskList;
import manmeowth.storage.ListLoader;
import manmeowth.ui.Ui;

/**
 * Represents command to ManMeowth to find tasks in list.
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
    public String execute(TaskList tasks, Ui ui, ListLoader storage) {
        return ui.showFindList(tasks.giveFindList(content));
    }
}
