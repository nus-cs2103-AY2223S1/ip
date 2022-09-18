package jarvis;

import jarvis.task.Deadline;
import jarvis.task.Event;
import jarvis.task.Task;
import jarvis.task.Todo;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Stores the task list when the program is running
 */
public class TaskList {

    ArrayList<Task> taskList = new ArrayList<>();

    private Storage storage;

    public TaskList(Storage storage) {
        this.storage = storage;
    }

    public TaskList() {
        super();
    }

    /**
     * Add a task to the task list and write to the database
     * @param input Include the description (or with time) of the task
     * @param taskType Type of the task to add
     * @param isDone Whether the task is done initially
     * @throws IOException If something went wrong when storing task to database
     */
    public String addTask(String input, Task.TaskType taskType, boolean isDone) throws IOException {
        Task task;
        try {
            task = taskType == Task.TaskType.ToDo
                    ? new Todo(input, isDone)
                    : taskType == Task.TaskType.Event
                    ? new Event(input, isDone)
                    : new Deadline(input, isDone);
        } catch (DateTimeParseException e) {
            return "Wrong time format\n"
                    + "Correct format: yyyy-mm-dd hh:mm";
        }
        int originalSize = taskList.size();
        taskList.add(task);
        assert taskList.size() == originalSize + 1 : "Task list size should increase"
                + "by 1 after adding";
        Collections.sort(taskList);
        storage.saveTaskList(this);
        String msg = "Got it. I've added this task:\n" + "  "
                + task
                + "\n" + "Now you have " + taskList.size() + " tasks in the list";
        return msg;
    }

    /**
     * Append the loaded task from database to task list, used when initializing each run
     * @param task The task to append
     */
    public void appendLoadedTask(Task task) {
        taskList.add(task);
    }

    public String markTaskAsDone(int taskNum) throws IOException {
        if (taskNum >= taskList.size()) {
            return "There is no task with index " + (taskNum + 1);
        }
        taskList.get(taskNum).markAsDone();
        String msg = "Nice! I've marked this task as done:\n";
        msg += taskList.get(taskNum);
        // Initially I want to just change the line of the task instead of rewrite the whole
        // data file as it's more efficient, but I haven't found a way to implement it.
        storage.saveTaskList(this);
        return msg;
    }

    public String markTaskAsUnDone(int taskNum) throws IOException {
        if (taskNum >= taskList.size()) {
            return "There is no task with index " + (taskNum + 1);
        }
        taskList.get(taskNum).markAsUnDone();
        String msg = "Nice! I've marked this task as done:\n";
        msg += taskList.get(taskNum);
        storage.saveTaskList(this);
        return msg;
    }

    /**
     * Delete(remove) a task from the task list
     * @param index The position of the task to delete, 0-based
     */
    public String deleteTask(int index) throws IOException {
        if (index >= taskList.size()) {
            return "There is no task with index " + (index + 1);
        }
        String msg = "Noted. I've removed this task:\n"
                + taskList.get(index) + "\n"
                + "Now you have " + (taskList.size() - 1) + " tasks in the list";
        int originalSize = taskList.size();
        taskList.remove(index);
        assert taskList.size() == originalSize - 1 : "Task list length should decrease"
                + "by 1 after deletion";
        Collections.sort(taskList);
        storage.saveTaskList(this);
        return msg;
    }

    /**
     * Print all the task in the task list
     */
    public String printTasks() {
        if (taskList.size() == 0) {
            return "There's nothing in the list.";
        }
        StringBuilder msg = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            msg.append(i + 1).append(". ").append(taskList.get(i)).append("\n");
        }
        return msg.toString();
    }

    public Task getTask(int i) {
        return taskList.get(i);
    }

    /**
     * Get the number of task in the current list
     * @return The number of task
     */
    public int getTaskCount() {
        return taskList.size();
    }

    public String find(String keyword) {
        Task[] searchResult = taskList.stream().filter(task -> task.match(keyword)).toArray(Task[]::new);
        if (searchResult.length == 0) {
            return "There's no matching task";
        }
        StringBuilder msg = new StringBuilder("Here are the matching tasks in your list:");
        for (int i = 0; i < searchResult.length; i++) {
            msg.append("\n").append(i + 1).append(". ").append(searchResult[i]);
        }
        return msg.toString();
    }
}
