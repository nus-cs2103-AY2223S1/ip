package duke;

import duke.exceptions.DukeException;
import duke.exceptions.DukeMissingInputException;
import duke.exceptions.DukeUnknownDateException;
import duke.exceptions.DukeUnknownInputException;

import java.time.format.DateTimeParseException;

public class Parser {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;


    /**
     * Creates an instance of a Parser object
     * @param ui
     */
    public Parser(Ui ui) {
        this.ui = ui;
        this.storage = new Storage();
        this.tasks = new TaskList(storage.read());
    }

    /**
     * Accepts user input and generates duke output accordingly
     * @param input
     * @return boolean end to determine execution finish
     * @throws DukeException when runtime errors occur
     */
    public String handler(String input) throws DukeException {
        String[] args = input.split(" ", 2);
        //boolean isEnded = false;
        Task currTask;

        switch (args[0]) {
        case "list":
            return ui.listPrint(tasks.getTasks());
        case "todo":
            // Fallthrough
        case "deadline":
            // Fallthrough
        case "event":
            try {
                currTask = this.makeTask(args[0], args[1]);
                tasks.listAdd(currTask);
                storage.save(tasks.getTasks());
                return ui.addTask(args[0], currTask, tasks.getSize());
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeMissingInputException(args[0]);
            }
        case "delete":
            try {
                currTask = tasks.listDelete(args[1]);
                storage.save(tasks.getTasks());
                return ui.deleteTask(currTask, tasks.getSize());
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeMissingInputException(args[0]);
            }
        case "mark":
            // mark is implemented as a toggle. note this.
            try {
                currTask = tasks.listToggle(args[1]);
                storage.save(tasks.getTasks());
                return ui.toggleTask(currTask);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeMissingInputException(args[0]);
            }
        case "find":
            try {
                return ui.find(tasks.getTasks(), args[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeMissingInputException(args[0]);
            }
        case "bye":
            //isEnded = true;
            return ui.exit();
        default:
            throw new DukeUnknownInputException(args[0]);
        }
        //return isEnded;
    }

    private Task makeTask(String type, String item) throws DukeException {
        String[] args;
        Task currTask;

        switch (type) {
        case "todo":
            currTask = new Todo(item);
            break;
        case "deadline":
            args = item.split("/by ");
            try {
                currTask = new Deadline(args[0], args[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeMissingInputException(type);
            } catch (DateTimeParseException e) {
                throw new DukeUnknownDateException(type);
            }
            break;
        default:
            args = item.split("/at ");
            try {
                currTask = new Event(args[0], args[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeMissingInputException(type);
            } catch (DateTimeParseException e) {
                throw new DukeUnknownDateException(type);
            }
            break;
        }

        return currTask;
    }
}
