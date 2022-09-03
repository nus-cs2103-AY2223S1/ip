package duke.command;

import duke.TaskList;

public class FindCommand extends Command {
    String description;
    
    public FindCommand(String description) {
        this.description = description;
    }
    
    // must search by description (and not date/time)
    public String execute(TaskList tasks) {
        return tasks.findTasks(description);
    }
}
