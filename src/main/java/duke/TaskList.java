package duke;

import java.io.IOException;
import java.util.List;

/**
 * Stores and manages the tasks
 */
public class TaskList {
    private final List<Task> tasks;
    private final Storage storage;

    /**
     * A constructor for the TaskList class
     *
     * @param tl The list of tasks
     * @param s The storage that manages a file
     */
    public TaskList(List<Task> tl, Storage s) {
        this.tasks = tl;
        this.storage = s;
    }

    /**
     * Obtains the number of tasks in the <code>TaskList</code>
     * @return The total number of tasks
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Prints out all the tasks in the <code>TaskList</code>
     *
     * @param ui The <code>Ui</code> to print out the tasks or any messages
     * @throws DukeException If there is a Duke-specific error encountered
     */
    public void listTasks(Ui ui) throws DukeException {
        if (this.tasks.size() == 0) { // List is empty
            ui.printEmptyListMessage();
        } else {
            ui.printLine();
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < this.tasks.size(); i++) {
                Task curTask = this.tasks.get(i);
                System.out.println((i + 1) + "." + curTask.toString());
            }
            ui.printLine();
        }
    }

    /**
     * Marks a specific task in the list of tasks as done
     *
     * @param index The specific task number
     * @param ui The <code>Ui</code> to print out any messages
     * @throws IOException If the task index is invalid
     */
    public void markTask(int index, Ui ui) throws IOException {
        Task taskChosen = this.tasks.get(index);
        taskChosen.markAsDone();
        ui.printTaskMarked(taskChosen);
        storage.refreshList(this.tasks);
    }

    /**
     * Marks a specific task in the list of tasks as not done
     *
     * @param index The specific task number
     * @param ui The <code>Ui</code> to print out any messages
     * @throws IOException If the task index is invalid
     */
    public void unmarkTask(int index, Ui ui) throws IOException {
        Task taskChosen = this.tasks.get(index);
        taskChosen.markAsUndone();
        ui.printTaskUnmarked(taskChosen);
        storage.refreshList(this.tasks);
    }

    /**
     * Creates a <code>Todo</code> task
     *
     * @param desc The description of the task
     * @param ui The <code>Ui</code> to print out any messages
     * @throws IOException If the task description cannot be interpreted
     */
    public void createToDo(String[] desc, Ui ui) throws IOException {
        Todo newToDo = new Todo(String.join(" ", desc));
        this.tasks.add(newToDo);
        ui.printToDoCreated(newToDo, getSize());
        storage.refreshList(this.tasks);
    }

    /**
     * Creates a <code>Deadline</code> task
     *
     * @param desc The description of the task
     * @param ui The <code>Ui</code> to print out any messages
     * @throws IOException If the task description cannot be interpreted
     */
    public void createDeadline(String[] desc, Ui ui) throws IOException {
        Deadline newDeadline = new Deadline(desc[0], desc[1]);
        this.tasks.add(newDeadline);
        ui.printDeadlineCreated(newDeadline, getSize());
        storage.refreshList(this.tasks);
    }

    /**
     * Creates a <code>Event</code> task
     *
     * @param desc The description of the task
     * @param ui The <code>Ui</code> to print out any messages
     * @throws IOException If the task description cannot be interpreted
     */
    public void createEvent(String[] desc, Ui ui) throws IOException {
        Event newEvent = new Event(desc[0], desc[1]);
        this.tasks.add(newEvent);
        ui.printEventCreated(newEvent, getSize());
        storage.refreshList(this.tasks);
    }

    /**
     * Deletes a specific task in the list of tasks
     *
     * @param index The specific task number
     * @param ui The <code>Ui</code> to print out any messages
     * @throws IOException If the task index is invalid
     */
    public void deleteTask(int index, Ui ui) throws IOException{
        Task taskToRemove = this.tasks.get(index);
        this.tasks.remove(index);
        ui.printTaskDeleted(taskToRemove, getSize());
        storage.refreshList(this.tasks);
    }
}
