package DukeUI;
import java.util.ArrayList;

import DukeUI.Task.Task;

/**
 * This class represents the todo-list that allows users
 * to insert and remove different tasks, and also to mark or
 * unmark the tasks as completed.
 */
public class TaskList {

    /** This represents the todo-list to be populated with tasks */
    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskList() {
        this.tasks = new ArrayList<>();
    } 

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    } 

    public ArrayList<Task> getList() {
        return this.tasks;
    }

    public int getNumOfTasks() {
        return this.tasks.size();
    }

    public void insertTask(Task task) {
        this.tasks.add(task);
    }

    public Task delTask(int taskID) throws ArrayIndexOutOfBoundsException {
        return this.tasks.remove(taskID);
    }

    public Task markTask(int taskID) throws ArrayIndexOutOfBoundsException {
        tasks.get(taskID).markTask();
        return tasks.get(taskID);
    }

    public Task unmarkTask(int taskID) throws ArrayIndexOutOfBoundsException {
        tasks.get(taskID).unmarkTask();
        return tasks.get(taskID);
    }

    /**
     * Print the output in customised format.
     * 
     * @param list The list to print
     * @return String representation of the todo-list
     */
    @Override
    public String toString() {
        String res = ("Here are the tasks in your list:\n");
        for (int i = 0; i <  tasks.size(); i++) {
            if (tasks.get(i) == null) {
                break;
            }
            res += String.format("\n %d.%s", i + 1, tasks.get(i).toString());
        }
        return res;
    }

}
