package duke.commands;

import duke.tasks.TaskList;
import duke.ui.Ui;

public class PrintTasksCommand extends Command {

    private Ui ui;
    private TaskList tasks;

    public PrintTasksCommand(Ui ui, TaskList tasks) {
        this.ui = ui;
        this.tasks = tasks;
    }

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
