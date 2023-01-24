package seedu.duke;

import seedu.duke.ui.Launcher;
import seedu.duke.ui.Ui;
import seedu.duke.command.Command;
import seedu.duke.exception.DukeException;
import seedu.duke.list.TaskList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Creates or loads a todo list.
 * Accepts the following commands:
 * todo [name of task] - adds an item to the todo list
 * deadline [name of task] /by [date] - adds an item to the todo list
 * event [name of event] /on [time] - adds an item to the todo list
 * mark [item index] - marks the given item as done
 * unmark [item index] - marks the given item as undone
 * delete [item index] - removes the given item from list
 * edit [item index] [new description] - changes the description of the given item
 * list - prints the list
 * find [search string] - searchs the list for items matching the string and prints them
 * coffee - flavour command for fun
 * bye - saves the list and closes the program
 */
public class Duke {
    private static TaskList list;
    private static Path filePath;
    private static Storage storage = new Storage();

    public Duke() throws IOException {
        filePath = storage.createSave();
        list = storage.loadList(filePath);
    }

    public String start() {
        return Ui.greet();
    }

    public String getResponse(String input) {
        String output = " ";
        try {
            Command command = Parser.parse(input);
            output = command.execute(list);
            if (command.isExit()) {
                assert Files.exists(filePath) : "filePath does not exist";
                storage.saveList(list, filePath);
                output += Ui.saved(list);
            }
        } catch (DukeException e) {
            output = e.getMessage();
        } catch (NullPointerException e) {
            output = e.getMessage();
        } catch (IOException e) {
            output = e.getMessage();
        } finally {
            return output;
        }
    }

    public static void main(String[] args) {
        Launcher.main(args);
    }

}
