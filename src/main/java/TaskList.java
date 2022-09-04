import java.util.List;
import java.util.ListIterator;

public class TaskList {
    private final List<Task> taskList;

    TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public List<Task> view() {
        return taskList;
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    public int getSize() {
        return taskList.size();
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public void delete(int index) {
        taskList.remove(index);
    }

    public ListIterator<Task> getListIterator() {
        return taskList.listIterator();
    }
}
