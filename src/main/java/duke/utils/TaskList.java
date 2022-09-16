package duke.utils;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;

import duke.DukeException;
import duke.task.Task;

/**
 * Represents the list of tasks that Duke keeps track of.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public Function<String, String> secondary;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The new task to be added.
     */
    public String checkAndAddTask(Task task) {
        return (checkTask(task) != null) ? checkTask(task) : addTask(task);
    }

    private String checkTask(Task task) {
        String sameTasks = findSimilar(task);

        if (sameTasks != null) {
            // Store function as a lambda for future execution
            this.secondary = (input) -> {
                if (input.contains("y")) {
                    return addTask(task);
                } else if (input.contains("n")) {
                    return "Task not added.";
                } else {
                    return "Huh?";
                }
            };
            // Need to probe for another input: y/n
            return sameTasks;
        }
        return null;
    }

    private String addTask(Task task) {
        Integer initialSize = tasks.size();
        tasks.add(task);
        assert tasks.size() == initialSize + 1 : "Task addition is broken";

        String str = "Added: " + task + "\n" + String.format("Now you have %d tasks in the list", tasks.size());
        return str;
    }

    /**
     * Marks a task as done.
     *
     * @param input The string representation of the index of the task to be marked.
     * @throws DukeException if task number is incorrectly provided.
     */
    public String markTask(String input) throws DukeException {
        int taskNum = Integer.parseInt(input) - 1;
        Task task = getTask(taskNum);
        return task.markIsDone();
    }

    /**
     * Unmarks a task if it is done.
     *
     * @param input The string representation of the index of the task to be unmarked.
     * @throws DukeException if task number is incorrectly provided.
     */
    public String unmarkTask(String input) throws DukeException {
        int taskNum = Integer.parseInt(input) - 1;
        Task task = getTask(taskNum);
        return task.unmarkIsDone();
    }

    /**
     * Deletes a task.
     *
     * @param input The string representation of the index of the task to be deleted.
     * @throws DukeException if task number is incorrectly provided.
     */
    public String deleteTask(String input) throws DukeException {
        int taskNum = Integer.parseInt(input) - 1;
        Task task = getTask(taskNum);
        try {
            Integer initialSize = tasks.size();
            tasks.remove(taskNum);
            assert tasks.size() == initialSize - 1 : "Task deletion is broken";

            return "Removed: " + task + "\n" + String.format("Now you have %d tasks in the list", tasks.size());
        } catch (Exception e) {
            throw new DukeException(String.format("Task number %d not found", taskNum));
        }
    }

    private Task getTask(int taskNum) throws DukeException {
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

    /**
     * Lists out all the tasks.
     */
    public String listTasks() {
        if (tasks.size() == 0) {
            return "You have no tasks";
        }

        assert tasks.size() > 0 : "Task list should not be empty";

        String str = "Your tasks:\n";
        int index = 1;
        for (Task task : tasks) {
            String taskString = String.format("%d. %s\n", index, task);
            str += taskString;
            index += 1;
        }
        return str;
    }

    /**
     * Finds all task that contains the input.
     *
     * @param input Input string to match the tasks with.
     */
    public String findTasks(String input) {
        ArrayList<Task> results = tasks.stream().filter(task -> task.isContain(input))
                .collect(Collectors.toCollection(ArrayList::new));
        String str = "Here are the matching tasks in your list:\n";
        int index = 1;
        for (Task task : results) {
            String taskString = String.format("%d. %s\n", index, task);
            str += taskString;
            index += 1;
        }
        return str;
    }

    public String findSimilar(Task newTask) {
        ArrayList<Task> results = tasks.stream().filter(task -> task.equals(newTask))
                .collect(Collectors.toCollection(ArrayList::new));

        if (results.size() == 0) {
            return null;
        }

        String str = "There are similar tasks in your list:\n";
        // TODO: abstract out the task printing logic
        int index = 1;
        for (Task task : results) {
            String taskString = String.format("%d. %s\n", index, task);
            str += taskString;
            index += 1;
        }
        str += "Would you still like to add this task? (y/n)\n";
        return str;
    }
}
