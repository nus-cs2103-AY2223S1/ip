package duke.utils;

import duke.DukeException;
import duke.task.Task;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Represents the list of tasks that Duke keeps track of.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The new task to be added.
     */
    public String addTask(Task task) {
        tasks.add(task);
        String str = "Added: " + task + "\n" + String.format("Now you have %d tasks in the list", tasks.size());
        return str;
    }

    /**
     * Marks a task as done.
     *
     * @param input The string representation of the index of the task to be marked.
     */
    public String markTask(String input) throws Exception {
        int taskNum = Integer.parseInt(input) - 1;
        Task task = getTask(taskNum);
        return task.markIsDone();
    }

    /**
     * Unmark a task if it is done.
     *
     * @param input The string representation of the index of the task to be unmarked.
     */
    public String unmarkTask(String input) throws Exception {
        int taskNum = Integer.parseInt(input) - 1;
        Task task = getTask(taskNum);
        return task.unmarkIsDone();
    }

    public String deleteTask(String input) throws Exception {
        int taskNum = Integer.parseInt(input) - 1;
        Task task = getTask(taskNum);
        try {
            tasks.remove(taskNum);
            return "Removed: " + task + "\n" + String.format("Now you have %d tasks in the list", tasks.size());
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

    public String printTasks() {
        String str = "Your tasks:\n";
        for (Task task : tasks) {
            int taskIndex = tasks.indexOf(task) + 1;
            String taskString = String.format("%d. %s\n", taskIndex, task);
            str += taskString;
        }
        return str;
    }

    public String findTasks(String input) {
        ArrayList<Task> results = tasks.stream().filter(task -> task.isContain(input))
                .collect(Collectors.toCollection(ArrayList::new));
        String str = "Here are the matching tasks in your list:\n";
        for (Task task : results) {
            int taskIndex = tasks.indexOf(task) + 1;
            String taskString = String.format("%d. %s", taskIndex, task);
            str += taskString;
        }
        return str;
    }
}
