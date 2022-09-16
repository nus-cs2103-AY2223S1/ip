package duke;

import duke.Command;
import duke.Duke;

public class ListCommand extends Command {
    @Override
    public void run(Duke duke) {
        duke.printTasks();
    }
}
