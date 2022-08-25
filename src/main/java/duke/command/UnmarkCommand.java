package duke.command;

import duke.DukeException;
import duke.util.TaskList;
import duke.util.Ui;

public class UnmarkCommand extends Command{
    private String input;

    public UnmarkCommand(String input) {
        this.input = input;
    }

    @Override
    public void run(TaskList taskList) throws DukeException {
        int index = Integer.parseInt(input) - 1;
        try {
            taskList.getTask(index).setUndone();
            Ui.formatMessage("I've marked this duke.task as not done.. \n  "
                    + taskList.getTask(index).toString());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Index is out of bounds!");
        }
    }
}
