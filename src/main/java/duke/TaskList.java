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

    private final String NEWLINE = System.lineSeparator();

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
     * @return A String containing all found tasks, each separated by a new line.
     */
    protected String findAllTasksWith(String name) {

        ArrayList<Task> foundTasks = new ArrayList<>();

        for (Task task : this.arrayList) {
            if (task.getTaskname().contains(name)) {
                foundTasks.add(task);
            }
        }

        if (!foundTasks.isEmpty()) {
            String intro = String.format("Found %d matching tasks in your list:\n", foundTasks.size());

            for (int i = 0; i < foundTasks.size(); i++) {
                int j = i + 1;
                intro += j + "." + foundTasks.get(i);
            }

            return intro;

        } else {
            return "Unable to find any task containing: " + name;
        }
    }

    /**
     * Prints out all the contents of the tasks in the arraylist.
     *
     * @return A String containing all tasks, each separated by a new line.
     */
    protected String listTasks() {

        if (arrayList.size() == 0) {
            return "Wow! You have no tasks to do currently!!" + NEWLINE;
        }

        String intro = "You have " + arrayList.size() + " task(s):" + NEWLINE;

        for (int i = 0; i < arrayList.size(); i++) {
            int j = i + 1;
            intro += j + "." + arrayList.get(i) + NEWLINE;
        }

        return intro;
    }

    /**
     * Marks task as done at a specified index.
     *
     * @param i Index of the task.
     * @return Information of the marked task.
     * @throws IOException If unable to save changes to file.
     */
    protected String markTaskDoneAt(int i) throws IOException {
        this.arrayList.get(i).markDone();
        this.saveChangesToFile();

        return "Nice! You actually did something:" + NEWLINE
                + " " + this.arrayList.get(i) + NEWLINE;
    }

    /**
     * Marks task as not done at a specified index.
     *
     * @param i Index of the task.
     * @return Information of the unmarked task.
     * @throws IOException If unable to save changes to file.
     */
    protected String markTaskNotDoneAt(int i) throws IOException {
        this.arrayList.get(i).markNotDone();
        this.saveChangesToFile();

        return "OK, I've marked this task as not done yet" + NEWLINE
                + " " + this.arrayList.get(i) + NEWLINE;
    }

    /**
     * Deletes task at a specified index.
     *
     * @param i Index of the task.
     * @return Information of the deleted task.
     * @throws IOException If unable to save changes to file.
     */
    protected String deleteTaskAt(int i) throws IOException {
        Task deletedTask = arrayList.remove(i);
        this.saveChangesToFile();

        return "Noted. I've removed this task:" + NEWLINE
                + " " + deletedTask + NEWLINE
                        + "Now you have " + arrayList.size() + " task(s) in the list." + NEWLINE;
    }

    /**
     * Adds a ToDo task at the end of the arraylist.
     *
     * @param taskname Name of the task.
     * @return Information of the added task.
     * @throws IOException If unable to save changes to file.
     */
    protected String addToDo(String taskname) throws IOException {
        Task todo = new Todo(taskname.trim());
        arrayList.add(todo);
        this.saveChangesToFile();

        return "Got it. I've added this task:" + NEWLINE
                + " " + todo + NEWLINE
                        + "Now you have " + arrayList.size() + " task(s) in the list." + NEWLINE;
    }

    /**
     * Adds a Deadline task at the end of the arraylist.
     *
     * @param taskname Name of the task.
     * @param localDate Date of the deadline.
     * @return Information of the added task.
     * @throws IOException If unable to save changes to file.
     */
    protected String addDeadline(String taskname, LocalDate localDate) throws IOException {
        Task deadline = new Deadline(taskname.trim(), localDate);
        arrayList.add(deadline);
        this.saveChangesToFile();

        return "Got it. I've added this task:" + NEWLINE
                + " " + deadline + NEWLINE
                        + "Now you have " + arrayList.size() + " task(s) in the list." + NEWLINE;
    }

    /**
     * Adds an Event task at the end of the arraylist.
     *
     * @param taskname Name of the task.
     * @param localDate Date of the deadline.
     * @return Information of the added task.
     * @throws IOException If unable to save changes to file.
     */
    protected String addEvent(String taskname, LocalDate localDate) throws IOException {
        Task event = new Event(taskname.trim(), localDate);
        arrayList.add(event);
        this.saveChangesToFile();

        return "Got it. I've added this task:" + NEWLINE
                + " " + event + NEWLINE
                        + "Now you have " + arrayList.size() + " task(s) in the list." + NEWLINE;
    }

}
