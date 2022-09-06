package seedu.duke;

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
 * bye - saves the list and closes the program
 */
public class Duke {
    private static String logo  = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static TaskList list;
    private static Path filePath;
    private static Storage storage = new Storage();
    private static Parser parser;

    public static void main(String[] args) throws DukeException, IOException {
        filePath = storage.createSave();
        list = storage.loadList(filePath);

        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        boolean isOpen = true;

        while (isOpen) {
            try {
                Command command = Parser.parse(text);
                command.execute(list);
                if (command.isExit()) {
                    storage.saveList(list, filePath);
                    scanner.close();
                    isOpen = false;
                }
            } catch (DukeException e) {
                System.out.println(e);
            } catch (NullPointerException e) {
                System.out.println(e);
            } finally {
                if (isOpen) {
                    text = scanner.nextLine();
                }
            }
        }
    }
}
