package duke;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * class Duke is the main class for a chatbot named ashy
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();

        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (IOException e) {
            System.out.println("OOPS! I have issue creating a new file!");
        }
    }

    public static void main(String[] args) {
        //Run bot
        Duke duke = new Duke("src/tasks/tasks.txt");
        Parser parser = new Parser(duke);
        duke.run(parser);
    }

    private void run(Parser parser) {
        ui.hello();
        parser.start();
        ui.bye();
    }

    /**
     * Handles a Todo task inputted by user by calling on TaskList and Ui objects.
     *
     * @param tDescription A String of the description for the task.
     * @throws DukeException
     */
    public void addTodo(String tDescription) throws DukeException {
        if (tDescription.equals("")) {
            throw new DukeException("OOPS! The description of a todo cannot be empty.");
        } else {
            Task todo = new Todo(tDescription);
            tasks.addTask(todo);
            int size = tasks.getSize();
            ui.printTodo(todo, size);
            storage.save(tasks);
        }
    }

    /**
     * Handles a Deadline task inputted by user by calling on TaskList and Ui objects.
     *
     * @param str A string representing the entire input user keyed in after "deadline".
     */
    public void addDeadline(String str) throws DukeException {
        LocalDate date;
        String dDescription;
        try {
            if (str.equals("")) {
                throw new DukeException("OOPS! The description of a todo cannot be empty.");
            }
            dDescription = str.substring(0, str.indexOf('/') - 1);
            String dBy = str.substring(str.indexOf('/') + 4);
            date = LocalDate.parse(dBy);
        } catch (DateTimeException e) {
            throw new DukeException("OOPS! Date must be in proper format!");
        }
        Task deadline = new Deadline(dDescription, date);
        tasks.addTask(deadline);
        int size = tasks.getSize();
        ui.printDeadline(deadline, size);
        storage.save(tasks);
    }

    /**
     * Handles an Event task inputted by user by calling on TaskList and Ui objects.
     *
     * @param str A string representing the entire input user keyed in after "event".
     */
    public void addEvent(String str) throws DukeException {
        String eDescription;
        String eAt;
        if (str.equals("")) {
            throw new DukeException("OOPS! The description of a todo cannot be empty.");
        } else {
            eDescription = str.substring(0, str.indexOf('/') - 1);
            eAt = str.substring(str.indexOf('/') + 4);
            Task event = new Event(eDescription, eAt);
            tasks.addTask(event);
            int size = tasks.getSize();
            ui.printEvent(event, size);
            storage.save(tasks);
        }
    }

    /**
     * Calls Ui object to print the list of tasks.
     */
    public void printList() {
        ui.listTasks(tasks);
    }

    /**
     * Calls TaskList and Ui objects to handle the marking of a task.
     *
     * @param index The index of the task to be marked.
     * @throws DukeException
     */
    public void handleMark(int index) throws DukeException {
        tasks.markTask(index);
        Task taskToBeMarked = tasks.getTask(index);
        ui.printMarkedTask(taskToBeMarked);
        storage.save(tasks);
    }

    /**
     * Calls TaskList and Ui objects to handle the unmarking of a task.
     *
     * @param index The index of the task to be unmarked.
     * @throws DukeException
     */
    public void handleUnmark(int index) throws DukeException {
        tasks.unmarkTask(index);
        Task taskToBeUnmarked = tasks.getTask(index);
        ui.printUnmarkedTask(taskToBeUnmarked);
        storage.save(tasks);
    }

    /**
     * Calls TaskList and Ui objects to handle the deleting of a task.
     *
     * @param index The index of the task to be deleted.
     * @throws DukeException
     */
    public void handleDelete(int index) throws DukeException {
        Task taskToBeDeleted = tasks.deleteTask(index);
        int size = tasks.getSize();
        ui.printDelete(taskToBeDeleted, size);
        storage.save(tasks);
    }

    /**
     * Filters the list based on keyword
     * Calls Ui object to display the list of filtered tasks.
     *
     * @param str The keyword inputted by user in the format of String.
     */
    public void find(String str) {
        ArrayList<Task> lst = tasks.getTasks();
        ArrayList<Task> filtered = new ArrayList<>();
        for (Task task : lst) {
            String description = task.description;
            if (description.contains(str)) {
                filtered.add(task);
            }
        }
        ui.printFind(filtered);


    }

}
