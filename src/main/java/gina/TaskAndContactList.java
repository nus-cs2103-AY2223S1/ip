package gina;

import gina.task.Task;
import java.util.ArrayList;

/**
 * Contains and manages the list of tasks and contacts.
 */
public class TaskAndContactList {
    private ArrayList<Task> tasks;
    private ArrayList<Contact> contacts;

    /**
     * Constructs an empty task list and contacts list.
     */
    public TaskAndContactList() {
        tasks = new ArrayList<>();
        contacts = new ArrayList<>();
    }

    /**
     * Constructs a list containing the specified tasks and contacts.
     *
     * @param tasks The specified tasks.
     * @param contacts The specified contacts.
     */
    public TaskAndContactList(ArrayList<Task> tasks, ArrayList<Contact> contacts) {
        this.tasks = tasks;
        this.contacts = contacts;
        assert(tasks != null);
    }

    /**
     * Returns the task at the specified index.
     *
     * @param i Index of the task in the task list.
     * @return Task at the specified index.
     * @throws GinaException If index is out of bounds.
     */
    public Task getTask(int i) throws GinaException {
        try {
            return tasks.get(i);
        } catch (IndexOutOfBoundsException e) {
            throw new GinaException("Please enter a valid task number!");
        }
    }

    /**
     * Returns the contact at the specified index.
     *
     * @param i Index of the task in the contact list.
     * @return Contact at the specified index.
     * @throws GinaException If index is out of bounds.
     */
    public Contact getContact(int i) throws GinaException {
        try {
            return contacts.get(i);
        } catch (IndexOutOfBoundsException e) {
            throw new GinaException("Please enter a valid contact index number!");
        }
    }

    /**
     * Returns the size of the task list.
     *
     * @return Size of task list.
     */
    public int tasksSize() {
        return tasks.size();
    }

    /**
     * Returns the size of the contacts list.
     *
     * @return Size of the contacts list.
     */
    public int contactsSize() {
        return contacts.size();
    }

    /**
     * Converts an ArrayList of Tasks to a list as a String.
     *
     * @param tasks List of tasks.
     * @return List of tasks as a string.
     */
    public static String convertTasksToListString(ArrayList<Task> tasks) {
        assert(tasks != null);
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            String newLine = "T" + (i + 1) + " " + tasks.get(i).toString() + "\n";
            list.append(newLine);
        }
        return list.toString();
    }

    /**
     * Converts an ArrayList of contacts to a string.
     *
     * @param contacts The list of contacts.
     * @return The list of tasks as a string.
     */
    public static String convertContactsToListString(ArrayList<Contact> contacts) {
        assert(contacts != null);
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < contacts.size(); i++) {
            String newLine = "C" + (i + 1) + " " + contacts.get(i).toString() + "\n";
            list.append(newLine);
        }
        return list.toString();
    }

    /**
     * Adds task to task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        assert(task != null);
        tasks.add(task);
    }

    /**
     * Adds contact to contact list.
     *
     * @param contact The contact to be added.
     */
    public void addContact(Contact contact) {
        assert(contact != null);
        contacts.add(contact);
    }

    /**
     * Deletes the specified task from task list.
     *
     * @param index The index of the specified task in the list.
     * @return Deleted task.
     * @throws GinaException If index is out of bounds.
     */
    public Task deleteTask(int index) throws GinaException {
        try {
            return tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new GinaException("I can't find such a task to delete!");
        }
    }

    /**
     * Deletes the specified contact from the contact list.
     *
     * @param index The index of the specified contact in the list.
     * @return The deleted contact.
     * @throws GinaException If the index is out of bounds.
     */
    public Contact deleteContact(int index) throws GinaException {
        try {
            return contacts.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new GinaException("I can't find such a contact to delete!");
        }
    }

    /**
     * Returns a task and contacts list with tasks on the specified date.
     *
     * @param dateStr The specified date.
     * @return Task and contacts list with specified date.
     * @throws GinaException If date is blank or in the wrong format.
     */
    public TaskAndContactList getTasksOnDate(String dateStr) throws GinaException {
        ArrayList<Task> tasksOnDate = new ArrayList<>();
        for (Task t : tasks) {
            if (t.isOnThisDate(dateStr)) {
                tasksOnDate.add(t);
            }
        }
        return new TaskAndContactList(tasksOnDate, new ArrayList<>());
    }

    /**
     * Returns a task and contacts list containing tasks with a specified keyword.
     *
     * @param input The specified keyword.
     * @return The list of tasks containing the keyword.
     * @throws GinaException If the input is incorrect.
     */
    public TaskAndContactList getTasksWithWord(String input) throws GinaException {
        ArrayList<Task> tasksWithWord = new ArrayList<>();
        for (Task t : tasks) {
            if (t.doesDescriptionContain(input)) {
                tasksWithWord.add(t);
            }
        }
        return new TaskAndContactList(tasksWithWord, new ArrayList<>());
    }

    /**
     * Gets the list of tasks as a string.
     *
     * @return The list of tasks.
     */
    protected String getTaskList() {
        return convertTasksToListString(tasks);
    }

    /**
     * Gets the list of contacts as a string.
     *
     * @return The list of contacts.
     */
    protected String getContactsList() {
        return convertContactsToListString(contacts);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Tasks:\n" + convertTasksToListString(tasks)
                + "Contacts:\n" + convertContactsToListString(contacts);
    }
}
