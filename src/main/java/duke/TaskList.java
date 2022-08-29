package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * {@code TaskList} keep tracks of the current tasks during the program runtime
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructor for {@code TaskList}
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * To get the current list of tasks
     * @return an {@code ArrayList} that contains the current list of tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Constructor for {@code TaskList}
     * @param tasks the tasks data to be loaded
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * To get the task at the given index
     * @param idx the index
     * @return a {@code Task} at the given index
     */
    public Task get(int idx) {
        return tasks.get(idx);
    }

    /**
     * To add the task to the list
     * @param t the {@code Task} to be added
     */
    public void add(Task t) {
        tasks.add(t);
    }

    /**
     * To remove the task at the given index
     * @param idx the index
     */
    public void remove(int idx) {
        tasks.remove(idx);
    }

    /**
     * To get the size of the current list of tasks
     * @return the size
     */
    public int size() {
        return tasks.size();
    }

    /**
     * To find tasks that matches the user input
     * @param s user input searching criteria
     * @return an {@code ArrayList} of matching tasks
     */
    public ArrayList<Task> find(String s) {
        ArrayList<Task> temp = new ArrayList<>();
        for (Task task: tasks) {
            if (task.getDescription().contains(s)) {
                temp.add(task);
            }
        }
        return temp;
    }
    
    /**
     * To list out all the tasks in the list
     */
    public void listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t " + (i + 1) + "." + tasks.get(i).toString());
        }
    }
}
