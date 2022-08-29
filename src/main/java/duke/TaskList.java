package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Stores an arraylist that keeps tracks of tasks.
 *
 */
public class TaskList {

    private ArrayList<Task> arrayList;
    private final Storage storage;

    /** Constructor for a Tasklist object */
    protected TaskList() {
        this.storage = new Storage();
        this.arrayList = this.storage.readFileAtStart();
    }

    /**
     * Writes the contents of the arraylist into file.
     *
     * @throws IOException If unable to save changes to file.
     */
    private void saveChangesToFile() throws IOException {
        this.storage.saveDataToFile(this.arrayList);
    }

    /**
     * Prints out all tasks with the specified taskname.
     *
     */
    protected void findAllTasksWith(String name) {

        ArrayList<Task> foundTasks = new ArrayList<>();

        for (Task task : this.arrayList) {
            if (task.getTaskname().contains(name)) {
                foundTasks.add(task);
            }
        }

        if (!foundTasks.isEmpty()) {
            System.out.printf("Found %d matching tasks in your list:\n", foundTasks.size());

            for (int i = 0; i < foundTasks.size(); i++) {
                int j = i + 1;
                System.out.println(j + "." + foundTasks.get(i));
            }

        } else {
            System.out.println("Unable to find any task containing: " + name);
        }
    }

    /**
     * Prints out all the contents of the tasks in the arraylist.
     *
     */
    protected void listTasks() {
        System.out.println("Here are the tasks in your current list:");

        if (arrayList.size() == 0) {
            System.out.println("  Wow! You have no tasks to do currently!!");
        }

        for (int i = 0; i < arrayList.size(); i++) {
            int j = i + 1;
            System.out.println(j + "." + arrayList.get(i));
        }
    }

    /**
     * Marks task as done at a specified index.
     *
     * @param i Index of the task.
     * @throws IOException If unable to save changes to file.
     */
    protected void markTaskDoneAt(int i) throws IOException {
        this.arrayList.get(i).markDone();
        this.saveChangesToFile();

        System.out.println("Nice! You actually did something:");
        System.out.println(" " + this.arrayList.get(i));
    }

    /**
     * Marks task as not done at a specified index.
     *
     * @param i Index of the task.
     * @throws IOException If unable to save changes to file.
     */
    protected void markTaskNotDoneAt(int i) throws IOException {
        this.arrayList.get(i).markNotDone();
        this.saveChangesToFile();

        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(" " + this.arrayList.get(i));
    }

    /**
     * Deletes task at a specified index.
     *
     * @param i Index of the task.
     * @throws IOException If unable to save changes to file.
     */
    protected void deleteTaskAt(int i) throws IOException {
        this.arrayList.get(i).markNotDone();
        Task deletedTask = arrayList.remove(i);
        this.saveChangesToFile();

        System.out.println("Noted. I've removed this task:");
        System.out.println(" " + deletedTask);
        System.out.println("Now you have " + arrayList.size() + " task(s) in the list.");
    }

    /**
     * Adds a ToDo task at the end of the arraylist.
     *
     * @param taskname Name of the task.
     * @throws IOException If unable to save changes to file.
     */
    protected void addToDo(String taskname) throws IOException {
        Task todo = new Todo(taskname.trim());
        arrayList.add(todo);
        this.saveChangesToFile();

        System.out.println("Got it. I've added this task:");
        System.out.println(" " + todo);
        System.out.println("Now you have " + arrayList.size() + " task(s) in the list.");
    }

    /**
     * Adds a Deadline task at the end of the arraylist.
     *
     * @param taskname Name of the task.
     * @param localDate Date of the deadline.
     * @throws IOException If unable to save changes to file.
     */
    protected void addDeadline(String taskname, LocalDate localDate) throws IOException {
        Task deadline = new Deadline(taskname.trim(), localDate);
        arrayList.add(deadline);
        this.saveChangesToFile();

        System.out.println("Got it. I've added this task:");
        System.out.println(" " + deadline);
        System.out.println("Now you have " + arrayList.size() + " task(s) in the list.");
    }

    /**
     * Adds an Event task at the end of the arraylist.
     *
     * @param taskname Name of the task.
     * @param localDate Date of the deadline.
     * @throws IOException If unable to save changes to file.
     */
    protected void addEvent(String taskname, LocalDate localDate) throws IOException {
        Task event = new Event(taskname.trim(), localDate);
        arrayList.add(event);
        this.saveChangesToFile();

        System.out.println("Got it. I've added this task:");
        System.out.println(" " + event);
        System.out.println("Now you have " + arrayList.size() + " task(s) in the list.");
    }

}
