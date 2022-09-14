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
    public String findTask(String search) {
        List<Task> foundTasks = new ArrayList<>();
        for (Task task : this.list) {
            if (task.getName().contains(search)) {
                foundTasks.add(task);
            }
        }

        if (foundTasks.isEmpty()) {
            return "No tasks found matching " + search;
        } else {
            String out = "";
            out += "Here are the matching tasks in your list:\n";
            int num = 1;
            for (Task task : foundTasks) {
                out += num + ". " + task.toString() + "\n";
                num++;
            }
            return out;
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
            assert num <= list.size() : "num out of range";
        }
        System.out.println(out);
    }

    public String getAllTasks() {
        if (list.isEmpty()) {
            return "Oooooweee No tasks in the task list at the moment.\n";
        }
        String out = "";
        out += "Ooooweee Here are the tasks in your list:\n";
        int num = 1;
        for (Task x : list) {
            out += num + ". " + x.toString() + "\n";
            num++;
            assert num <= list.size() : "num out of range";
        }
        return out;
    }

}
