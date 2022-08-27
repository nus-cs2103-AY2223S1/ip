package duke.task;

import java.util.ArrayList;
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
        if (lines.length == 1) {
            lines[0] = "No tasks found in the list!";
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

    /**
     * Finds all matching tasks containing a specific string.
     *
     * @param toFind the string to find
     * @return the print format of all the tasks found
     */
    public String[] find(String toFind) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            Task curr = tasks.get(i);
            if (curr.description.contains(toFind)) {
                res.append(curr.toStringWithIndex(i + 1)).append("\n");
            }
        }
        return new String[] {"Here are the matching tasks in your list:",
                res.toString()};
    }
}
