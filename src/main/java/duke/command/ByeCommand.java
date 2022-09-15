package duke.command;

import duke.Parser;
import duke.task.TaskList;

public class ByeCommand extends Command {
    @Override
    public String execute(TaskList tasks) {
        return "Bye. Hope to see you again soon!";
    }
}
