package duke;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.commands.Command;
import duke.items.Deadline;
import duke.items.Event;
import duke.items.Item;
import duke.items.ToDo;

/**
 * Object that takes in a Command Object then creates and stores the task.
 */
public class TaskList {
    private final ArrayList<Item> storedItems;
    private final Storage saveManager = new Storage();
    private final UI ui;

    /**
     * Creates a Tasklist Object.
     * @param ui UI Object to display messages to user.
     */
    public TaskList(UI ui) {
        this.storedItems = this.saveManager.loadItems();
        this.ui = ui;
    }

    /**
     * Saves the items in the list of tasks to file upon closing of the app.
     */
    public void destructor() {
        this.saveManager.saveItems(this.storedItems);
    }

    /**
     * Creates the tasks from a Command Object.
     * @param command Command to turn into a task.
     * @return A String for the UI object to print.
     */
    public String executeTask(Command command) {
        String[] arguments = command.getArguments();
        switch (command.getCommand()) {
        case TODO:
            return addToDo(command.getArguments()[0]);
        case DEADLINE:
            return addDeadline(arguments[0], arguments[1]);
        case EVENT:
            return addEvent(arguments[0], arguments[1]);
        case LIST:
            return getList();
        case MARK:
            return markItem(arguments[0]);
        case UNMARK:
            return unMarkItem(arguments[0]);
        case DELETE:
            return deleteItem(arguments[0]);
        case FIND:
            return findString(arguments[0]);
        default:
            return null;
        }
    }


    private String addItem(Item item) {
        this.storedItems.add(item);
        return "    Alright! I've added it to our list:\n      " + item.toString()
                + "\n    Now we have " + this.storedItems.size() + " tasks in our list Dattebayo!";
    }

    private String addToDo(String item) {
        ToDo todo = new ToDo(item);
        return this.addItem(todo);
    }

    private String addDeadline(String item, String due) {
        try {
            Deadline deadline = new Deadline(item, due);
            return this.addItem(deadline);
        } catch (DateTimeParseException e) {
            ui.printErrorMessage("Error Parsing Date Time Info, duke.items.Item not added, "
                    + "please use this format /by YYYY-MM-DD HH:MM (omit time if not necessary)");
            return null;
        }
    }

    private String addEvent(String item, String at) {
        try {
            Event event = new Event(item, at);
            return this.addItem(event);
        } catch (DateTimeParseException e) {
            ui.printErrorMessage("Error Parsing Date Time Info, duke.items.Item not added, "
                    + "please use this format /at YYYY-MM-DD HH:MM (omit time if necessary)");
            return null;
        }
    }

    private String getList() {
        if (this.storedItems.isEmpty()) {
            return "    The list is currently empty Dattebayo!";
        }
        StringBuilder list = new StringBuilder("    Here's the list you asked for Dattebayo:");
        for (int count = 0; count < this.storedItems.size(); count++) {
            list.append("\n").append("    ").append(count + 1).append(".").append(storedItems.get(count).toString());
        }
        return list.toString();
    }

    private String markItem(String strIndex) {
        int index = this.string2Int(strIndex) - 1;
        if (index >= this.storedItems.size() || index < 0) {
            return "Whoops it appears you entered an invalid index, there are " + this.storedItems.size()
                    + " items in the list Dattebayo!";
        }
        this.storedItems.get(index).setDone();
        return "    Alright! I've marked this task as done Dattebayo:\n  " + this.storedItems.get(index).toString();
    }

    private String unMarkItem(String strIndex) {
        int index = this.string2Int(strIndex) - 1;
        if (index >= this.storedItems.size() || index < 0) {
            ui.printErrorMessage("Whoops it appears you entered an invalid index, there are " + this.storedItems.size()
                    + " items in the list Dattebayo!");
            return null;
        }
        this.storedItems.get(index).setUnDone();
        return "    Alright! I've marked this task as not done yet Dattebayo: \n  "
                + this.storedItems.get(index).toString();
    }


    private String deleteItem(String strIndex) {
        int index = this.string2Int(strIndex) - 1;
        if (index >= this.storedItems.size() || index < 0) {
            return "Whoops it appears you entered an invalid index, there are " + this.storedItems.size()
                    + " items in the list Dattebayo!";
        }
        Item item = this.storedItems.get(index);
        this.storedItems.remove(index);
        return "    Alright! I've removed this task Dattebayo: \n  " + item.toString()
                + "\n    Now we have " + this.storedItems.size() + " tasks in our list Dattebayo!";
    }

    private int string2Int(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            ui.printErrorMessage("Whoops! it seems you your index is not an integer Dattebayo!"
                    + "\n'delete <Index>'");
            return -1;
        }
    }

    private String findString(String searchParam) {
        if (this.storedItems.isEmpty()) {
            return "    The list is currently empty Dattebayo!";
        }
        StringBuilder strBuilder =
                new StringBuilder("    Here's the search results for '" + searchParam + "' Dattebayo:");
        int count = 1;
        boolean min1ItemFound = false;
        for (Item item : this.storedItems) {
            String name = item.getName();
            if (name.contains(searchParam)) {
                strBuilder.append("\n").append("    ").append(count).append(".").append(item);
                min1ItemFound = true;
            }
            count++;
        }
        return min1ItemFound ? strBuilder.toString()
                : "    Sorry there were no results for '" + searchParam + "' Dattebayo:";
    }

}
