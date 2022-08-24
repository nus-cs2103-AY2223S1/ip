package Commands;
import Models.Todo;
import Duck.TaskList;
import Duck.Storage;
import Duck.UI;

public class MarkCommand extends Commands{
    private int index;
    public MarkCommand(int index){
        this.index = index;
    }

    @Override
    public void execute(TaskList<Todo> list, Storage storage) {
        Todo item = list.get(index);
        item.completeTask();
        UI.markItemMessage(item);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
