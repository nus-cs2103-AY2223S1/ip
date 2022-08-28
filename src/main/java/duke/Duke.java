package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * Represents the Duke bot.
 */
public class Duke {
    private static Ui ui;
    private static Storage storage;
    private static TaskList tasklist;

    public static void main(String[] args) throws DukeException, IOException {

        ui = new Ui();
        ui.printWelcomeMessage();
        storage = new Storage("out/duke.txt");

        try {
            tasklist = storage.load();
            toDo();
        } catch (DukeException de) {
            ui.printLoadingError();
            tasklist = new TaskList();
        }
    }

    public static void toDo() throws DukeException {
        Parser parser = new Parser(ui, tasklist, storage);

        Scanner sc = new Scanner(System.in);
        String str2 = sc.nextLine();

        while (!str2.equals("bye")) {
            parser.parse(str2);
            if (sc.hasNextLine()) { str2 = sc.nextLine();}
        }
        ui.printGoodbyeMessage();
    }

}
