package duke;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Stream;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * TaskList contains the list of Task(s) and operations to modify the list.
 *
 * @author Samsation
 * @version CS2103T AY 22/23 Sem 1
 *
 */

public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * A constructor for TaskList without saved Task(s).
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * A constructor for TaskList with saved Task(s).
     *
     * @param dataArrayList ArrayList containing the data of saved Task(s).
     */
    public TaskList(ArrayList<String> dataArrayList) {
        this.tasks = new ArrayList<>();
        for (String data : dataArrayList) {
            tasks.add(Task.loadToTaskList(data));
        }
    }

    /**
     * Returns if the list is empty.
     *
     * @return Is the list empty.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns the size of the list.
     *
     * @return Size of the list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * A method that retrieves a Task from the list.
     *
     * @param index Index of the Task to be retrieved, with respect to the TaskList.
     * @return Task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Marks a Task from the list.
     *
     * @param index Index of the Task to be marked, with respect to the TaskList.
     */
    public void markTask(int index) {
        tasks.get(index).markAsDone();
    }

    /**
     * Un-marks a Task from the list.
     *
     * @param index Index of the Task to be un-marked, with respect to the TaskList.
     */
    public void unmarkTask(int index) {
        tasks.get(index).markAsUndone();
    }

    /**
     * Adds a ToDo to the list.
     *
     * @param description The description of the ToDo.
     */
    public void addToDo(String description) {
        ToDo toDo = new ToDo(description, false);
        tasks.add(toDo);
    }

    /**
     * Adds a Deadline to the list.
     *
     * @param description The description of the Deadline.
     * @param date The due date of the Deadline.
     */
    public void addDeadline(String description, LocalDate date) {
        Deadline deadline = new Deadline(description, false, date);
        tasks.add(deadline);
    }

    /**
     * Adds an Event to the list.
     *
     * @param description The description of the Event.
     * @param date The due date of the Event.
     */
    public void addEvent(String description, LocalDate date) {
        Event event = new Event(description, false, date);
        tasks.add(event);
    }

    /**
     * Deletes a Task from the list.
     *
     * @param index Index of the Task to be deleted, with respect to the TaskList.
     * @return The deleted Task.
     */
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Searches the TaskList for Task(s) with the specified keyword(s).
     *
     * @param keywords The keyword(s) to search for.
     * @return The list of Task(s) found with the specified keyword.
     */
    public ArrayList<Task> findTask(String ... keywords) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            for (String keyword : keywords) {
                if (task.hasKeyword(keyword)) {
                    foundTasks.add(task);
                }
            }
        }
        return foundTasks;
    }

    /**
     * Converts list into data to be saved in Storage.
     *
     * @return List containing data of the Task(s) to be saved in Storage.
     */
    public ArrayList<String> saveToStorage() {
        ArrayList<String> dataArrayList = new ArrayList<>();
        for (Task task : tasks) {
            dataArrayList.add(task.saveStringFormat());
        }
        return dataArrayList;
    }

    /**
     * Converts the TaskList into its String representation.
     *
     * @return String representation of the TaskList.
     */
    @Override
    public String toString() {
        int size = tasks.size();
        return Stream.iterate(0, i -> i + 1)
                .limit(size)
                .map(j -> (j == size - 1)
                        ? String.format("\t%d.%s", j + 1, tasks.get(j))
                        : String.format("\t%d.%s\n", j + 1, tasks.get(j)))
                .reduce("Here are the task(s) in your list:\n", (x, y) -> x + y);
    }
}

