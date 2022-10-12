package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Stores and manages the tasks
 */
public class TaskList {

    private final List<Task> tasks;
    private final Storage storage;
    private boolean isClosed;

    /**
     * A constructor for the TaskList class
     *
     * @param tl The list of tasks
     * @param s The storage that manages a file
     */
    public TaskList(List<Task> tl, Storage s) {
        this.tasks = tl;
        this.storage = s;
        this.isClosed = false;
    }

    public void addTask(Task t) {
        this.tasks.add(t);
    }

    /**
     * Obtains the number of tasks in the <code>TaskList</code>
     *
     * @return The total number of tasks
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Obtains the list of tasks that is stored in the <code>TaskList</code>
     *
     * @return The list of tasks
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Prints out all the tasks in the <code>TaskList</code>
     *
     * @return Message to display
     * @throws DukeException If there is a Duke-specific error encountered
     */
    public String listTask() throws DukeException {
        String response = null;
        if (this.tasks.size() == 0) { // List is empty
            response = "Your list is empty! Why not add a task to it first?";
        } else {
            response = "Here are the tasks in your list:\n";
            for (int i = 0; i < this.tasks.size(); i++) {
                Task curTask = this.tasks.get(i);
                response = response.concat((i + 1) + "." + curTask.toString() + "\n");
            }
        }
        return response;
    }

    /**
     * Marks a specific task in the list of tasks as done
     *
     * @param index The specific task number
     * @return Message to display
     * @throws IOException If the task index is invalid
     */
    public Task markTask(int index) throws IndexOutOfBoundsException {
        Task taskChosen = this.tasks.get(index);
        taskChosen.markAsDone();
        return taskChosen;
    }

    /**
     * Marks a specific task in the list of tasks as not done
     *
     * @param index The specific task number
     * @return Message to display
     * @throws IOException If the task index is invalid
     */
    public Task unmarkTask(int index) throws IndexOutOfBoundsException {
        Task taskChosen = this.tasks.get(index);
        taskChosen.markAsUndone();
        return taskChosen;
    }

    /**
     * Creates a <code>Todo</code> task
     *
     * @param desc The description of the task
     * @return Message to display
     * @throws IOException If the task description cannot be interpreted
     */
    public String createToDo(String[] desc) throws IOException {
        Todo newToDo = new Todo(String.join(" ", desc));
        this.tasks.add(newToDo);
        String response = "Got it! I have added this task to your list:\n  "
                + newToDo.toString()
                + "\nNow you have " + getSize() + " tasks in the list.";
        storage.refreshList(this.tasks);
        return response;
    }

    /**
     * Creates a <code>Deadline</code> task
     *
     * @param desc The description of the task
     * @return Message to display
     * @throws IOException If the task description cannot be interpreted
     */
    public String createDeadline(String[] desc) throws IOException {
        Deadline newDeadline = new Deadline(desc[0], desc[1]);
        this.tasks.add(newDeadline);
        String response = "Got it! I have added this task to your list:\n  "
                + newDeadline.toString()
                + "\nNow you have " + getSize() + " tasks in the list.";
        storage.refreshList(this.tasks);
        return response;
    }

    /**
     * Creates a <code>Event</code> task
     *
     * @param desc The description of the task
     * @return Message to display
     * @throws IOException If the task description cannot be interpreted
     */
    public String createEvent(String[] desc) throws IOException {
        Event newEvent = new Event(desc[0], desc[1]);
        this.tasks.add(newEvent);
        String response = "Got it! I have added this task to your list:\n  "
                + newEvent.toString()
                + "\nNow you have " + getSize() + " tasks in the list.";
        storage.refreshList(this.tasks);
        return response;
    }

    /**
     * Deletes a specific task in the list of tasks
     *
     * @param index The specific task number
     * @return The task that is deleted
     */
    public Task deleteTask(int index) throws IndexOutOfBoundsException {
        Task taskToRemove = this.tasks.get(index);
        this.tasks.remove(index);
        return taskToRemove;
    }

    /**
     * Finds all tasks that has descriptions containing the keyword
     *
     * @param keyword Keyword to match
     * @return Message response to display
     */
    public List<Task> findTasks(String keyword) throws DukeException {
        if (this.tasks.size() == 0) { // List is empty
            throw new DukeException("Your list is empty! Why not add a task to it first?");
        }
        List<Task> matchingTasks = new ArrayList<>();
        for (int i = 0; i < this.tasks.size(); i++) {
            Task curTask = this.tasks.get(i);
            String taskDescription = curTask.getDescription().toUpperCase();
            if (taskDescription.contains(keyword)) {
                matchingTasks.add(curTask);
            } else {
                matchingTasks.add(null);
            }
        }

        return matchingTasks;
    }

    /**
     * Snooze a task that has a datetime
     *
     * @param index The index of the task
     * @param newDateTime The new DateTime to set
     * @return Message to display
     * @throws IOException If the task description cannot be interpreted
     * @throws DukeException If
     */
    public String snoozeTask(int index, String newDateTime) throws DukeException, DateTimeParseException {
        String response;
        Task chosenTask = this.tasks.get(index);
        try {
            response = chosenTask.setDatetime(newDateTime);
        } catch (DateTimeParseException err) {
            response = "I don't recognise this time format."
                    + "\nThe format for your new DateTime should be as followed below"
                    + "\nFor Events: dd/MM/yyyy HHmm"
                    + "\nFor Deadlines: dd/MM/yyyy[ HHmm]";
        }
        storage.refreshList(this.tasks);
        return response;
    }

    public void closeTaskList() {
        this.isClosed = true;
    }

    public boolean getTaskListStatus() {
        return this.isClosed;
    }
}
