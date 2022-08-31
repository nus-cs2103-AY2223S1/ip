package duke;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Duke is an interactive chat-bot that can record specified tasks
 */
public class Duke {

    private Ui ui;
    private Parser parser;
    private Storage storage;
    private TaskList tasks;

    public static String GREETING = "Hello! I'm Ee Suan!\nWhat can I do for you?";

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

    public String getResponse(String input) {
        if (parser == null) {
            parser = new Parser(this);
        }
        return parser.parse(input);
    }

    /**
     * Generates message of current list of tasks.
     *
     * @return String containing list of tasks.
     */
    public String handleList() {
        return ui.printList(tasks);
    }

    /**
     * Marks a task and prints it to console.
     *
     * @param index The index of the task to be marked.
     * @return String reply from Duke.
     * @throws DukeException
     */
    public String handleMark(int index) throws DukeException {
        tasks.mark(index);
        storage.save(tasks);
        return ui.dukeReply("Nice! I've marked this task as done: \n  " + tasks.get(index));
    }

    /**
     * Unmarks a task and prints it to console.
     *
     * @param index The index of the task to be unmarked.
     * @return String reply from Duke.
     * @throws DukeException
     */
    public String handleUnmark(int index) throws DukeException {
        tasks.unmark(index);
        storage.save(tasks);
        return ui.dukeReply("OK, I've marked this task as not done yet: \n  " + tasks.get(index));
    }

    /**
     * Dispatches instructions for processing a ToDo task.
     *
     * @param desc String description of the task.
     * @return String reply of added task and updated list from Duke.
     * @throws DukeException
     */
    public String handleToDo(String desc) throws DukeException {
        Task todo = new ToDo(desc);
        tasks.addTask(todo);
        storage.save(tasks);
        return ui.echoTask(todo, tasks);
    }

    /**
     * Dispatches instructions for processing a Deadline task.
     *
     * @param desc String description of the task.
     * @param by Date of the deadline.
     * @return String reply of added task and updated list.
     * @throws DukeException
     */
    public String handleDeadline(String desc, String by) throws DukeException {
        Task deadline = new Deadline(desc, by);
        tasks.addTask(deadline);
        storage.save(tasks);
        return ui.echoTask(deadline, tasks);

    }

    /**
     * Dispatches instructions for processing an event task.
     *
     * @param desc String description of the task.
     * @param at Date of the event.
     * @return String reply of added task and updated list.
     * @throws DukeException
     */
    public String handleEvent(String desc, String at) throws DukeException {
        Task event = new Event(desc, at);
        tasks.addTask(event);
        storage.save(tasks);
        return ui.echoTask(event, tasks);
    }

    /**
     * Deletes a task and returns it, along with the updated list, as a String.
     *
     * @param index The index of the task to be deleted.
     * @return String of deleted task and updated list.
     * @throws DukeException
     */
    public String handleDelete(int index) throws DukeException {
        Task taskToDelete = tasks.delete(index);
        storage.save(tasks);
        return ui.echoDelete(taskToDelete, tasks);
    }

    public String find(String keyword) {
        ArrayList<Task> newList = tasks.filterToArrayList(keyword);
        storage.save(tasks);
        return ui.printFind(newList);
    }

    public String handleExit() {
        return ui.exit();
    }
}





