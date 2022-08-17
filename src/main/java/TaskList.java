import java.util.ArrayList;

public class TaskList {

    protected final ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<Task>();
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < this.taskList.size(); i++) {
            Task task = this.taskList.get(i);
            String line = String.format("%s.%s", i+1, task.toString());
            result = String.format("%s%s", result, line);
            if (i < this.taskList.size() - 1) {
                result = result.concat("\n");
            }
        }
        return result;
    }

    public int getSize() {
        return this.taskList.size();
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public void removeTask(int num) {
        this.taskList.remove(num-1);
    }

    public Task getTask(int num) {
        return this.taskList.get(num-1);
    }
}

