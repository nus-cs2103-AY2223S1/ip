package duke;

import java.io.IOException;
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
            ui.printErrorMsg(de.toString());
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
