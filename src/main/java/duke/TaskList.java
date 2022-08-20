package duke;

import java.time.LocalDate;
import java.util.ArrayList;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * TaskList contains the list of Tasks and its operations.
 *
 * @author Jet Lee
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
     * Constructor for TaskList with saved Tasks.
     *
     * @param dataList List containing data of saved Tasks.
     */
    public TaskList(ArrayList<String> dataList) {
        this.tasks = new ArrayList<>();
        for (String data : dataList) {
            tasks.add(Task.loadTask(data));
        }
        this.size = tasks.size();
    }

    /**
     * Returns the current size of the list.
     *
     * @return Current size of the list.
     */
    public int getSize() {
        return size;
    }

    /**
     * Converts the Tasks in the list to data to be saved.
     *
     * @return List containing data representing the Tasks.
     */
    public ArrayList<String> saveTasks() {
        ArrayList<String> dataList = new ArrayList<>();
        for (Task task : tasks) {
            dataList.add(task.saveTask());
        }
        return dataList;
    }

    /**
     * Marks the Task at the given index as completed.
     *
     * @param idx Given index.
     * @return String representation of the Task marked.
     */
    public String markTask(int idx) {
        return tasks.get(idx).mark();
    }

    /**
     * Marks the Task at the given index as uncompleted.
     *
     * @param idx Given index.
     * @return String representation of the Task unmarked.
     */
    public String unmarkTask(int idx) {
        return tasks.get(idx).unmark();
    }

    /**
     * Adds a new Todo to the list.
     *
     * @param description Description of the Todo.
     * @return String representation of the Todo.
     */
    public String addTodo(String description) {
        Todo todo = new Todo(description);
        tasks.add(todo);
        size++;
        return todo.toString();
    }

    /**
     * Adds a new Deadline to the list.
     *
     * @param description Description of the Deadline.
     * @param by The date/time when the Deadline is due.
     * @return String representation of the Deadline.
     */
    public String addDeadline(String description, LocalDate by) {
        Deadline deadline = new Deadline(description, by);
        tasks.add(deadline);
        size++;
        return deadline.toString();
    }

    /**
     * Adds a new Event to the list.
     *
     * @param description Description of the Event.
     * @param at The date/time when the Event happens.
     * @return String representation of the Event.
     */
    public String addEvent(String description, LocalDate at) {
        Event event = new Event(description, at);
        tasks.add(event);
        size++;
        return event.toString();
    }

    /**
     * Deletes the Task at the given index.
     *
     * @param idx Given index.
     * @return String representation of the Task deleted.
     */
    public String deleteTask(int idx) {
        Task deleted = tasks.remove(idx);
        size--;
        return deleted.toString();
    }

    /**
     * Returns the String representation of the TaskList.
     *
     * @return String representation of the TaskList.
     */
    @Override
    public String toString() {
        if (size == 0) {
            return "There are no tasks in your list.";
        } else {
            StringBuilder sb = new StringBuilder(String.format("Here %s the task%s in your list:",
                    size > 1 ? "are" : "is", size > 1 ? "s" : ""));
            for (int i = 1; i <= size; i++) {
                sb.append(String.format("%n%d.%s", i, tasks.get(i - 1)));
            }
            return sb.toString();
        }
    }
}
