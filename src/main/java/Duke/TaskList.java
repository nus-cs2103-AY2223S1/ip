package Duke;

import java.util.ArrayList;
import java.util.List;

import Tasks.Task;

/**
 * Contains the logic for which a list of tasks work in Duke
 */
public class TaskList {
    private List<Task> lst;

    /**
     * Constructor for TaskList
     *
     * @param load
     */
    public TaskList(ArrayList<Task> load) {
        this.lst = load;
    }

    /**
     * Adds the task to taskList
     *
     * @param t
     */
    public void addTask(Task t) {
        lst.add(t);
    }

    /**
     * Deletes the specified tasks from the taskList
     *
     * @param i
     * @return Deleted Task
     */
    public Task deleteTask(int i) {
        Task removed = lst.get(i - 1);
        lst.remove(i - 1);
        return removed;
    }

    public Task markTask(int i) {
        Task t = lst.get(i - 1);
        t.setMarked();
        return t;
    }

    public Task unmarkTask(int i) {
        Task t = lst.get(i - 1);
        t.setUnmarked();
        return t;
    }

    /**
     * Sets the priority of  the designated task
     *
     * @param i index of task in task list
     * @param s priority of task
     * @return designated task
     */
    public Task setTaskPriority(int i, String s) {
        Task t = lst.get(i - 1);
        t.setPriority(s);
        return t;
    }

    /**
     * Returns list of Tasks with the corresponding name
     *
     * @param name
     * @return List<Task>
     */
    public List<Task> findTask(String name) {
        List<Task> foundTasks = new ArrayList<>();
        for (int i = 0; i < lst.size(); i++) {
            Task t = lst.get(i);
            if (t.getTaskName().contains(name)) {
                foundTasks.add(t);
            }
        }
        return foundTasks;
    }

    public int getSize() {
        return lst.size();
    }

    /**
     * Print the tasks in the taskList
     */
    public String printList() {
        String s = Constants.LIST;
        for (int i = 0; i < lst.size(); i++) {
            s += String.format("\n%d.%s", i + 1, lst.get(i).toString());
        }
        System.out.println(s);
        return s;
    }

    /**
     * Prints the tasks in the given list
     *
     * @param list
     */
    public String printList(List<Task> list) {
        String s = Constants.FIND;
        for (int i = 0; i < list.size(); i++) {
            s += String.format("\n%d.%s", i + 1, list.get(i).toString());
        }
        System.out.println(s);
        return s;
    }

    /**
     * Stringifies all the tasks in taskList
     *
     * @return ArrayList containing all the stringified tasks
     */
    public ArrayList<String> stringfy() {
        ArrayList<String> content = new ArrayList<>();
        for (int i = 0; i < lst.size(); i++) {
            Task t = lst.get(i);
            String type = t.toString().substring(1,2);
            if (type.equals("T")) {
                content.add(String.format("%s - %s - %s - %s\n", type, t.getPriorityStatus(),
                        t.getMarkedStatus(), t.getTaskName()));
            } else {
                content.add(String.format("%s - %s - %s - %s - %s\n", type, t.getPriorityStatus(), t.getMarkedStatus(),
                        t.getTaskName(), t.getDate()));
            }
        }
        return content;
    }

}
