package duke;

import duke.Command;
import duke.Duke;

public class DeleteCommand extends Command {
    int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void run(Duke duke) {
        duke.deleteTask(index);
    }
}
