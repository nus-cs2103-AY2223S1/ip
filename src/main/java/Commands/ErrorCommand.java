package Commands;
import Models.Todo;
import Duck.TaskList;
import Duck.Storage;

/**
 * Command to encapsulate an invalid command
 */
public class ErrorCommand extends Commands{
    public ErrorCommand(){

    }
    /**
     * since its an error, it should do nothing
     * @param list does nothing
     * @param storage does nothing
     */
    @Override
    public void execute(TaskList<Todo> list, Storage storage) {
        //do nothing
    }

    /**
     * see commands superclass
     * @return returns false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
