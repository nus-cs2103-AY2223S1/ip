package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.DukeTask;
import duke.task.Event;
import duke.task.ToDo;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates adding items to a TaskList object.
 *
 * @author Kartikeya
 */
public class AddItemCommand implements Command {
    private final String[] input;

    /**
     * Constructor for an AddItemCommand object.
     *
     * @param input split strings input by user
     */
    public AddItemCommand(String[] input) {
        this.input = input;
    }

    /**
     * {@inheritDoc}
     * Adds given input item to itemList, and shows the resulting message to the user.
     */
    @Override
    public void execute(TaskList itemList, Ui ui, Storage storage) throws DukeException {
        ui.showToUser(executeAddItem(input, itemList));
    }

    /**
     * Executes the addition of newItem to a given TaskList.
     *
     * @param newItem parsed item addition command
     * @return the string signifying result of adding newItem to items
     * @throws DukeException if the input is erroneous
     */
    private String executeAddItem(String[] newItem, TaskList itemList) throws DukeException {
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
            throw new DukeException("\"" + newItem[0] + "\""
                    + " is not a recognised command.");
        }
    }
}
