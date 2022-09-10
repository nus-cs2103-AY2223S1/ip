package seedu.duke;

import seedu.duke.Ui.Ui;
import seedu.duke.command.Command;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Creates or loads a todo list.
 * Accepts the following commands:
 * todo [name of task] - adds an item to the todo list
 * deadline [name of task] /by [date] - adds an item to the todo list
 * event [name of event] /on [time] - adds an item to the todo list
 * mark [item index] - marks the given item as done
 * unmark [item index] - marks the given item as undone
 * delete [item index] - removes the given item from list
 * list - prints the list
 * find [search string] - searchs the list for items matching the string and prints them
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
                storage.saveList(list, filePath);
                output += Ui.saved(list);
            }
        } catch (DukeException e) {
            return e.getMessage();
        } catch (NullPointerException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            return output;
        }
    }
}
