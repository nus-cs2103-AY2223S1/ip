package duke.commands;

import duke.tasks.TaskList;

public class PrintTasksCommand extends Command {

    private TaskList tasks;

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

    @Override
    public String undo() {
        return "There is nothing to undo for your previous list tasks command.";
    }
}
