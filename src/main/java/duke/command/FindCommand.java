package duke.command;

import duke.TaskList;

/**
 * Represents a command to find all tasks that contain a certain keyword.
 */
public class FindCommand extends Command {
    private final String description;
    
    public FindCommand(String description) {
        this.description = description;
    }

    /**
     * {@inheritDoc}
     */
    public String execute(TaskList tasks) {
        return tasks.findTasks(description);
    }
}
