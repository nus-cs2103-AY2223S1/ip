package duke;

import duke.exceptions.CannotFindTaskException;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>(100);
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public Task toggleTaskStatus(Task task) throws CannotFindTaskException{
        for (int i = 0; i < taskList.size(); i ++) {
            Task curr = taskList.get(i);
            if (curr.equals(task)) {
                curr.toggleStatus();
                return curr;
            }
        }
        throw new CannotFindTaskException();
    }
    @Override
    public String toString() {
        Object[] taskArr = this.taskList.toArray();
        String result = "";
        for (int i = 0; i < this.taskList.size(); i++) {
            result += (i + 1) + ". " + taskArr[i].toString() + "\n";
        }
        return result;
    }
}
