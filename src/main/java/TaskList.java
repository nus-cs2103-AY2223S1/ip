import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;


public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public Task delete(int taskNum) {
        return this.tasks.remove(taskNum - 1);
    }

    public Task get(int taskNum) {
        return this.tasks.get(taskNum - 1);
    }

    public int getLength() {
        return this.tasks.size();
    }

    public void markAsDone(int taskNum) {
        this.get(taskNum).markAsDone();
    }

    public void unmarkAsDone(int taskNum) {
        this.get(taskNum).unmarkAsDone();
    }

    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    public List<String> toTaskStrings() {
        return this.tasks.stream()
                .map(StorageParser::toTaskString)
                .collect(Collectors.toList());
    }
}
