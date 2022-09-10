package bloop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Operations related to the list of tasks.
 */
public class TaskList {

    /** list of tasks */
    private ArrayList<Task> tasks;
    private Storage storage;

    /**
     * Constructor for TaskList object.
     *
     * @param storage Object of class Storage.
     */
    public TaskList(Storage storage) {
        this.storage = storage;
        tasks = new ArrayList<>();
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
     * @return message.
     * @throws IOException If there is a problem writing to file.
     */
    public String mark(Task task) throws IOException {
        task.mark();
        storage.rewriteFile(tasks);
        return "This task has been marked as done -\n\t" + task;
    }

    /**
     * Marks the specified task as not done.
     *
     * @param task Task to be marked as not done.
     * @return message.
     * @throws IOException If there is a problem writing to file.
     */
    public String unmark(Task task) throws IOException {
        task.unmark();
        storage.rewriteFile(tasks);
        return "This task has been marked as not done -\n\t" + task;
    }

    /**
     * Removes the specified task from the list.
     *
     * @param task Task to be removed from the list.
     * @return message
     * @throws IOException If there is a problem writing to file.
     */
    public String remove(Task task) throws IOException {
        tasks.remove(task);
        storage.rewriteFile(tasks);
        return "This task has been removed -\n\t" + task
                + "\n\tNow you have " + tasks.size() + " tasks in the list";
    }

    /**
     * Displays all the tasks in the list.
     *
     * @return message.
     */
    public String listOut(ArrayList<? extends Task> tasks) {
        String list = "Tasks in your list -";
        for (int i = 0; i < tasks.size(); i++) {
            list += "\n\t" + (i + 1) + ". " + tasks.get(i);
        }
        return list;
    }

    /**
     * Adds a task to the list.
     *
     * @param input Input from the user.
     * @param type Type of the task.
     * @return message.
     * @throws BloopException If the task is not specified.
     */
    public String addTask(String input, char type) throws BloopException {
        assert Character.toUpperCase(input.split(" ")[0].charAt(0)) == type : "Wrong type";
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
            return e.getMessage();
        }
        return "I've added this task -\n" + task + "\n\tNow you have " + tasks.size() + " tasks in the list";
    }

    /**
     * Looks for all the tasks that have the keyword in them.
     *
     * @param keyword The keyword contained in tasks.
     * @return message.
     */
    public String findTasks(String keyword) {
        String list = "Matching tasks -";
        int counter = 0;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getTask().contains(keyword)) {
                list += "\n\t" + (++counter) + ". " + task;
            }
        }
        return list;
    }

    public String sortDeadlines() {
        String list = "Deadlines sorted by time -";
        ArrayList<Deadline> deadlines = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getType() == 'D') {
                deadlines.add((Deadline) task);
            }
        }
        deadlines.sort(Comparator.comparing(Deadline::getDateTime));
        return listOut(deadlines);
    }

}
