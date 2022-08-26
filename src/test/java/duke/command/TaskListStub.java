package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.task.Task;

import java.util.ArrayList;

public class TaskListStub extends TaskList {
    private ArrayList<Task> data;
    
    public TaskListStub() throws DukeException {
        super(new Storage("././././data/duke.txt"), new UI());
        this.data = new ArrayList<>();
    }

    @Override
    public void addTask(Task task) {
        data.add(task);
    }
    
    public boolean checkTask(int pos, String string) {
        return data.get(pos).toString().equals(string);
    }
}
