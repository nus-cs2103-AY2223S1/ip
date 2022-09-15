package duke.command;

import duke.Ui;

/**
 * Command for listing Tasks.
 */
public class ListCommand extends Command {
    /**
     * Constructor for ListCommad.
     */
    public ListCommand() {
        super.isExit = false;
    }

    /**
     * {@inheritDoc}
     * Displays Tasks in TaskList.
     */
    @Override
    public String execute() {
        return Ui.getTaskListMessage(Command.taskList.getTasks());
    }
}
