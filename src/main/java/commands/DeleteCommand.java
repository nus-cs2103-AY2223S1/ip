package commands;
import byu.*;
import exceptions.InvalidIndex;

public class DeleteCommand extends Command{

    int index;

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
