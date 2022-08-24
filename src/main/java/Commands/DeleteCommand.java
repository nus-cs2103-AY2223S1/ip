package Commands;
import Models.Todo;
import Duck.TaskList;
import Duck.Storage;
import Duck.UI;

public class DeleteCommand extends Commands{
    private int index;
    public DeleteCommand(int index){
        this.index = index;
    }

    @Override
    public void execute(TaskList<Todo> list, Storage storage)  {
        Todo item = list.removeFromList(index);
        UI.deleteItemMessage(item);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
