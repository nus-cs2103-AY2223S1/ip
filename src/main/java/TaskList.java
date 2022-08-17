import java.util.ArrayList;
import java.util.Arrays;

public class TaskList {

    private ArrayList<Task> taskList;

    TaskList() {
        this.taskList = new ArrayList<>();
    }

    TaskList(Task... taskList) {
        this.taskList = new ArrayList<>(Arrays.asList(taskList));
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public void deleteTask(int index) {
        this.taskList.remove(index);
    }

    public String[] toStringList() {
        return this.taskList.stream()
                .map(Task::toString)
                .toArray(String[]::new);
    }

    public int getLength() {
        return this.taskList.size();
    }

    public Task getTask(int index) {
        return this.taskList.get(index);
    }
}
