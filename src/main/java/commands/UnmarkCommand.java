package commands;

import byu.*;
import exceptions.InvalidIndex;

public class UnmarkCommand extends Command {

    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    public void execute(ToDoList list, Ui ui) throws InvalidIndex {
        list.unmark(this.index);
    }

    public boolean isExit() {
        return false;
    }

}
