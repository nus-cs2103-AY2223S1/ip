package duke;

import java.util.ArrayList;

/**
 * Contains the task list.
 */
public class TaskList {

    private ArrayList<Task> tasks;
    private int todoCount = 0;
    private int deadlineCount = 0;
    private int eventCount = 0;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks, int tdCount, int deadlineCount, int eventCount) {
        this.todoCount = tdCount;
        this.tasks = tasks;
        this.deadlineCount = deadlineCount;
        this.eventCount = eventCount;
    }

    /**
     * Adds task to list.
     *
     * @param task Task to be added.
     * @return Task added.
     */
    public Task addTask(Task task) {
        tasks.add(task);
        return task;
    }

    /**
     * Deletes tasks from list.
     *
     * @param taskNum Task to be deleted.
     */
    public void deleteTask(int taskNum) {
        tasks.remove(taskNum);
    }

    /**
     * Marks task as done.
     *
     * @param index Index of task to be marked.
     * @return Task marked.
     */
    public Task mark(int index) {
        tasks.get(index).isDone = true;
        return tasks.get(index);
    }

    /**
     * Unmarks task.
     *
     * @param index Index of task to be unmarked.
     * @return Task unmarked.
     */
    public Task unmark(int index) {
        tasks.get(index).isDone = false;
        return tasks.get(index);
    }

    /**
     * Gets a specific task.
     *
     * @param index Index of task to get.
     * @return Task to get.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Gets size of task list.
     *
     * @return Size of task list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Increases count of todos.
     */
    public void increaseTodoCount() {
        this.todoCount++;
    }

    /**
     * Gets number of todos.
     *
     * @return Number of todos.
     */
    public int getTodoCount() {
        return this.todoCount;
    }

    /**
     * Increases count of deadlines.
     */
    public void increaseDeadlineCount() {
        this.deadlineCount++;
    }

    /**
     * Gets number of deadlines.
     *
     * @return Number of deadlines.
     */
    public int getDeadlineCount() {
        return this.deadlineCount;
    }

    /**
     * Increases count of events.
     */
    public void increaseEventCount() {
        this.eventCount++;
    }

    /**
     * Gets number of events.
     *
     * @return Number of events.
     */
    public int getEventCount() {
        return this.eventCount;
    }

    /**
     * Reduces task count when task is deleted.
     *
     * @param task Task that is deleted.
     */
    public void reduceTaskCount(Task task) {
        if (task instanceof ToDos) {
            this.todoCount--;
        } else if (task instanceof Deadlines) {
            this.deadlineCount--;
        } else {
            this.eventCount--;
        }
    }
}
