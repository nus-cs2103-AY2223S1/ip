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

    /**
     * Adds a Task to TaskList
     *
     * @param task Task to be added to TaskList
     */
    public void addTask(Task task) {
        tasks.add(task);
        System.out.printf("Got it. I've added this task:\n %s\n", task);
        showTasksCount();
    }

    /**
     * Deletes Task at desired index from TaskList
     *
     * @param idx Index of Task to be deleted from TaskList
     */
    public void deleteTask(int idx) {
        Task task = tasks.get(idx - 1);
        tasks.remove(task);
        System.out.printf("Noted. I've removed this task:\n %s\n", task);
        showTasksCount();
    }

    /**
     * Marks Task as done at desired index in TaskList
     *
     * @param idx Index of Task to be marked as done in TaskList
     */
    public void markAsDone(int idx) {
        tasks.get(idx - 1).markAsDone();
    }

    /**
     * Marks Task as not done at desired index in TaskList
     *
     * @param idx Index of Task to be marked as not done in TaskList
     */
    public void markAsNotDone(int idx) {
        tasks.get(idx - 1).markAsNotDone();
    }

    /**
     * Prints all tasks in TaskList to output
     */
    public void showTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, tasks.get(i));
        }
    }

    /**
     * Prints number of tasks in TaskList to output
     */
    public void showTasksCount() {
        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
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
