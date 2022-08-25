package duke;

import java.io.IOException;

/**
 * Duke is an interactive chat-bot that can record specified tasks
 */
public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructor for Duke class.
     *
     * @param filePath File path for storage file.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadSaveData());
        } catch (DukeException | IOException e) {
            ui.dukeReply(e.getMessage());
            tasks = new TaskList();
        }
    }

    // main method to run Duke
    public static void main(String[] args) {
        Duke duke = new Duke("src/data/duke.txt");
        duke.start();
        Parser parser = new Parser(duke);
        parser.parse();
        duke.ui.exit();
    }

    private void start() {
        ui.greet();
}

    /**
     * Prints list of tasks to console.
     */
    public void printList() {
        ui.printList(tasks);
    }

    /**
     * Marks a task and prints it to console.
     *
     * @param index The index of the task to be marked.
     * @throws DukeException
     */
    public void handleMark(int index) throws DukeException {
        tasks.mark(index);
        storage.save(tasks);
        ui.dukeReply("Nice! I've marked this task as done: \n  " + tasks.get(index));
    }

    /**
     * Unmarks a task and prints it to console.
     *
     * @param index The index of the task to be unmarked.
     * @throws DukeException
     */
    public void handleUnmark(int index) throws DukeException {
        tasks.unmark(index);
        storage.save(tasks);
        ui.dukeReply("OK, I've marked this task as not done yet: \n  " + tasks.get(index));
    }

    /**
     * Dispatches instructions for processing a ToDo task.
     *
     * @param input String description of the task.
     * @throws DukeException
     */
    public void handleToDo(String input) throws DukeException {
        Task todo = createToDo(input);
        tasks.addTask(todo);
        ui.echoTask(todo, tasks);
        storage.save(tasks);
    }

    private Task createToDo(String input) throws DukeException {
        try {
            String description = input.substring(5);
            return new ToDo(description);
        }
        catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    /**
     * Dispatches instructions for processing a Deadline task.
     *
     * @param input String description of the task.
     * @throws DukeException
     */
    public void handleDeadline(String input) throws DukeException {
        Task deadline = createDeadline(input);
        tasks.addTask(deadline);
        ui.echoTask(deadline, tasks);
        storage.save(tasks);
    }

    private Task createDeadline(String input) throws DukeException {
        int end = input.indexOf('/');
        String description = input.substring(9, end - 1);
        String by = input.substring(end + 4);
        return new Deadline(description, by);
    }

    /**
     * Dispatches instructions for processing an event task.
     *
     * @param input String description of the task.
     * @throws DukeException
     */
    public void handleEvent(String input) throws DukeException {
        Task event = createEvent(input);
        tasks.addTask(event);
        ui.echoTask(event, tasks);
        storage.save(tasks);
    }
    private Task createEvent(String input) throws DukeException {
        int end = input.indexOf('/');
        String description = input.substring(6, end - 1);
        String at = input.substring(end + 4);
        return new Event(description, at);
    }

    /**
     * Deletes a task and prints it, along with the updated list, to the console.
     *
     * @param index The index of the task to be deleted.
     * @throws DukeException
     */
    public void handleDelete(int index) throws DukeException {
        Task taskToDelete = tasks.delete(index);
        ui.echoDelete(taskToDelete, tasks);
        storage.save(tasks);
    }

    public void printException(DukeException e) {
        ui.dukeReply(e.getMessage());
    }




}
