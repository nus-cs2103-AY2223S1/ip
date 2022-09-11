package duke.task;

import java.util.ArrayList;

/**
 * Tasklist class to store task objects.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor for tasklist class.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Method to add a task into tasklist and
     * save it into the file.
     *
     * @param t The task to be added.
     */
    public void addTask(Task t) {
        this.tasks.add(t);
    }

    /**
     * Gets the task based on task number.
     *
     * @param taskNo The task number.
     * @return The task in the taskList at taskNo position.
     */
    public Task getTask(int taskNo) {
        assert taskNo <= this.tasks.size() : "The task number should not be bigger than total tasks size";
        return tasks.get(taskNo - 1);
    }

    /**
     * Gets all the tasks
     *
     * @return All the tasks in task list
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Gets the size of the tasks
     *
     * @return The size of the tasks
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Marks the task as done.
     *
     * @param taskNo The corresponding task ID.
     */
    public void markTask(int taskNo) {
        assert taskNo <= this.tasks.size() : "The task number should not be bigger than total tasks size";
        Task task = this.getTask(taskNo);
        task.mark();
    }

    /**
     * Marks the task as undone.
     *
     * @param taskNo The corresponding task ID.
     */
    public void unMarkTask(int taskNo) {
        assert taskNo <= this.tasks.size() : "The task number should not be bigger than total tasks size";
        Task task = this.getTask(taskNo);
        task.unMark();
    }

    /**
     * Deletes the task from tasklist.
     *
     * @param taskNo The corresponding task ID.
     */
    public void deleteTask(int taskNo) {
        assert taskNo <= this.tasks.size() : "The task number should not be bigger than total tasks size";
        this.tasks.remove(taskNo - 1);
    }

    /**
     * Gets the task type.
     *
     * @param task The task to check.
     * @return Returns the task type.
     */
    public String getTaskType(Task task) {
        if (task instanceof Deadline) {
            return "Deadlines";
        } else if (task instanceof Event) {
            return "Events";
        } else if (task instanceof ToDo) {
            return "ToDos";
        }
        return "Task";
    }
}
