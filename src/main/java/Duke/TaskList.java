package Duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public List<Task> getList() {
        return list;
    }

    /**
     * Helper function to print the task description
     */
    public void taskPrinter() {
        if (list.isEmpty()) {
            System.out.println("No tasks in the task list at the moment.");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        String out = "";
        int num = 1;
        for (Task x : list) {
            out += num + ". " + x.toString() + "\n";
            num++;
        }
        System.out.println(out);
    }
}
