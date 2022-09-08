package sally.task;

import java.util.ArrayList;

/**
 * TaskList class to represent the list of tasks stored.
 *
 * @author liviamil
 */

public class TaskList {
    protected ArrayList<Task> tasks;
    protected int numOfTasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
        this.numOfTasks = 0;
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.numOfTasks = tasks.size();
    }

    public int getNumOfTasks() {
        return numOfTasks;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public void addTask(Task task) {
//        System.out.println("enters addTask in TaskLIst");
//        System.out.println("task parsed: " + task);
        tasks.add(task);
//        System.out.println("tasks.add(task) executed");
//        String printTask = "";
//        for (int i = 0; i < tasks.size(); i++) {
//            printTask = printTask + tasks.get(i).toString();
//        }
//        System.out.println("local tasks in sally.task.TaskList: " + printTask);
//        System.out.println("task number: " + tasks.size());
    }

}
