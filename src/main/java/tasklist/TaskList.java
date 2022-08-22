package tasklist;

import tasks.Task;

import java.util.List;
import java.util.ArrayList;


/**
 * Implementation of the TaskList, the list of tasks created by the user.
 */
public class TaskList {

    private List<Task> taskList;

    /**
     * Constructor for the TaskList class.
     * @return TaskList instance
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Marks a task as complete in the Task List.
     * @param index The index of the task to be marked as complete
     */
    public void markTaskComplete(int index) {
        taskList.get(index).markAsDone();
        System.out.println("Nice! I've marked this task as having been completed:");
        System.out.println(taskList.get(index));
    }

    /**
     * Marks a task as incomplete in the Task List.
     * @param index The index of the task to be marked as incomplete.
     */
    public void markTaskIncomplete(int index) {
        taskList.get(index).markAsUndone();
        System.out.println("Okay, I've marked this task as not done yet:");
        System.out.println(taskList.get(index));
    }

    /**
     * Adds a task to the Task List.
     * @param task The task that is to be added to the List.
     */
    public void addTaskToList(Task task) {
        taskList.add(task);
        System.out.println("Got it, i've added this task to your list:\n  " + task);
        System.out.println("You now have " + taskList.size() + " tasks in your list.");
    }

    /**
     * Deletes a task from the Task List.
     * @param index The index of the task to be deleted.
     */
    public void deleteTask(int index) {
        Task removedTask = taskList.get(index);
        taskList.remove(index);
        System.out.println("Okay, I've removed this task:");
        System.out.println(removedTask);
        System.out.println("You now have " + taskList.size() + " tasks in your list.");
    }

    /**
     * Fetch the size of the task list.
     * @return The size of the task list.
     */
    public int getSize() {
        return taskList.size();
    }
    @Override
    public String toString() {
        int counter = 1;
        String res = "Here are the tasks that you have added to the list:\n";
        for (Task task : this.taskList) {
            if (task != null) {
                res += counter + ". " + task + "\n";
                counter++;
            }
        }
        return counter == 1 ? "There are no tasks in your task list at the moment!" : res;
    }
}
