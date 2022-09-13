package chatbot.tasks;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import chatbot.exceptions.DukeException;

/**
 * The class contains two fields. The first "todos" is a list containing
 * all the user's tasks. The second taskByDates contains all the user's tasks
 * that are time sensitive grouped by their dates.
 *
 * It provides methods to access and manipulate the data encapsulated (CRUD operations).
 */
public class TaskList {
    private List<Task> todos = new ArrayList<>();
    private HashMap<LocalDate, TaskBucket> taskByDates = new HashMap<>();
    private HashMap<String, TaskBucket> taskByTags = new HashMap<>();

    public int getNumberOfTasks() {
        return todos.size();
    }

    /**
     * The method allows user to add a Todo to their todo list.
     *
     * @param taskName Name of the task.
     * @return The corresponding Todo task.
     */
    public Task addTodo(String taskName, String[] tags) {
        Task newTask = new Todo(taskName, tags);
        todos.add(newTask);
        for (String tag : tags) {
            bucketTasks(tag, newTask);
        }
        return newTask;
    }

    /**
     * The method allows user to add a Deadline task to their todo list.
     *
     * @param taskName Name of the task.
     * @param date Date that the task should be completed by.
     * @return The corresponding Deadline task.
     */
    public Task addDeadline(String taskName, LocalDate date, String[] tags) {
        Task newTask = new Deadline(taskName, date, tags);
        todos.add(newTask);
        bucketTasks(date, newTask);
        for (String tag : tags) {
            bucketTasks(tag, newTask);
        }
        return newTask;
    }

    /**
     * The method allows user to add an Event to their todo list.
     *
     * @param taskName Name of the event.
     * @param date Date that the event is happening.
     * @return The corresponding Event.
     */
    public Task addEvent(String taskName, LocalDate date, String[] tags) {
        Task newTask = new Event(taskName, date, tags);
        todos.add(newTask);
        bucketTasks(date, newTask);
        for (String tag : tags) {
            bucketTasks(tag, newTask);
        }
        return newTask;
    }

    /**
     * Getter for all the tasks.
     *
     * @return The list that contains all the user's tasks.
     */
    public List<Task> listTasks() {
        return this.todos;
    }

    /**
     * Gets the time sensitive tasks on the specified date.
     *
     * @param date
     * @return The list containing all the relevant task.
     */
    public List<Task> getTaskOn(LocalDate date) {
        if (taskByDates.containsKey(date)) {
            assert date != null : "Date object not provided";
            return taskByDates.get(date).getTasks();
        } else {
            return null;
        }
    }

    /**
     * Gets the tasks with the specified tag.
     *
     * @param tag
     * @return The list containing all the relevant task.
     */
    public List<Task> getTaskWithTag(String tag) {
        if (taskByTags.containsKey(tag)) {
            return taskByTags.get(tag).getTasks();
        } else {
            return null;
        }
    }

    /**
     * The method allows user to delete a given task from the todo list.
     *
     * @param index The index of the task to be deleted.
     * @throws DukeException If the index to be deleted does not exist.
     */
    public Task deleteTask(int index) throws DukeException {
        try {
            return todos.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw DukeException.INVALID_TASK_INDEX;
        }
    }

    /**
     * The method allows user to mark a task as completed.
     *
     * @param index The task to be marked as completed.
     * @throws DukeException If no task exists at the provided index.
     */
    public Task markTask(int index) throws DukeException {
        try {
            Task target = todos.get(index - 1);
            target.mark();
            return target;
        } catch (IndexOutOfBoundsException e) {
            throw DukeException.INVALID_TASK_INDEX;
        }
    }

    /**
     * The method allows user to mark a task as incomplete.
     *
     * @param index The task to be marked as incomplete.
     * @throws DukeException If no task exists at the provided index.
     */
    public Task unmarkTask(int index) throws DukeException {
        try {
            Task target = todos.get(index - 1);
            target.unmark();
            return target;
        } catch (IndexOutOfBoundsException e) {
            throw DukeException.INVALID_TASK_INDEX;
        }
    }

    /**
     * Finds all the tasks in the todo list containing the keyword.
     *
     * @param keyword The keyword in user's search
     * @return The list of tasks containing the keyword.
     */
    public List<Task> find(String keyword) {
        assert !keyword.isEmpty() : "Keyword should not be empty";
        List<Task> matches = new ArrayList<>();
        for (Task task : todos) {
            if (task.getTaskName().contains(keyword)) {
                matches.add(task);
            }
        }

        return matches;
    }

    /**
     * Finds all the tasks in the todo list containing the keyword.
     *
     * @param keyword The keyword in user's search
     * @return The list of tasks containing the keyword.
     */
    public Task[] streamFind(String keyword) {
        return (Task[]) todos.stream().filter(todo -> todo.getTaskName().contains(keyword)).toArray();
    }

    /**
     * The method assigns the task to a group by its date.
     *
     * @param date The date associated with the task.
     * @param task The task to be assigned.
     */
    private void bucketTasks(LocalDate date, Task task) {
        if (taskByDates.containsKey(date)) {
            taskByDates.get(date).addTask(task);
        } else {
            TaskBucket newBucket = new TaskBucket();
            newBucket.addTask(task);
            taskByDates.put(date, newBucket);
        }
    }

    private void bucketTasks(String tag, Task task) {
        if (taskByTags.containsKey(tag)) {
            taskByTags.get(tag).addTask(task);
        } else {
            TaskBucket newTagBucket = new TaskBucket();
            newTagBucket.addTask(task);
            taskByTags.put(tag, newTagBucket);
        }
    }
}
