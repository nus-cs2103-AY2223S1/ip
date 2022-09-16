package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * MarkCommand marks the task specified as completed.
 */
public class MarkCommand extends Command{

    /**
     * String array containing arguments for the mark command.
     */
    private String[] arg;

    /**
     * Creates a new MarkCommand.
     *
     * @param arg String array containing arguments from the user input.
     */
    public MarkCommand(String[] arg) {
        this.arg = arg;
    }

    /**
     * Marks the task at the specified index as done if it exists.
     *
     * @param tasks List of tasks.
     * @param ui User interface for duke.
     * @param storage Storage information for tasks.
     * @return String output to be displayed by duke.
     * @throws DukeException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int i = Integer.parseInt(arg[1]);
        assert i >= 0;
        if (i  <= tasks.getCount()) {
            tasks.get(i - 1).setComplete();
            storage.write(tasks);
            StringBuilder toReturn = new StringBuilder();
            toReturn.append("Nice! I have marked this task as done: \n");
            toReturn.append(tasks.get(i - 1) + "\n");
            return toReturn.toString();
        } else {
            throw new DukeException("Index does not exist");
        }
    }
}
