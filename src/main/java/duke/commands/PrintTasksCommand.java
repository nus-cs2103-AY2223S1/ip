package duke.commands;

import duke.tasks.TaskList;

/**
 * A command that displays the list of tasks.
 */
public class PrintTasksCommand extends Command {

    private TaskList tasks;

    /**
     * Constructs a new command that displays the list of tasks.
     * @param tasks TaskList object to display tasks from.
     */
    public PrintTasksCommand(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Obtains the list of Tasks.
     * @return Response message.
     */
    @Override
    public String execute() {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n").append(tasks.toString());
        return sb.toString();
    }

    /**
     * Does nothing.
     * @return A string indicating that there is nothing to undo.
     */
    @Override
    public String undo() {
        return "There is nothing to undo for your previous list tasks command.";
    }
}
