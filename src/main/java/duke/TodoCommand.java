package duke;

import java.io.IOException;
import java.util.ArrayList;

public class TodoCommand extends Command{

    @Override
    void execute(String taskName, ArrayList<Task> listOfTasks, Ui ui, Storage storage) throws IOException, DukeTodoEmptyException {
        if(taskName.length() == 4){
            throw new DukeTodoEmptyException();
        }
        TaskList taskList = new TaskList(listOfTasks);
        Task t = new ToDos(taskName.substring(5), false);
        taskList.addToList(t);
    }
}
