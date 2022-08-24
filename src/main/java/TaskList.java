import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public List<String> convertToStringList() {
        return tasks.stream().map(task -> Objects.toString(task)).collect(Collectors.toList());
    }

}
