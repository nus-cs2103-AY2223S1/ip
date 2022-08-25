package commands;

import byu.ToDoList;
import byu.Ui;

public class FindCommand extends Command {

    private String substring;

    public FindCommand(String substring) {
        this.substring = substring;
    }

    public void execute(ToDoList tasks, Ui ui) {
        tasks.find(this.substring);
    }

    public boolean isExit() {
        return false;
    }

}
