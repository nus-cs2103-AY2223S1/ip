package commands;

import byu.ToDoList;
import byu.Ui;

import exceptions.InvalidIndex;

public class ByeCommand extends Command {

    public void execute(ToDoList list, Ui ui) throws InvalidIndex {
        ui.exit();
        list.save();
    }

    public boolean isExit() {
        return true;
    }
}
