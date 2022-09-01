package bloop;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Operations related to the list of tasks.
 */
public class TaskList {

    /** list of tasks */
    private static ArrayList<Task> tasks;
    private Ui ui;
    private Storage storage;

    /**
     * Constructor for TaskList object.
     *
     * @param ui Object of class Ui.
     * @param storage Object of class Storage.
     */
    public TaskList(Ui ui, Storage storage) {
        this.ui = ui;
        tasks = new ArrayList<>();
        this.storage = storage;
    }

    public ArrayList<Task> getList() {
        return tasks;
    }

    /**
     * Adds a task to the list of tasks.
     *
     * @param task Task to be added to the list of tasks.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Gets the task in the list at the specified index.
     *
     * @param index index at which the task is present.
     * @return Task in the list at the specified index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Marks the specified task as done.
     *
     * @param task Task to be marked as done.
     * @throws IOException If there is a problem writing to file.
     */
    public void mark(Task task) throws IOException {
        task.mark();
        ui.print("This task has been marked as done -\n\t\t" + task);
        storage.rewriteFile(tasks);
    }

    /**
     * Marks the specified task as not done.
     *
     * @param task Task to be marked as not done.
     * @throws IOException If there is a problem writing to file.
     */
    public void unmark(Task task) throws IOException {
        task.unmark();
        ui.print("This task has been marked as not done -\n\t\t" + task);
        storage.rewriteFile(tasks);
    }

    /**
     * Removes the specified task from the list.
     *
     * @param task Task to be removed from the list.
     * @throws IOException If there is a problem writing to file.
     */
    public void remove(Task task) throws IOException {
        tasks.remove(task);
        storage.rewriteFile(tasks);
        ui.print("This task has been removed -\n\t\t" + task
                + "\n\tNow you have " + tasks.size() + " tasks in the list");
    }

    /**
     * Displays all the tasks in the list.
     */
    public void listOut() {
        System.out.println(ui.getSeparator());
        System.out.println("\tTasks in your list -");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t\t" + (i + 1) + ". " + tasks.get(i));
        }
        System.out.println(ui.getSeparator());
    }

    /**
     * Adds a task to the list.
     *
     * @param input Input from the user.
     * @param type Type of the task.
     * @throws BloopException If the task is not specified.
     */
    public void addTask(String input, char type) throws BloopException {
        Task task;
        if (type == 'T') {
            if (input.trim().length() == 4) {
                throw new BloopException("There is no task to do");
            }
            task = new ToDo(input.substring(5));
        } else {
            int index = input.indexOf('/');
            if (type == 'E') {
                if (input.trim().length() == 5) {
                    throw new BloopException("No event specified");
                }
                task = new Event(input.substring(6, index), input.substring(index + 3));
            } else {
                if (input.trim().length() == 8) {
                    throw new BloopException("No deadline specified");
                }
                task = new Deadline(input.substring(9, index), input.substring(index + 3));
            }
        }
        tasks.add(task);
        try {
            storage.writeToFile(task);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        ui.print("I've added this task -\n\t\t" + task + "\n\tNow you have " + tasks.size() + " tasks in the list");
    }

    /**
     * Looks for all the tasks that have the keyword in them.
     *
     * @param keyword The keyword contained in tasks.
     */
    public void findTasks(String keyword) {
        System.out.println(ui.getSeparator());
        System.out.println("\tMatching tasks -");
        int counter = 0;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getTask().contains(keyword)) {
                System.out.println("\t\t" + (++counter) + ". " + task);
            }
        }
        System.out.println(ui.getSeparator());
    }
}
