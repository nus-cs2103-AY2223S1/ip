import java.util.ArrayList;

public class TaskList {

    ArrayList<Task> tasks = new ArrayList<>();

    public String add(Task task) {
        tasks.add(task);
        return String.format(
                "Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.",
                task, tasks.size());
    }

    public String markdone(int index) {
        return tasks.get(index - 1).markdone();
    }

    public String unmarkdone(int index) {
        return tasks.get(index - 1).unmarkdone();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            result.append(String.format("%d. %s \n", i + 1, tasks.get(i)));
        }
        return result.toString();
    }

}
