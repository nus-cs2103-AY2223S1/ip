package commands;

import byu.TaskList;
import byu.Ui;

public class FindCommand extends Command {

    private String substring;

    public FindCommand(String substring) {
        this.substring = substring;
    }

    public void execute(TaskList tasks, Ui ui) {
        tasks.find(this.substring);
    }

    public boolean isExit() {
        return false;
    }

}
