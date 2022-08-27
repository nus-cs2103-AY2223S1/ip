package duke.parser;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.command.*;
import duke.task.Deadline;
import duke.task.DukeTask;
import duke.task.Event;
import duke.task.ToDo;

public class Parser {
    private final TaskList itemList;
    private final Storage storage;
    private final Ui ui;

    public Parser(TaskList itemList, Storage storage, Ui ui) {
        this.itemList = itemList;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Processes chat inputs using a switch statement, throwing a duke.exception.DukeException
     * on incorrect inputs.
     *
     * @param inputString String given to Apollo
     * @throws DukeException Indicates incorrect inputs
     */
    public Command parseUserInput(String inputString) throws DukeException {
        String[] input = inputString.split(" ");
        try {
            switch (input[0]) {
            case "bye": {
                return new ExitCommand();
            }
            case "list": {
                return new ListItemsCommand();
            }
            case "mark": {
                return new MarkCommand(input[1]);
            }
            case "unmark": {
                return new UnmarkCommand(input[1]);
            }
            case "delete": {
                return new DeleteCommand(input[1]);
            }
            default: {
                return new AddItemCommand(input, this);
            }
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Missing command description.");
        } catch (NumberFormatException e) {
            throw new DukeException("Please recheck your number input, " +
                    "including trailing spaces.");
        }
    }

    /**
     * Executes item addition commands, throwing a duke.exception.DukeException on incorrect
     * inputs.
     *
     * @param newItem Parsed item addition command
     * @return String signifying result of adding newItem to items
     * @throws DukeException Indicates incorrect inputs
     */
    public String parseAddItem(String[] newItem) throws DukeException {
        String s = String.join(" ", newItem);
        if (newItem[0].equals("")) {
            return "";
        }
        DukeTask newTask;
        switch (newItem[0]) {
        case "todo": {
            try {
                // Obtain description of task
                newTask = new ToDo(s.split("todo ")[1]);
                itemList.add(newTask);
                return "Item added: " + newTask + itemList.listCount();
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("A todo task's description cannot be empty.");
            }
        }
        case "deadline": {
            String[] deadlineSplit = s.split(" /by ");
            try {
                // Obtain description of task
                newTask = new Deadline(deadlineSplit[0].split("deadline ")[1],
                        deadlineSplit[1]);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("An incorrect deadline description was entered.");
            }
            itemList.add(newTask);
            return "Item added: " + newTask + itemList.listCount();
        }
        case "event": {
            String[] deadlineSplit = s.split(" /at ");
            try {
                // Obtain description of task
                newTask = new Event(deadlineSplit[0].split("event ")[1],
                        deadlineSplit[1]);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("An incorrect event description was entered.");
            }
            itemList.add(newTask);
            return "Item added: " + newTask + itemList.listCount();
        }
        default:
            throw new DukeException("\"" + newItem[0] + "\"" +
                    " is not a recognised command.");
        }
    }
}
