package duke;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public int size() {
        return taskList.size();
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public void addFromStorage(Task task) {
        taskList.add(task);
    }

    public Task delete(int i) {
        Task toDelete = taskList.remove(i - 1);
        return toDelete;
    }

    public Task mark(int index) {
        Task toMark = taskList.get(index - 1);
        toMark.markAsDone();
        return toMark;
    }

    public Task unmark(int index) {
        Task toUnmark = taskList.get(index - 1);
        toUnmark.markAsUndone();
        return toUnmark;
    }

    public Task get(int i) {
        return taskList.get(i);
    }

    public void print() {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + "." + taskList.get(i).toString());
        }
    }
}
