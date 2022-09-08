package duke;

import java.util.ArrayList;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * The TaskList class contains the list of tasks used in Duke.
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    /**
     * Constructor for TaskList from a given ArrayList.
     * @param list The given ArrayList containing a number of Task objects.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Prints the TaskList.
     */
    public void printTaskList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + "." + list.get(i).toString());
        }
    }

    /**
     * Deletes the Task at index from the TaskList.
     * @param index The position of the Task to be deleted.
     */
    public Task deleteTask(int index) {
        Task task = this.list.get(index);
        this.list.remove(index);
        return task;
    }

    /**
     * Marks the Task at index.
     * @param index The position of the Task to be marked.
     */
    public Task markTask(int index) {
        this.list.get(index).markAsDone();
        return this.list.get(index);
    }

    /**
     * Unmarks the Task at index.
     * @param index The position of the Task to be unmarked.
     */
    public Task unmarkTask(int index) {
        this.list.get(index).markAsUndone();
        return this.list.get(index);
    }

    /**
     * Adds a Todo object in the TaskList.
     * @param description The description of the Todo object.
     */
    public Task addTodo(String description) {
        this.list.add(new Todo(description));
        return this.list.get(list.size() - 1);
    }

    /**
     * Adds a Deadline object in the TaskList.
     * @param description The description of the Deadline object.
     * @param by When the Deadline object has to be completed by.
     */
    public Task addDeadline(String description, String by) {
        this.list.add(new Deadline(description, by));
        return this.list.get(list.size() - 1);
    }

    /**
     * Adds an Event object in the TaskList.
     * @param description The description of the Event object.
     * @param when When the Event will occur.
     */
    public Task addEvent(String description, String when) {
        this.list.add(new Event(description, when));
        return this.list.get(list.size() - 1);
    }

    /**
     * Finds Tasks in the TaskList which have descriptions that
     * contain the keyword, and return a filtered TaskList containing
     * these Tasks.
     * @param keyword
     * @return Filtered TaskList.
     */
    public TaskList findTask(String keyword) {
        ArrayList<Task> filteredList = new ArrayList<>();

        for (Task t: this.list) {
            if (t.getDescription().contains(keyword)) {
                filteredList.add(t);
            }
        }

        return new TaskList(filteredList);
    }

    /**
     * Returns the number of Tasks.
     * @return number of Tasks in the TaskList.
     */
    public int getSize() {
        return this.list.size();
    }

    /**
     * Returns list.
     * @return list.
     */
    public ArrayList<Task> getList() {
        return this.list;
    }

    @Override
    public String toString() {
        StringBuilder listString = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            listString.append(i + 1 + "." + list.get(i).toString() + "\n");
        }
        return listString.toString();
    }
}
