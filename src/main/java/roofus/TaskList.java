package roofus;

import java.util.ArrayList;
import java.util.List;

import roofus.task.Deadline;
import roofus.task.Event;
import roofus.task.Task;
import roofus.task.ToDo;

/**
 * Represents a list of tasks to be remembered by Roofus.
 */
public class TaskList {
    private List<Task> tasks = new ArrayList<>();

    /**
     * Constructs an instance of TaskList.
     *
     * @param taskString List of strings to be saved as Tasks.
     * @see Task
     */
    public TaskList(List<String> taskString) {
        for (String i : taskString) {
            char taskType = i.charAt(0);
            String[] components = i.split(" \\| ");
            Task thisTask = new Task("task not saved");
            switch (taskType) {
            case 'T':
                thisTask = new ToDo(components[2]);
                break;
            case 'D':
                thisTask = new Deadline(components[2],
                        components[3]);
                break;
            case 'E':
                thisTask = new Event(components[2],
                        components[3]);
                break;
            default:
                break;
            }
            if (components[1].equals("1")) {
                thisTask.mark();
            }
            this.tasks.add(thisTask);
        }
    }

    /**
     * Adds a task to Tasklist.
     *
     * @param task
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Marks a task in TaskList as done.
     *
     * @param index Task number to be marked as done.
     */
    public void mark(int index) {
        tasks.get(index - 1).mark();
    }

    /**
     * Marks a task in TaskList as not done.
     *
     * @param index Task number to be marked as not done.
     */
    public void unMark(int index) {
        tasks.get(index - 1).unmark();
    }

    /**
     * Deletes a task in TaskList.
     *
     * @param index Task number to be deleted.
     */
    public void delete(int index) {
        tasks.remove(index - 1);
    }

    /**
     * Gets a task from TaskList.
     *
     * @param index Task number to be returned.
     * @return Task A specific task from TaskList.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Gets the number of tasks in TaskList.
     *
     * @return int The size of TaskList.
     */
    public int length() {
        return tasks.size();
    }

    /**
     * Clears and deletes all tasks in TaskList.
     */
    public void clearStorage() {
        this.tasks = new ArrayList<>();
    }
}
