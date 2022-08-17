import java.util.ArrayList;

public class TaskList {

    ArrayList<Task> tasks = new ArrayList<>();

    public String add(Task task) {
        tasks.add(task);
        return String.format("added: %s", task.toString());
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            result.append(String.format("%d. %s \n", i + 1, tasks.get(i).toString()));
        }
        return result.toString();
    }

}
