package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class to manage the list of tasks present when program is running
 * @author Reuben Chay
 */
public class TaskList {
    private List<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public List<Task> getList() {
        return list;
    }

    /**
     * Prints each task description in the list
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
