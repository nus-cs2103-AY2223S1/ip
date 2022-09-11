package duke;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

import duke.exceptions.DukeEmptyDescriptionException;
import duke.exceptions.DukeException;
import duke.exceptions.DukeMissingInputException;
import duke.exceptions.DukeUnknownDateException;
import duke.exceptions.DukeUnknownInputException;

/** Represents Parser object. Handles user input */
public class Parser {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    private static final String FILENAME = "SaveData.txt";

    private static final ArrayList<String> ADDED_ARG_COMMANDS =
            new ArrayList<>(Arrays.asList(new String[] {"todo", "deadline", "event", "delete", "mark", "find"}));

    /**
     * Creates an instance of a Parser object
     * @param ui
     */
    public Parser(Ui ui) {
        this.ui = ui;
        this.storage = new Storage(FILENAME);
        this.tasks = new TaskList(storage.read());
    }

    /**
     * Accepts user input and generates duke output accordingly
     * @param input
     * @return String output
     * @throws DukeException when runtime errors occur
     */
    public String handleInput(String input) throws DukeException {
        String[] args = input.split(" ", 2);
        Task currTask;

        String command = args[0];
        String addedArg = "";

        if (ADDED_ARG_COMMANDS.contains(command)) {
            try {
                addedArg = args[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeMissingInputException(command);
            }
        }

        switch (command) {
        case "list":
            return ui.listPrint(tasks.getTasks());
        case "todo":
            // Fallthrough
        case "deadline":
            // Fallthrough
        case "event":
            currTask = this.makeTask(command, addedArg);
            tasks.addTask(currTask);
            storage.save(tasks.getTasks());
            return ui.addTask(command, currTask, tasks.getSize());
        case "delete":
            currTask = tasks.deleteTask(args[1]);
            storage.save(tasks.getTasks());
            return ui.deleteTask(currTask, tasks.getSize());
        case "mark":
            // mark is implemented as a toggle. note this.
            currTask = tasks.toggleTask(args[1]);
            storage.save(tasks.getTasks());
            return ui.toggleTask(currTask);
        case "find":
            return ui.find(tasks.getTasks(), args[1]);
        case "bye":
            return ui.exit();
        case "remind":
            return ui.remind(tasks.getTasks());
        default:
            throw new DukeUnknownInputException(command);
        }
    }

    private Task makeTask(String type, String item) throws DukeException {
        String[] args;
        String description = "";
        String addedArg = "";
        Task currTask;

        if (item.isBlank()) {
            throw new DukeMissingInputException(type);
        }

        if (!type.equals("todo")) {
            args = (type.equals("deadline")) ? item.split("/by ") : item.split("/at ");
            try {
                description = args[0].trim();
                if (description.isBlank()) {
                    throw new DukeEmptyDescriptionException(type);
                }
                addedArg = args[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeMissingInputException(type);
            }
        }

        switch (type) {
        case "todo":
            currTask = new Todo(item);
            break;
        case "deadline":
            try {
                currTask = new Deadline(description, addedArg);
            } catch (DateTimeParseException e) {
                throw new DukeUnknownDateException(type);
            }
            break;
        case "event":
            try {
                currTask = new Event(description, addedArg);
            } catch (DateTimeParseException e) {
                throw new DukeUnknownDateException(type);
            }
            break;
        default:
            throw new DukeUnknownInputException(type);
        }

        return currTask;
    }
}
