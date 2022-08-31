package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents the Duke bot.
 */
public class Duke {
    private static Ui ui;
    private static Storage storage;
    private static TaskList tasklist;

    /**public static void main(String[] args) throws DukeException, IOException {

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
        storage.save(tasklist);
        ui.printGoodbyeMessage();
    }*/

    public Duke() {
        ui = new Ui();
        storage = new Storage("out/duke.txt");
        try {
            tasklist = storage.load();
        } catch (DukeException de) {
            //ui.printErrorMsg(de.toString());
            tasklist = new TaskList();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getResponse(String input) throws DukeException {
        Parser parser = new Parser(ui, tasklist, storage);
        try {
            return parser.parse(input);
        } catch (DukeException de) {
            return ui.printErrorMsg(de.toString());
        }
    }

}
