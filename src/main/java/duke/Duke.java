package duke;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Is an interactive chatbot that keep tracks of tasks inputted by the user.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Class constructor.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("src/main/data/duke.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (IOException e) {
            System.out.println("I am unable to create a new file.");
        }
    }

    /**
     * Returns a string response based on user's input.
     *
     * @param input String input entered by user.
     * @param duke Duke object.
     * @return String response associated with the input.
     */
    public String getResponse(String input, Duke duke) {
        Parser parser = new Parser(duke);
        return parser.initialise(input);
    }


    //Solution adapted from https://github.com/nus-cs2103-AY2223S1/ip/commit/1425eadf831d33ce6909694a2c3d5d58670aacd9

    /**
     * Returns the list of all current tasks.
     */
    public String showList() {
        return ui.printList(tasks);
    }


    /**
     * Handles the addition of a new Todo task inputted by the user.
     *
     * @param description String description of Todo task.
     * @return String response corresponding to user's todo input.
     * @throws DukeException
     */
    public String setToDo(String description) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException("The description of todo cannot be empty");
        }
        Task toDo = new ToDo(description);
        tasks.addTask(toDo);
        int size = tasks.getSize();
        storage.save(tasks);
        return ui.printToDo(toDo, size);
    }

    /**
     * Handles the addition of a new Deadline task inputted by the user.
     *
     * @param description String description of Deadline task.
     * @param by String representation of date and time of the Deadline task.
     * @return String response corresponding to user's deadline input
     */
    public String setDeadLine(String description, String by) {
        Task deadLine = new Deadline(description, by);
        tasks.addTask(deadLine);
        int size = tasks.getSize();
        storage.save(tasks);
        return ui.printDeadLine(deadLine, size);
    }

    /**
     * Handles the addition of a new Event task inputted by the user.
     *
     * @param description String description of Event task.
     * @param at String representation of date and time of the Event task.
     * @return String response corresponding to user's event input.
     */
    public String setEvent(String description, String at) {
        Task event = new Event(description, at);
        tasks.addTask(event);
        int size = tasks.getSize();
        storage.save(tasks);
        return ui.printEvent(event, size);
    }


    /**
     * Calls TaskList and Ui objects to handle the marking of a task.
     *
     * @param index Index of the task to be marked.
     * @return String response corresponding to user's mark input.
     * @throws DukeException
     */
    public String mark(int index) throws DukeException {
        tasks.markTask(index);
        Task markedTask = tasks.getTask(index);
        storage.save(tasks);
        return ui.printMark(markedTask);
    }

    /**
     * Calls TaskList and Ui objects to handle the unmarking of a task.
     *
     * @param index Index of the task to be unmarked.
     * @return String response corresponding to user's unmark input.
     * @throws DukeException
     */
    public String unmark(int index) throws DukeException {
        tasks.unMarkTask(index);
        Task unmarkedTask = tasks.getTask(index);
        storage.save(tasks);
        return ui.printUnMark(unmarkedTask);
    }


    /**
     * Calls TaskList and Ui objects to handle the deletion of a task.
     *
     * @param index Index of the task to be deleted.
     * @return String response corresponding to the user's delete input.
     * @throws DukeException
     */
    public String delete(int index) throws  DukeException {
        Task taskToDelete = tasks.deleteTask(index);
        int size = tasks.getSize();
        storage.save(tasks);
        return ui.printDelete(taskToDelete, size);
    }

    /**
     * Filters the task list based on the specified keyword and display the list of filtered task(s).
     *
     * @param keyword Keyword to be used for filtering the list.
     * @return String response corresponding to the user's find input.
     */
    public String find(String keyword) {
        ArrayList<Task> taskList = tasks.getAllTasks();
        ArrayList<Task> filteredTasks = new ArrayList<>();
        for (Task task : taskList) {
            String description = task.description;
            if (description.contains(keyword)) {
                filteredTasks.add(task);
            }
        }
       return ui.printFind(filteredTasks);
    }

    /**
     * Returns goodbye message to the user.
     *
     * @return String of goodbye message.
     */
    public String sayBye() {
        return ui.goodBye();
    }

    /**
     * Returns a list of all available commands usable in Duke to the user.
     *
     * @return String representation of help list.
     */
    public String sendHelp() {
        return ui.printHelp();
    }
}
