package duke.util;

//import util
import java.util.ArrayList;

import duke.exception.TaskMarkException;
import duke.exception.TaskNotFoundException;
import duke.exception.TaskUnmarkException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents a list of Tasks and provides methods to modify the list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs empty ArrayList that stores Task.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a ArrayList that stores tasks embedded in text.
     *
     * @param text Contain a number of task.
     */
    public TaskList(String text) {
        this();
        if (text != "") {
            String[] texts = text.split("\n");
            String taskType;
            String description;
            for (int i = 0; i < texts.length; i++) {
                taskType = texts[i].substring(3, 6);
                String[] descriptions;
                switch (taskType) {
                case "[T]":
                    description = texts[i].substring(10);
                    tasks.add(new Todo(description, texts[i].charAt(7) == 'X'));
                    break;
                case "[D]":
                    description = texts[i].substring(10);
                    descriptions = description.split("by");
                    tasks.add(new Deadline(descriptions[0].substring(0, description.indexOf("(") - 1),
                            descriptions[1].substring(2, descriptions[1].length() - 1), texts[i].charAt(7) == 'X'));
                    break;
                case "[E]":
                    description = texts[i].substring(10);
                    descriptions = description.split("at");
                    tasks.add(new Event(descriptions[0].substring(0, description.indexOf("(") - 1),
                            descriptions[1].substring(2, descriptions[1].length() - 1), texts[i].charAt(7) == 'X'));
                    break;
                default:
                    break;
                }
            }
        }
    }

    /**
     * Adds task to tasks.
     *
     * @param task To be added to tasks.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Checks if index is out of bound.
     *
     * @param index index to be checked.
     * @return true if index is in bound else false.
     */
    private boolean isIndexOutOfBound(int index) {
        return (index < 0 || index > tasks.size() - 1);
    }

    /**
     * Marks task specified by the index as done.
     *
     * @param index index of task to be mark as done.
     * @return task that is marked.
     * @throws TaskMarkException when Task is already marked.
     * @throws TaskNotFoundException when the given index is out of bound.
     */
    public Task markTask(int index) throws TaskMarkException, TaskNotFoundException {
        if (isIndexOutOfBound(index)) {
            throw new TaskNotFoundException(index + 1);
        }
        Task task = tasks.get(index);
        task.mark();
        return task;
    }

    /**
     * Marks task specified by the index as not done.
     *
     * @param index index of task to be mark as not done.
     * @return task that is unmarked.
     * @throws TaskUnmarkException when Task is already not done.
     * @throws TaskNotFoundException when the given index is out of bound.
     */
    public Task unmarkTask(int index) throws TaskUnmarkException, TaskNotFoundException {
        if (isIndexOutOfBound(index)) {
            throw new TaskNotFoundException(index + 1);
        }
        Task task = tasks.get(index);
        task.unmark();
        return task;
    }

    /**
     * Deletes task specified by the index.
     *
     * @param index index of task to be deleted
     * @return task that is removed.
     * @throws TaskNotFoundException when the given index is out of bound.
     */
    public Task deleteTask(int index) throws TaskNotFoundException {
        if (isIndexOutOfBound(index)) {
            throw new TaskNotFoundException(index + 1);
        }
        return tasks.remove(index);
    }

    /**
     * Checks if tasks is empty.
     *
     * @return true if tasks is empty else false.
     */
    public boolean isEmpty() {
        return tasks.size() == 0;
    }

    /**
     * Displays the status of the tasks.
     * Includes total number of task and unmarked task.
     *
     * @return String Representation of the status.
     */
    public String getStatus() {
        String totalTaskMessage = "Total Task: " + tasks.size();
        String unmarkTaskMessage = "Unmarked Task: " + getNoOfUnmarkTask();
        return totalTaskMessage + "\n" + unmarkTaskMessage;
    }

    /**
     * Retrieves the number of unmark task in tasks.
     *
     * @return the number of unmark task.
     */
    private int getNoOfUnmarkTask() {
        Task task;
        int count = 0;
        for (int i = 0; i < tasks.size(); i++) {
            task = tasks.get(i);
            if (!task.isDone()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Returns a list of task that contains description in it.
     *
     * @param description filter out task with description in it.
     * @return TaskList containing all filtered out task with description in it.
     */
    public TaskList findTask(String description) {
        TaskList filteredTasks = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.doesContain(description)) {
                filteredTasks.addTask(task);
            }
        }
        return filteredTasks;
    }

    /**
     * Returns a string representation of tasks.
     *
     * @return tasks in string representation.
     */
    @Override
    public String toString() {
        int size = tasks.size();
        String text = String.format("%d. %s", 1, tasks.get(0));
        for (int i = 1; i < size; i++) {
            text += String.format("\n%d. %s", i + 1, tasks.get(i));
        }
        return text;
    }
}
