package duke;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;


import javafx.scene.control.Label;

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

//    public static void main(String[] args) {
//        //Run bot
//        Duke duke = new Duke("src/tasks/tasks.txt");
//        Parser parser = new Parser(duke);
//        duke.run(parser);
//    }

    public String getResponse(String input, Duke duke) {
        Parser parser = new Parser(duke);
        return parser.start(input);
    }

//    private void run(Parser parser) {
//        ui.hello();
//        parser.start();
//        ui.bye();
//    }

    /**
     * Handles a Todo task inputted by user by calling on TaskList and Ui objects.
     *
     * @param tDescription A String of the description for the task.
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
     */
    public String printList() {
        return ui.listTasks(tasks);
    }

    /**
     * Calls TaskList and Ui objects to handle the marking of a task.
     *
     * @param index The index of the task to be marked.
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

    public String handleBye() {
        return ui.bye();
    }

}

