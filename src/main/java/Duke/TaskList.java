package Duke;

import java.util.ArrayList;
import java.util.List;

import Tasks.Task;

public class TaskList {
    private List<Task> lst;

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

    /**
     * Marks the specified task
     *
     * @param i
     * @return Marked Task
     */
    public Task markTask(int i) {
        Task t = lst.get(i - 1);
        t.setMarked();
        return t;
    }

    /**
     * Unmarks the specified task
     *
     * @param i
     * @return Unmarked Task
     */
    public Task unmarkTask(int i) {
        Task t = lst.get(i - 1);
        t.setUnmarked();
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

    /**
     * Gets the number of tasks in the taskList
     *
     * @return size of taskList
     */
    public int getSize() {
        return lst.size();
    }

    /**
     * Print the tasks in the taskList
     */
    public void printList() {
        System.out.println(Constants.list);
        for (int i = 0; i < lst.size(); i++) {
            System.out.println(String.format("%d.%s", i + 1, lst.get(i).toString()));
        }
    }

    /**
     * Prints the tasks in the given list
     *
     * @param list
     */
    public void printList(List<Task> list) {
        System.out.println(Constants.find);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(String.format("%d.%s", i + 1, list.get(i).toString()));
        }
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
                content.add(String.format("%s - %s - %s\n", type, t.getMarkedStatus(), t.getTaskName()));
            } else {
                content.add(String.format("%s - %s - %s - %s\n", type, t.getMarkedStatus(),
                        t.getTaskName(), t.getDate()));
            }
        }
        return content;
    }

}
