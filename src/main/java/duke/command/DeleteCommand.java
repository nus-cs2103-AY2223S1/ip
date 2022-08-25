package duke.command;

import duke.DukeException;
import duke.util.TaskList;

public class DeleteCommand extends Command{
    private String input;

    public DeleteCommand(String input) {
        this.input = input;
    }

    @Override
    public void run(TaskList taskList) throws DukeException {
        int index = Integer.parseInt(input) - 1;
        try {
            taskList.deleteTask(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Index is out of bounds");
        }
    }
}
