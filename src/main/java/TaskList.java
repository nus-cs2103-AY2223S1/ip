import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public String[] list() {
        String[] lines = new String[tasks.size() + 1];
        lines[0] = "Here are the tasks in your list: ";
        for (int i = 1; i < tasks.size() + 1; i++) {
            lines[i] = tasks.get(i - 1).toStringWithIndex(i);
        }
        return lines;
    }

    public String[] mark(int index) {
        Task task = this.tasks.get(index);
        task.mark();
        return new String[] {"Nice! I've marked this task as done:",
                "  " + task.toString()};
    }

    public String[] unmark(int index) {
        Task task = this.tasks.get(index);
        task.unmark();
        return new String[] {"OK, I've marked this task as not done yet:",
                "  " + task.toString()};
    }

    public String[] delete(int index) {
        Task task = this.tasks.get(index);
        this.tasks.remove(index);
        return new String[] {"Noted. I've removed this task:",
                "  " + task.toString(),
                String.format("Now you have %d tasks in the list.", this.tasks.size())};
    }

    public String[] add(Task task) {
        this.tasks.add(task);
        return new String[] {"Got it. I've added this task:",
                "   " + task.toString(),
                String.format("Now you have %d tasks in the list.", this.tasks.size())};
    }

    public String toFileFormat() {
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            sb.append(task.toFileFormat()).append(System.lineSeparator());
        }
        return sb.toString();
    }
}
