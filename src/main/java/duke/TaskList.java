package duke;

import java.util.ArrayList;

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
    public void deleteTask(int index) {
        Ui.deleteTask(this.list.get(index));
        this.list.remove(index);
        Ui.numOfTasks(this.list.size());
    }

    /**
     * Marks the Task at index.
     * @param index The position of the Task to be marked.
     */
    public void markTask(int index) {
        this.list.get(index).markAsDone();
        Ui.markTask(this.list.get(index));
    }

    /**
     * Unmarks the Task at index.
     * @param index The position of the Task to be unmarked.
     */
    public void unmarkTask(int index) {
        this.list.get(index).markAsUndone();
        Ui.unmarkTask(this.list.get(index));
    }

    /**
     * Adds a Todo object in the TaskList.
     * @param description The description of the Todo object.
     */
    public void addTodo(String description) {
        this.list.add(new Todo(description));
        Ui.addTask(this.list.get(list.size() - 1));
        Ui.numOfTasks(this.list.size());
    }

    /**
     * Adds a Deadline object in the TaskList.
     * @param description The description of the Deadline object.
     * @param by When the Deadline object has to be completed by.
     */
    public void addDeadline(String description, String by) {
        this.list.add(new Deadline(description, by));
        Ui.addTask(this.list.get(list.size() - 1));
        Ui.numOfTasks(this.list.size());
    }

    /**
     * Adds an Event object in the TaskList.
     * @param description The description of the Event object.
     * @param when When the Event will occur.
     */
    public void addEvent(String description, String when) {
        this.list.add(new Event(description, when));
        Ui.addTask(this.list.get(list.size() - 1));
        Ui.numOfTasks(this.list.size());
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
}
