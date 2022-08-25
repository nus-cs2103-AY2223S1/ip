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

public class Duke {
    private static Ui ui;
    private static Storage storage;
    private static TaskList tasklist;

    public static void main(String[] args) throws DukeException, IOException {

        ui = new Ui();
        ui.printWelcomeMessage();
        //storage = new Storage("src/main/java/duke/duke.txt");
        storage = new Storage("out/duke.txt");

        try {
            tasklist = storage.load();
            toDo();
            //tasklist = storage.load();
        } catch (DukeException de) {
            //System.out.println(de);
            ui.printLoadingError();
            tasklist = new TaskList();
        }

        /**tasklist = new TaskList();
        toDo();*/
    }

    /**public Duke(String filePath) throws DukeException {
        ui = new Ui();
        ui.printWelcomeMessage();
        storage = new Storage(filePath);
        try {
            tasklist = storage.load();
        } catch (FileNotFoundException de) {
            ui.printLoadingError();
            tasklist = new TaskList();
        }
    }*/

    public static void toDo() throws DukeException {
        //storage = new Storage(filePath);
        Parser parser = new Parser(ui, tasklist, storage);

        Scanner sc = new Scanner(System.in);
        String str2 = sc.nextLine();

        while (!str2.equals("bye")) {
            parser.parse(str2);
            if (sc.hasNextLine()) { str2 = sc.nextLine();}
        }
        ui.printGoodbyeMessage();
        //storage.save(tasklist);
    }

}
