package duke;
import java.io.IOException;

/**
 * Duke is an interactive chatbot that keeps track of tasks inputted by user
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor of the Duke class
     * @param filePath
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
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
     * A method that calls TaskList and Ui objects to handle aTodo task inputted by user
     * @param tDescription A String of the description for the task
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
     * A method that calls TaskList and Ui objects to handle a Deadline task inputted by user
     * @param dDescription A String of the description for the task
     * @param dBy A String representing the deadline for the task
     */
    public void addDeadline(String dDescription, String dBy) {
        Task deadline = new Deadline(dDescription, dBy);
        tasks.addTask(deadline);
        int size = tasks.getSize();
        ui.printDeadline(deadline, size);
        storage.save(tasks);
    }

    /**
     * A method that calls TaskList and Ui objects to handle an Event task inputted by user
     * @param eDescription A String of the description for the task
     * @param eAt A String representing the day for the task
     */
    public void addEvent(String eDescription, String eAt) {
        Task event = new Event(eDescription, eAt);
        tasks.addTask(event);
        int size = tasks.getSize();
        ui.printEvent(event, size);
        storage.save(tasks);
    }

    /**
     * A method that calls Ui object to print the list of tasks
     */
    public void printList() {
        ui.listTasks(tasks);
    }

    /**
     * A method that calls TaskList and Ui objects to handle the marking of a task
     * @param index The index of the task to be marked
     * @throws DukeException
     */
    public void handleMark(int index) throws DukeException {
        tasks.markTask(index);
        Task taskToBeMarked = tasks.getTask(index);
        ui.printMarkedTask(taskToBeMarked);
        storage.save(tasks);
    }

    /**
     * A method that calls TaskList and Ui objects to handle the unmarking of a task
     * @param index The index of the task to be unmarked
     * @throws DukeException
     */
    public void handleUnmark(int index) throws DukeException {
        tasks.unmarkTask(index);
        Task taskToBeUnmarked = tasks.getTask(index);
        ui.printUnmarkedTask(taskToBeUnmarked);
        storage.save(tasks);
    }

    /**
     * A method that calls TaskList and Ui objects to handle the deleting of a task
     * @param index The index of the task to be deleted
     * @throws DukeException
     */
    public void handleDelete(int index) throws DukeException {
        Task taskToBeDeleted = tasks.deleteTask(index);
        int size = tasks.getSize();
        ui.printDelete(taskToBeDeleted, size);
        storage.save(tasks);
    }

}

