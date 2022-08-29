package commands;

import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {

    private TaskList tasks;
    private String toFind;
    private Ui ui;

    public FindCommand(TaskList tasks, String toFind, Ui ui) {
        this.tasks = tasks;
        this.toFind = toFind;
        this.ui = ui;
    }

    public String execute() {
        return ui.showFound(tasks.find(toFind));
    }

}
