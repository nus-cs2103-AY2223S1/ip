import java.util.ArrayList;

public class TaskList {

    ArrayList<Task> tasks = new ArrayList<>();

    public String add(Task task) {
        tasks.add(task);
        return String.format(
                "Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.",
                task, tasks.size());
    }

    public String remove(int index) throws IndexOutOfBoundsException, DaveNoTasksException {
        if (tasks.size() == 0) {
            throw new DaveNoTasksException();
        } else {
            Task task = tasks.remove(index - 1);
            return String.format("Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.",
                    task, tasks.size());
        }
    }

    public Task get(int index) throws ArrayIndexOutOfBoundsException, DaveNoTasksException {
        if (tasks.size() == 0) {
            throw new DaveNoTasksException();
        } else {
            return tasks.get(index);
        }
    }

    public int size() {
        return tasks.size();
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
