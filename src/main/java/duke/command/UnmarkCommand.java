package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class UnmarkCommand extends Command {

    /**
     * String array containing the arguments for the unmark command.
     */
    private String[] arg;

    public UnmarkCommand(String[] arg) {
        this.arg = arg;
    }

    /**
     * Marks task as undone at the specified index in the task list.
     *
     * @param tasks List of tasks.
     * @param ui User interface for duke.
     * @param storage Storage information for tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        int i = Integer.parseInt(arg[1]);
        assert i >=0;
        if (i <= tasks.getCount()) {
            tasks.get(i - 1).setIncomplete();
            storage.write(tasks);

            StringBuilder toReturn = new StringBuilder();
            toReturn.append("OK, I have marked this task as not done yet: \n");
            toReturn.append(tasks.get(i - 1) + "\n");
            return toReturn.toString();
        } else {
           throw new DukeException("Index does not exist");
        }
    }
}
