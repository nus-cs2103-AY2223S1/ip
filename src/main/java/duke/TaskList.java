package duke;

import java.io.IOException;
import java.time.format.DateTimeParseException;
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

    /**
     * Obtains the number of tasks in the <code>TaskList</code>
     *
     * @return The total number of tasks
     */
    public int getSize() {
        return this.tasks.size();
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
    public String markTask(int index) throws IOException {
        Task taskChosen = this.tasks.get(index);
        taskChosen.markAsDone();
        String response = "Okay, I have marked this task as done: \n"
                + taskChosen.toString();
        storage.refreshList(this.tasks);
        return response;
    }

    /**
     * Marks a specific task in the list of tasks as not done
     *
     * @param index The specific task number
     * @return Message to display
     * @throws IOException If the task index is invalid
     */
    public String unmarkTask(int index) throws IOException {
        Task taskChosen = this.tasks.get(index);
        taskChosen.markAsUndone();
        String response = "Okay, I have marked this task as not done: \n"
                + taskChosen.toString();
        storage.refreshList(this.tasks);
        return response;
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
     * @return Message to display
     * @throws IOException If the task index is invalid
     */
    public String deleteTask(int index) throws IOException{
        Task taskToRemove = this.tasks.get(index);
        this.tasks.remove(index);
        String response = "Okay, I have removed this task from the list:\n  "
                + taskToRemove.toString()
                + "\nNow you have " + getSize() + " tasks in the list.";
        storage.refreshList(this.tasks);
        return response;
    }

    /**
     * Finds all tasks that has descriptions containing the keyword
     *
     * @param keyword Keyword to match
     * @return Message response to display
     */
    public String findTask(String keyword) {
        String response = null;
        int numOfMatchingTasks = 0;
        if (this.tasks.size() == 0) { // List is empty
            response = "Your list is empty! Why not add a task to it first?";
        } else {
            response = "Here are the matching tasks in your list:\n";
            for (int i = 0; i < this.tasks.size(); i++) {
                Task curTask = this.tasks.get(i);
                if (curTask.getDescription().contains(keyword)) {
                    numOfMatchingTasks++;
                    response = response.concat((i + 1) + "." + curTask.toString() + "\n");
                }
            }
        }
        if (numOfMatchingTasks == 0) {
            response = "There is no matching task with your keyword";
        }
        return response;
    }

    /**
     * Snooze a task that has a datetime
     *
     * @param desc The description of the task
     * @return Message to display
     * @throws IOException If the task description cannot be interpreted
     * @throws DukeException If
     */
    public String snoozeTask(String[] desc) throws IOException, DukeException, DateTimeParseException {
        String response = null;
        int taskNum = Integer.parseInt(desc[0].replaceAll("\\s", "")) - 1;
        Task chosenTask = this.tasks.get(taskNum);
        response = chosenTask.setDatetime(desc[1]);
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
