package duke.utils;

import duke.DukeException;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents the list of tasks that Duke keeps track of.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void printTasks() {
        System.out.println("Your tasks:");
        for (Task task : tasks) {
            int taskIndex = tasks.indexOf(task) + 1;
            String taskString = String.format("%d. %s", taskIndex, task);
            System.out.println(taskString);
        }
    }

    public void addTask(Task task) {
        tasks.add(task);

        System.out.println("\n  _______________");
        System.out.println("  Added: " + task);
        System.out.println(String.format("  Now you have %d tasks in the list", tasks.size()));
        System.out.println("  _______________\n");
    }

    public void markTask(String input) throws Exception {
        int taskNum = Integer.parseInt(input) - 1;
        Task task = getTask(taskNum);
        task.markIsDone();
    }

    public void unmarkTask(String input) throws Exception {
        int taskNum = Integer.parseInt(input) - 1;
        Task task = getTask(taskNum);
        task.unmarkIsDone();
    }

    public void deleteTask(String input) throws Exception {
        int taskNum = Integer.parseInt(input) - 1;
        Task task = getTask(taskNum);
        try {
            tasks.remove(taskNum);

            System.out.println("\n  _______________");
            System.out.println("  Removed: " + task);
            System.out.println(String.format("  Now you have %d tasks in the list", tasks.size()));
            System.out.println("  _______________\n");
        } catch (Exception e) {
            throw new DukeException(String.format("Task number %d not found", taskNum));
        }
    }

    public Task getTask(int taskNum) throws Exception {
        Task task;

        try {
            task = tasks.get(taskNum);
        } catch (Exception e) {
            throw new DukeException("Task number is incorrectly provided");
        }

        return task;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
