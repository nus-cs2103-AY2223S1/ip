package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor for TaskList
     *
     * @param tasks List of tasks to be stored in TaskList
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this(new ArrayList<>());
    }

    /**
     * Finds tasks with keyword in description and returns String representation of tasks
     *
     * @param keyword String keyword to search for in Task
     * @return String representation of tasks
     */
    public String findTasks(String keyword) {
        TaskList temp = new TaskList();
        for (Task task : tasks) {
            if (task.containsKeyword(keyword)) {
                temp.tasks.add(task);
            }
        }
        return String.format("Here are the matching tasks in your list:\n%s", temp.showTasksPlain());
    }

    /**
     * Returns String representation of tasks in TaskList without additional comments
     *
     * @return String representation of tasks
     */
    private String showTasksPlain() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            stringBuilder.append(String.format("%d. %s\n", i + 1, tasks.get(i)));
        }

        return stringBuilder.toString();
    }

    /**
     * Adds a Task to TaskList
     *
     * @param task Task to be added to TaskList
     * @return String to be displayed to user after adding Task to TaskList
     */
    public String addTask(Task task) {
        tasks.add(task);
        return String.format("Got it. I've added this task:\n %s\n%s", task, showTasksCount());
    }

    /**
     * Deletes Task at desired index from TaskList
     *
     * @param idx Index of Task to be deleted from TaskList
     * @return String to be displayed to user after deleting Task from TaskList
     */
    public String deleteTask(int idx) {
        Task task = tasks.get(idx - 1);
        tasks.remove(task);
        return String.format("Noted. I've removed this task:\n %s\n%s", task, showTasksCount());
    }

    /**
     * Marks Task as done at desired index in TaskList
     *
     * @param idx Index of Task to be marked as done in TaskList
     */
    public String markAsDone(int idx) {
        return tasks.get(idx - 1).markAsDone();
    }

    /**
     * Marks Task as not done at desired index in TaskList
     *
     * @param idx Index of Task to be marked as not done in TaskList
     */
    public String markAsNotDone(int idx) {
        return tasks.get(idx - 1).markAsNotDone();
    }

    /**
     * Returns String representation of all tasks in TaskList
     *
     * @return String representation of all tasks in TaskList
     */
    public String showTasks() {
        return String.format("Here are the tasks in your list:\n%s", showTasksPlain());
    }

    /**
     * Returns String representation of number of tasks in TaskList
     *
     * @return String representation of number of tasks in TaskList
     */
    public String showTasksCount() {
        return String.format("Now you have %d tasks in the list.\n", tasks.size());
    }

    /**
     * Returns String representation of all tasks in TaskList
     *
     * @return String representation of all tasks in TaskList
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Task task : tasks) {
            // format is such that
            // <taskName> <isDone> <description> <time>
            str.append(task.getTxtString()).append("\n");
        }
        return str.toString();
    }


}
