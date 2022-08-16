import java.util.ArrayList;
import java.util.List;

import exceptions.TaskNotFoundException;

public class TaskController {
    private List<Task> tasks;

    public TaskController() {
        this.tasks = new ArrayList<>();
    }

    /**
     * @param idx
     * @return
     * @throws TaskNotFoundException
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
     * @param idx
     * @return
     * @throws TaskNotFoundException
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
     * @param newTask
     */
    public void addTask(Task newTask) {
        tasks.add(newTask);

        System.out.println("\n___________________________ \n");
        System.out.println("Your wish is my command. I've added this task:");
        System.out.println(newTask);
        System.out.println("___________________________ \n");
    }

    /**
     * @param idx
     * @return
     * @throws TaskNotFoundException
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
     * 
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
