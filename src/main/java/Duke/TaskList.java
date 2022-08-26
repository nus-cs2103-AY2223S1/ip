package Duke;
import java.util.ArrayList;

import Duke.Task.Task;

/**
 * This class represents the todo-list that allows users
 * to insert and remove different tasks, and also to mark or
 * unmark the tasks as completed.
 */
public class TaskList {

    /** This represents the todo-list to be populated with tasks. */
    private ArrayList<Task> tasks = new ArrayList<>();

    /** Constructs tan empty todo list. */
    public TaskList() {
        this.tasks = new ArrayList<>();
    } 

    /**
     * Constructs the todo list given a set of tasks.
     * 
     * @param tasks The current tasks to be put into todo list.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    } 

    /**
     * Returns the list of tasks.
     * 
     * @return The list of tasks.
     */
    public ArrayList<Task> getList() {
        return this.tasks;
    }

    /**
     * Returns the number of tasks in the list.
     * 
     * @return The number of tasks in the list.
     */
    public int getNumOfTasks() {
        return this.tasks.size();
    }

    /**
     * Inserts the given task into the todo list.
     * 
     * @param task The task to be inserted.
     */
    public void insertTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes task from the list.
     * 
     * @param taskID The id of the tasks to be deleted. The first task is of id 1.
     * @return The deleted task.
     * @throws ArrayIndexOutOfBoundsException
     */
    public Task delTask(int taskID) throws ArrayIndexOutOfBoundsException {
        return this.tasks.remove(taskID);
    }

    /**
     * Marks a task as completed.
     * 
     * @param taskID The id of the tasks to be marked. The first task is of id 1.
     * @return The marked task.
     * @throws ArrayIndexOutOfBoundsException
     */
    public Task markTask(int taskID) throws ArrayIndexOutOfBoundsException {
        tasks.get(taskID).markTask();
        return tasks.get(taskID);
    }

    /**
     * Unmarks a task as completed.
     * 
     * @param taskID The id of the tasks to be unmarked. The first task is of id 1.
     * @return The unmarked task.
     * @throws ArrayIndexOutOfBoundsException
     */
    public Task unmarkTask(int taskID) throws ArrayIndexOutOfBoundsException {
        tasks.get(taskID).unmarkTask();
        return tasks.get(taskID);
    }

    /**
     * Finds the list of alll the tasks whose description contains
     * the specified keyword.
     * 
     * @param keyword The keyword used to find the tasks.
     * @return The list of tasks that has description containing the
     * keyword.
     */
    public ArrayList<Task> findTasksContainingKeyword (String keyword) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task t : this.tasks) {
            if (t.containsKeyword(keyword)) {
                result.add(t);
            }
        }
        return result;
    }

    /**
     * Print the output in customised format.
     * 
     * @param list The list to print
     * @return String representation of the todo-list
     */
    @Override
    public String toString() {
        String res = ("Here are the tasks in your list:");
        for (int i = 0; i <  tasks.size(); i++) {
            if (tasks.get(i) == null) {
                break;
            }
            res += String.format("\n %d.%s", i + 1, tasks.get(i).toString());
        }
        return res;
    }

}
