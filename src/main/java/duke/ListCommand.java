package duke;

/**
 * Encapsulate a command that allows user see all existing items in TaskList,
 * which is-a Command.
 */
public class ListCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showList(tasks);
    }
}
