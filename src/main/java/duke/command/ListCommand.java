package duke.command;

import duke.TaskList;
import duke.UI;

public class ListCommand extends Command {
    public void execute(TaskList tasks) {
        UI.printList(tasks);
    }
}
