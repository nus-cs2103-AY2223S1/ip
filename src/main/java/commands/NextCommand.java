package commands;

import byu.ToDoList;
import byu.Ui;

/**
 * Represents a command to do nothing and move ot the next command.
 */
public class NextCommand extends Command {

    public void execute(ToDoList list, Ui ui) {
    }

    public boolean isExit() {
        return false;
    }

}
