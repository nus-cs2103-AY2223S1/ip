package commands;

import byu.ToDoList;
import byu.Ui;

import exceptions.InvalidIndex;

public class DeleteCommand extends Command{

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    public void execute(ToDoList list, Ui ui) throws InvalidIndex {
        list.delete(this.index);
    }

    public boolean isExit() {
        return false;
    }
}
