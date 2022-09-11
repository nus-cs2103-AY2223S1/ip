package gina.commands;

import gina.GinaException;
import gina.Storage;
import gina.TaskList;
import gina.Ui;
import gina.task.Deadline;

/**
 * Represents the command to create a deadline.
 */
public class DeadlineCommand extends Command {
    private final String input;

    /**
     * Constructs a command to create a new deadline with the specified input.
     *
     * @param input The specified user input.
     */
    public DeadlineCommand(String input) {
        this.input = input;
        assert(input != null);
    }

    /**
     * {@inheritDoc}
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws GinaException {
        if (input.isBlank()) {
            throw new GinaException("Hold up! Description cannot be empty!");
        }

        String[] str = input.split(" /by ", 2);
        if (str.length < 2 || str[1].trim().length() == 0) {
            throw new GinaException("Wait! You need to tell me the description AND date.");
        }
        Deadline newDeadline = new Deadline(str[0], str[1]);

        taskList.addTask(newDeadline);
        storage.save(taskList);
        return ui.showAddTask(newDeadline, taskList);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isExit() {
        return false;
    }
}
