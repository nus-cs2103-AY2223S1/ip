package Commands;
import Models.Todo;
import Duck.TaskList;
import Duck.Storage;
import Duck.UI;

public class AddCommand extends Commands{
    private Todo item;
    public AddCommand(Todo item) {
        this.item = item;
    }
    @Override
    public void execute(TaskList<Todo> list, Storage storage) {
        list.addToList(item);
        UI.addNewItemMessage(item);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
