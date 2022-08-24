package Commands;
import Models.Todo;
import Duck.TaskList;
import Duck.Storage;

public class ExitCommand extends Commands{
    public ExitCommand(){
    }
    @Override
    public void execute(TaskList<Todo> list, Storage storage) {
        storage.writeListToFile(list);
    }
    @Override
    public boolean isExit() {
        return true;
    }
}
