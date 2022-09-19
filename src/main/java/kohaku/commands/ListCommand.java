package kohaku.commands;

import kohaku.datastruct.TaskList;

public class ListCommand extends Command{

    private TaskList tasks;

    /**
     * Initialises a list command with a provided tasklist
     *
     * @param tasks Tasklist to be listed
     */
    public ListCommand(TaskList tasks, String args) {
        this.tasks = tasks;
    }

    /**
     * Executes the command.
     * @return The string representation of the result of the command.
     */
    @Override
    public String execute() {
        return this.tasks.toString();
    }
}
