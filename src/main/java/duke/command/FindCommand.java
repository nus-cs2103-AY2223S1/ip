package duke.command;

import duke.TaskList;
import duke.UI;

public class FindCommand extends Command {
    String description;
    
    public FindCommand(String description) {
        this.description = description;
    }
    
    // must search by description (and not date/time)
    public void execute(TaskList tasks) {
        UI.print(tasks.findTasks(description));
    }
}
