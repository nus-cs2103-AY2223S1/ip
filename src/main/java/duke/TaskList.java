package duke;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.exceptions.DukeListOobException;
import duke.exceptions.DukeMissingInputException;
import duke.exceptions.DukeUnknownDateException;
import duke.exceptions.DukeWrongInputException;

public class TaskList {

    private Ui ui;
    private Storage storage;
    private ArrayList<Task> tasks;

    /**
     * Creates a TaskList object from a reference input Tasks ArrayList
     * @param ui a UI Object
     * @param storage a Storage object
     */
    public TaskList(Ui ui, Storage storage) {
        this.ui = ui;
        this.storage = storage;
        this.tasks = storage.read();
    }

    /**
     * Commands Ui class to print out the Tasks ArrayList in pretty UI
     */
    public void list() {
        //move Ui calls to Parser instead. Function is redundant.
        ui.listPrint(tasks);
    }

    /**
     * Adds a Task item to the Tasks ArrayList accounting for the Type and Item
     * @param type Type of Task
     * @param item Additional Arguments for specified Task
     * @throws DukeException if inputs are missing or dates are incorrect
     */
    public void listAdd(String type, String item) throws DukeException {
        // atodo creates an empty task if no input after command (unresolved)
        // move Ui calls to Parser instead.
        Task currTask;
        String[] args;
        switch (type) {
        case "todo":
            currTask = new Todo(item);
            tasks.add(currTask);
            ui.addTask("todo", currTask, tasks.size());
            storage.save(tasks);
            break;
        case "deadline":
            args = item.split("/by ");
            try {
                currTask = new Deadline(args[0], args[1]);
                tasks.add(currTask);
                ui.addTask("deadline", currTask, tasks.size());
                storage.save(tasks);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeMissingInputException(type);
            } catch (DateTimeParseException e) {
                throw new DukeUnknownDateException(type);
            }
            break;
        case "event":
            args = item.split("/at ");
            try {
                currTask = new Event(args[0], args[1]);
                tasks.add(currTask);
                ui.addTask("event", currTask, tasks.size());
                storage.save(tasks);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeMissingInputException(type);
            } catch (DateTimeParseException e) {
                throw new DukeUnknownDateException(type);
            }
            break;
        default:
            break;
        }
    }

    /**
     * Deletes the Task item from the Tasks ArrayList at specified index (1-indexed)
     * @param indexString String representation of 1-indexed index
     * @throws DukeException if argument is of wrong format or OOB error
     */
    public void listDelete(String indexString) throws DukeException {
        int index = 0;
        try {
            index = Integer.parseInt(indexString) - 1;
        } catch (NumberFormatException e) {
            throw new DukeWrongInputException("delete");
        }
        if (index >= tasks.size() || index < 0) {
            throw new DukeListOobException(index + 1);
        }
        Task currTask = tasks.remove(index);
        ui.deleteTask(currTask, tasks.size());
        storage.save(tasks);
    }

    /**
     * Toggles the Task item completion from Tasks ArrayList at specified index (1-indexed)
     * @param indexString String representation of 1-indexed index
     * @throws DukeException if argument is of wrong format or OOB error
     */
    public void listToggle(String indexString) throws DukeException {
        int index = 0;
        try {
            index = Integer.parseInt(indexString) - 1;
        } catch (NumberFormatException e) {
            throw new DukeWrongInputException("mark");
        }
        if (index >= tasks.size() || index < 0) {
            throw new DukeListOobException(index + 1);
        }
        Task currTask = tasks.get(index);
        currTask.completeToggle();
        ui.toggleTask(currTask);
        storage.save(tasks);
    }

    /**
     * Calls Ui to print tasks containing regex in pretty UI
     * @param regex
     */
    public void find(String regex) {
        ui.find(tasks, regex);
    }
}
