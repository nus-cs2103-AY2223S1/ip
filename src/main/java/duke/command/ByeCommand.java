package duke.command;

import duke.Parser;
import duke.task.TaskList;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks) {
        Parser.printMsg("Bye. Hope to see you again soon!");
    }
}
