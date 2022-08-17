import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> taskList = new ArrayList<>();

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    public int getSize() {
        return this.taskList.size();
    }

    public boolean markDone(int index) {
        if (index < 0 || index >= this.getSize()) {
            return false;
        } else {
            Task task = this.getTask(index);
            task.markDone();
            return true;
        }
    }

    public boolean unmarkDone(int index) {
        if (index < 0 || index >= this.getSize()) {
            return false;
        } else {
            Task task = this.getTask(index);
            task.markUndone();
            return true;
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
