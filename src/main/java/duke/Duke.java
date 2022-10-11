package duke;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Is an interactive chatbot that keep tracks of to-do inputted by the user.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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
     * Calls TaskList object to handle a ToDo task inputted by the user.
     * @param description String description of the task.
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
     * Calls TaskList object to handle a DeadLine task inputted by the user.
     * @param description String description of the task.
     * @param by String representation of the deadline of the task.
     */
    public String setDeadLine(String description, String by) {
        Task deadLine = new Deadline(description, by);
        tasks.addTask(deadLine);
        int size = tasks.getSize();
        storage.save(tasks);
        return ui.printDeadLine(deadLine, size);
    }

    /**
     * Calls TaskList object to handle a Event task inputted by the user.
     * @param description String description of the task.
     * @param at String representation of when the task will happen.
     */
    public String setEvent(String description, String at) {
        Task event = new Event(description, at);
        tasks.addTask(event);
        int size = tasks.getSize();
        storage.save(tasks);
        return ui.printEvent(event, size);
    }

    /**
     * Calls TaskList object to handle the marking of a task.
     * @param index Index of the task to be marked.
     * @throws DukeException
     */
    public String mark(int index) throws DukeException {
        tasks.markTask(index);
        Task markedTask = tasks.getTask(index);
        storage.save(tasks);
        return ui.printMark(markedTask);
    }

    /**
     * Calls TaskList object to handle the unmarking of a task.
     * @param index Index of the task to be unmarked.
     * @throws DukeException
     */
    public String unmark(int index) throws DukeException {
        tasks.unMarkTask(index);
        Task unmarkedTask = tasks.getTask(index);
        storage.save(tasks);
        return ui.printUnMark(unmarkedTask);
    }

    /**
     * Calls TaskList object to handle the deletion of a task.
     * @param index Index of the task to be deleted.
     * @throws DukeException
     */
    public String delete(int index) throws  DukeException {
        Task taskToDelete = tasks.deleteTask(index);
        int size = tasks.getSize();
        storage.save(tasks);
        return ui.printDelete(taskToDelete, size);
    }

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

    public String sayBye() {
        return ui.goodBye();
    }

    public String sendHelp() {
        return ui.printHelp();
    }
}
