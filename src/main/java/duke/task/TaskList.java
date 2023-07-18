package duke.task;

import java.util.ArrayList;

/**
 * TaskList is a TaskList class that contains tasks.
 *
 * @author John Russell Himawan
 * @version CS2103T AY22/23 Sem 1
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private int size;

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.size = 0;
    }

    /**
     * Constructor for TaskList.
     *
     * @param dukeList ArrayList of Task to be added.
     */
    public TaskList(ArrayList<Task> dukeList) {
        this.tasks = new ArrayList<>();
        int len = dukeList.size();
        for (int i = 0; i < len; i++) {
            tasks.add(dukeList.get(i));
        }
        this.size = tasks.size();
    }

    /**
     * Returns the size of the TaskList.
     *
     * @return Size of TaskList.
     */
    public int getSize() {
        assert size >= 0 : "size should be greater or equal to 0";
        return size;
    }

    /**
     * Returns the specified Task in the Tasklist.
     *
     * @param index Index of which task to get.
     * @return Specified task in TaskList.
     */
    public Task getTask(int index) {
        assert index >= 0 : "index should be greater or equal to 0";
        return tasks.get(index);
    }

    /**
     * Marks task as done.
     *
     * @param index Index of task to be marked as done.
     * @return Marked task.
     */
    public Task markTask(int index) {
        assert index >= 0 : "index should be greater or equal to 0";
        Task markedTask = tasks.get(index);
        markedTask.markAsDone();
        return markedTask;
    }

    /**
     * Unmarks task as done.
     *
     * @param index Index of task to be unmarked as done.
     * @return Unmarked task.
     */
    public Task unmarkTask(int index) {
        assert index >= 0 : "index should be greater or equal to 0";
        Task unmarkedTask = tasks.get(index);
        unmarkedTask.unmarkAsDone();
        return unmarkedTask;
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added.
     * @return The added task.
     */
    public Task addTask(Task task) {
        tasks.add(task);
        size++;
        return task;
    }

    /**
     * Adds Todo task.
     *
     * @param description Description of the Todo.
     * @return Added Todo task.
     */
    public Task addTodo(String description) {
        assert description.length() > 0 : "todo description cannot be empty";
        Todo todo = new Todo(description);
        tasks.add(todo);
        size++;
        return todo;
    }

    /**
     * Adds Deadline task.
     *
     * @param description Description of the Deadline.
     * @param by Date of the deadline.
     * @return Added Deadline task.
     */
    public Task addDeadline(String description, String by) {
        assert description.length() > 0 : "deadline description cannot be empty";
        Deadline deadline = new Deadline(description, by);
        tasks.add(deadline);
        size++;
        return deadline;
    }

    /**
     * Adds Event task.
     *
     * @param description Description of the Event.
     * @param at Date of the event.
     * @return Added Event task.
     */
    public Task addEvent(String description, String at) {
        assert description.length() > 0 : "event description cannot be empty";
        Event event = new Event(description, at);
        tasks.add(event);
        size++;
        return event;
    }

    /**
     * Deletes a task.
     *
     * @param index Index of the task to be deleted in the TaskList.
     * @return The deleted task.
     */
    public Task deleteTask(int index) {
        assert index >= 0 : "index should be greater or equal to 0";
        Task deletedTask = tasks.remove(index);
        size--;
        return deletedTask;
    }

    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchedTasks = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Task task = tasks.get(i);
            if (task.getDescription().contains(keyword)) {
                matchedTasks.add(task);
            }
        }
        return matchedTasks;
    }

    /**
     * Reminds tasks that are within 7 days to expiring.
     *
     * @return The task to be reminded.
     */
    public ArrayList<Task> remindTasks() {
        ArrayList<Task> remindedTasks = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Task currentTask = tasks.get(i);
            if (currentTask.isNeedReminder()) {
                remindedTasks.add(currentTask);
            }
        }
        return remindedTasks;
    }

    /**
     * Returns a String representation of the TaskList.
     *
     * @return String representation of the TaskList.
     */
    @Override
    public String toString() {
        assert size >= 0 : "size should be greater or equal to 0";
        if (size == 0) {
            return "Hmm, there doesn't seem to be any tasks in your list.";
        } else {
            String message = "Alright, here are the tasks in your list:\n";
            for (int i = 0; i < size; i++) {
                int orderList = i + 1;
                message += Integer.toString(orderList) + ". " + tasks.get(i).toString() + "\n";
            }
            return message;
        }
    }
}
