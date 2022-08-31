package kirby.commands;

import java.io.IOException;

import kirby.Storage;
import kirby.TaskList;
import kirby.Ui;
import kirby.exceptions.KirbyMissingArgumentException;
import kirby.tasks.Deadline;

/**
 * DeadlineCommand class handles the command to create a Deadline task.
 */
public class DeadlineCommand extends Command {
    private String inputString;

    /**
     * Constructor for the class DeadlineCommand.
     *
     * @param inputString Arguments of a command.
     */
    public DeadlineCommand(String inputString) {
        this.inputString = inputString;
    }

    /**
     * {@inheritDoc}
     * Creates a Deadline task if arguments are valid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws KirbyMissingArgumentException {
        if (!inputString.contains("/by") || inputString.length() - 1 < inputString.indexOf("/by") + 4 || inputString.indexOf(" /by") <= inputString.indexOf("deadline") + 9) {
            throw new KirbyMissingArgumentException("deadline");
        }
        String taskName = inputString.substring(inputString.indexOf("deadline") + 9, inputString.indexOf(" /by"));
        String by = inputString.substring(inputString.indexOf("/by") + 4);
        Deadline deadline = new Deadline(taskName, by);
        tasks.addTask(deadline);
        try {
            storage.writeTask(tasks.getList());
        } catch (IOException e) {
            return (e.getMessage());
        }
        return tasks.addTaskString(deadline);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
