import java.util.ArrayList;
import java.util.List;

import events.Task;
import exceptions.TaskNotFoundException;

public class TaskController {
    private final List<Task> tasks;

    public TaskController() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Marks a task from the list of tasks given it's index.
     * 
     * @param idx Index of Task in List
     * @throws TaskNotFoundException Thrown if task is not found
     */
    public void markTask(int idx) throws TaskNotFoundException {
        try {
            Task curr = tasks.get(idx);
            curr.setComplete();

            System.out.println("I've marked this task as done:");
            System.out.println(curr);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFoundException();
        }
    }

    /**
     * Unmarks a task from the list of tasks given it's index.
     * 
     * @param idx Index of Task in List
     * @throws TaskNotFoundException Thrown if task is not found
     */
    public void unmarkTask(int idx) throws TaskNotFoundException {
        try {
            Task curr = tasks.get(idx);
            curr.setIncomplete();

            System.out.println("I've  unmarked this task as done:");
            System.out.println(curr);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFoundException();
        }
    }

    /**
     * Adds a new task to the list of tasks.
     * 
     * @param newTask Task to be added
     */
    public void addTask(Task newTask) {
        tasks.add(newTask);

        System.out.println("\n___________________________ \n");
        System.out.println("Your wish is my command. I've added this task:");
        System.out.println(newTask);
        System.out.println("___________________________ \n");
    }

    /**
     * Deletes a task from the list of tasks given it's index.
     * 
     * @param idx Index of Task in List
     * @throws TaskNotFoundException Thrown if task is not found
     */
    public void deleteTask(int idx) throws TaskNotFoundException {
        try {
            Task curr = tasks.get(idx);
            tasks.remove(curr);

            System.out.println("\n___________________________ \n");
            System.out.println("Your wish is my command. I've deleted this task:");
            System.out.println(curr);
            System.out.println("___________________________ \n");
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFoundException();
        }
    }

    /**
     * List all tasks in the list of tasks.
     */
    public void listTasks() {
        System.out.println("\n___________________________ \n");
        System.out.println("Here are the tasks in your list\n");
        for (int idx = 0; idx < tasks.size(); idx++) {
            System.out.println(idx + 1 + ": " + tasks.get(idx));
        }
        System.out.println("\n___________________________ \n");
    }
}
