package unc.command;

import unc.Storage;
import unc.TaskList;
import unc.Ui;
import unc.UncException;
import unc.task.Deadline;

/**
 * Command that adds a new Deadline to list when executed.
 */
public class DeadlineCommand extends Command {
    private final String description;

    /**
     * Constructor.
     *
     * @param input Description part of input following "deadline".
     */
    public DeadlineCommand(String input) {
        assert input != null : "A deadline task shouldn't be created with null description.";
        this.description = input;
    }

    /**
     * Adds a new Deadline to list.
     * Saves updated list.
     *
     * @param taskList {@inheritDoc}
     * @param ui {@inheritDoc}
     * @param storage {@inheritDoc}
     * @return the message to be shown by UI
     * @throws UncException If error occurs when creating new Deadline.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws UncException {
        String[] phrases = description.split(" /by ", 2);
        Deadline newDeadline = new Deadline(phrases[0], phrases[1]);
        taskList.add(newDeadline);
        storage.save(taskList);
        return ui.addDeadline(taskList, newDeadline);
    }

    /**
     * {@inheritDoc}
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
