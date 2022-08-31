package duke;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Duke is an interactive chatbot that keeps track of tasks inputted by user.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor of the Duke class.
     *
     */
    public Duke() {
        this.ui = new Ui();
        this.tasks = new TaskList();
        this.storage = new Storage("src/tasks/tasks.txt");
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showErrorMessage(e.getMessage());
            tasks = new TaskList();
        } catch (IOException e) {
            ui.showErrorMessage(e.getMessage());
        }

    }

    /**
     * Returns a String response based on the given input by user.
     *
     * @param input A String input by user.
     * @param duke A Duke object.
     * @return A String of the response associated with the user's input.
     */
    public String getResponse(String input, Duke duke) {
        Parser parser = new Parser(duke);
        return parser.start(input);
    }

    /**
     * Handles a Todo task inputted by user by calling on TaskList and Ui objects.
     *
     * @param tDescription A String of the description for the task.
     * @return A String of the response associated with the user's todo.
     * @throws DukeException
     */
    public String addTodo(String tDescription) throws DukeException {
            Task todo = new Todo(tDescription);
            tasks.addTask(todo);
            int size = tasks.getSize();
            storage.save(tasks);
            return ui.printTodo(todo, size);
    }

    /**
     * Handles a Deadline task inputted by user by calling on TaskList and Ui objects.
     *
     * @param str A string representing the entire input user keyed in after "deadline".
     * @return A String of the response associated with the user's deadline.
     * @throws DukeException
     */
    public String addDeadline(String str) throws DukeException {
        LocalDate date;
        String dDescription;
        try {
            dDescription = str.substring(0, str.indexOf('/') - 1);
            String dBy = str.substring(str.indexOf('/') + 4);
            date = LocalDate.parse(dBy);
        } catch (DateTimeException e) {
            throw new DukeException("OOPS! Date must be in proper format!");
        }
        Task deadline = new Deadline(dDescription, date);
        tasks.addTask(deadline);
        int size = tasks.getSize();
        storage.save(tasks);
        return ui.printDeadline(deadline, size);
    }

    /**
     * Handles an Event task inputted by user by calling on TaskList and Ui objects.
     *
     * @param str A string representing the entire input user keyed in after "event".
     * @return A String of the response associated with the user's event.
     * @throws DukeException
     */
    public String addEvent(String str) throws DukeException {
        String eDescription;
        String eAt;
        eDescription = str.substring(0, str.indexOf('/') - 1);
        eAt = str.substring(str.indexOf('/') + 4);
        Task event = new Event(eDescription, eAt);
        tasks.addTask(event);
        int size = tasks.getSize();
        storage.save(tasks);
        return ui.printEvent(event, size);
    }

    /**
     * Calls Ui object to print the list of tasks.
     *
     * @return A String of the response associated with the user's list.
     */
    public String printList() {
        return ui.listTasks(tasks);
    }

    /**
     * Calls TaskList and Ui objects to handle the marking of a task.
     *
     * @param index The index of the task to be marked.
     * @return A String of the response associated with the user's mark.
     * @throws DukeException
     */
    public String handleMark(int index) throws DukeException {
        tasks.markTask(index);
        Task taskToBeMarked = tasks.getTask(index);
        storage.save(tasks);
        return ui.printMarkedTask(taskToBeMarked);
    }

    /**
     * Calls TaskList and Ui objects to handle the unmarking of a task.
     *
     * @param index The index of the task to be unmarked.
     * @return A String of the response associated with the user's unmark.
     * @throws DukeException
     */
    public String handleUnmark(int index) throws DukeException {
        tasks.unmarkTask(index);
        Task taskToBeUnmarked = tasks.getTask(index);
        storage.save(tasks);
        return ui.printUnmarkedTask(taskToBeUnmarked);
    }

    /**
     * Calls TaskList and Ui objects to handle the deleting of a task.
     *
     * @param index The index of the task to be deleted.
     * @return A String of the response associated with the user's delete.
     * @throws DukeException
     */
    public String handleDelete(int index) throws DukeException {
        Task taskToBeDeleted = tasks.deleteTask(index);
        int size = tasks.getSize();
        storage.save(tasks);
        return ui.printDelete(taskToBeDeleted, size);
    }

    /**
     * Filters the list based on keyword specified and calls Ui object to display the list of filtered tasks.
     *
     * @param string The keyword inputted by user in the format of String.
     * @return A String of the response associated with the user's find.
     */
    public String find(String string) {
        ArrayList<Task> lst = tasks.getTasks();
        ArrayList<Task> filtered = new ArrayList<>();
        for (Task task : lst) {
            String description = task.description;
            if (description.contains(string)) {
                filtered.add(task);
            }
        }
        return ui.printFind(filtered);
    }

    /**
     * Returns a goodbye message to the user.
     *
     * @return A String of a goodbye message.
     */
    public String handleBye() {
        return ui.bye();
    }

}

