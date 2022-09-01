package duke;

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
     * Prints tasks found when find is called
     * @param search the keyword to search for
     */
    public void findTask(String search) {
        List<Task> foundTasks = new ArrayList<>();
        for (Task task : this.list) {
            if (task.getName().contains(search)) {
                foundTasks.add(task);
            }
        }

        if (foundTasks.isEmpty()) {
            System.out.println("No tasks found matching " + search);
        } else {
            String out = "";
            out += "Here are the matching tasks in your list:\n";
            int num = 1;
            for (Task task : foundTasks) {
                out += num + ". " + task.toString() + "\n";
                num++;
            }
            System.out.println(out);
        }
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
