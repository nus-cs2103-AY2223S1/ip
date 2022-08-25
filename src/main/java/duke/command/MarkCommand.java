package duke.command;

import duke.DukeException;
import duke.util.TaskList;
import duke.util.Ui;

public class MarkCommand extends Command{
    private String input;

    public MarkCommand(String input) {
        this.input = input;
    }

    @Override
    public void run(TaskList taskList) throws DukeException {
        int index = Integer.parseInt(input) - 1;
        try {
            taskList.getTask(index).setDone();
            Ui.formatMessage("I've marked this duke.task as done! \n  "
                    + taskList.getTask(index).toString());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Index is out of bounds!");
        }
    }
}
