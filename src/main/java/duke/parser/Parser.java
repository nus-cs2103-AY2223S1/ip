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

import java.util.ArrayList;

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
            case "find": {
                return new FindCommand(input, this);
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

    public String parseFindItems(String[] input) throws DukeException {
        StringBuilder parsedInput = new StringBuilder();
        for (int i = 1; i < input.length; i++) {
            parsedInput.append(input[i]);
            if (i + 1 != input.length) {
                parsedInput.append(" ");
            }
        }
        ArrayList<DukeTask> foundItems = itemList.find(parsedInput.toString());
        if (foundItems.size() == 0) {
            throw new DukeException("No items were found.");
        }
        StringBuilder parsedOutput = new StringBuilder("Here are the matching tasks in your list: \n");
        for (DukeTask t : foundItems) {
            parsedOutput.append("\n  ").append(t.toString());
        }
        return parsedOutput.toString();
    }
}
