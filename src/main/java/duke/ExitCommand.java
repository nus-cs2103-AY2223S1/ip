package duke;

import duke.Command;
import duke.Duke;

public class ExitCommand extends Command {
    @Override
    public void run(Duke duke) {
        duke.exit();
    }
}
