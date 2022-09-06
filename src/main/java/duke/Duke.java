package duke;

import duke.command.*;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

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
        Ui.greet();
        filePath = storage.createSave();
        list = storage.loadList(filePath);
        System.out.println("This is your current list:\n" + list.toString());

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
