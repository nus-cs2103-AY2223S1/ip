package duke.command;

import duke.Parser;
import duke.task.TaskList;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks) {
        Parser.printMsg(String.format("Here are the tasks in your list:\n%s",
                tasks));
    }
}
