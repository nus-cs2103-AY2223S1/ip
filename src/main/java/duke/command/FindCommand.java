package duke.command;

import duke.component.TaskList;

/**
 * Represents a command to find tasks matching the given keyword.
 */
public class FindCommand extends Command {

    /**
     * Constructs a new FindCommand.
     *
     * @param content Content of the user command.
     * @param tasks List of all tasks.
     */
    public FindCommand(String content, TaskList tasks) {
        super(content, tasks);
    }

    /**
     * Finds tasks in the TaskList that match the keywords.
     *
     * @return String representation of the message in response to the command.
     */
    @Override
    public String run() {
        return this.tasks.findTasks(this.content.split("\\s+"));
    }
}
