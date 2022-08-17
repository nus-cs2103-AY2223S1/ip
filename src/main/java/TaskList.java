import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> taskList = new ArrayList<>();

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    private Task getTask(int index) {
        return this.taskList.get(index);
    }

    public Task deleteTask(int index) throws DukeException{
        if (index < 0 || index >= this.getSize()) {
            throw new DukeException("Invalid task number.");
        } else {
            Task task = this.taskList.get(index);
            this.taskList.remove(index);
            return task;
        }
    }

    public int getSize() {
        return this.taskList.size();
    }

    public Task markDone(int index) throws DukeException{
        if (index < 0 || index >= this.getSize()) {
            throw new DukeException("Invalid task number.");
        } else {
            Task task = this.getTask(index);
            task.markDone();
            return task;
        }
    }

    public Task unmarkDone(int index) throws DukeException{
        if (index < 0 || index >= this.getSize()) {
            throw new DukeException("Invalid task number.");
        } else {
            Task task = this.getTask(index);
            task.markUndone();
            return task;
        }
    }

    @Override
    public String toString() {
        String[] stringList = new String[this.taskList.size()];
        for (int i = 0; i < this.taskList.size(); i++) {
            stringList[i] = (i + 1) + ". " + this.getTask(i) + "\n";
        }
        return String.join("", stringList);
    }
}
