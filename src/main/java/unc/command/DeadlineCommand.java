package unc.command;

import unc.*;
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
        this.description = input;
    }

    /**
     * Adds a new Deadline to list.
     * Saves updated list.
     *
     * @param taskList {@inheritDoc}
     * @param ui {@inheritDoc}
     * @param storage {@inheritDoc}
     * @throws UncException If error occurs when creating new Deadline.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws UncException {
        String[] phrases = description.split(" /by ", 2);
        Deadline newDeadline = new Deadline(phrases[0], phrases[1]);
        taskList.add(newDeadline);
        ui.addDeadline(taskList, newDeadline);
        storage.save(taskList);
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
