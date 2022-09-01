package duke;

import duke.exception.DukeException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Node;
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



/**
 * Main code for Duke.
 */
public class Duke {
    private final Storage storage;
    private TaskList list;
    private final Parser p;
    private boolean isExiting;

    public Duke() {
        storage = new Storage("data" + File.separator + "taskList.txt");
        try {
            list = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            list = new TaskList();
        }
        p = new Parser(list);
        isExiting = false;
    }

    public String getResponse(String input) {
        return "Duke heard: " + input;
    }

    public void run() {
        Ui.showHello();
        Scanner sc = new Scanner(System.in);
        do {
            String userInput = sc.nextLine();
            try {
                isExiting = p.parseInput(userInput, false);
            } catch (DukeException e) {
                Ui.showErrorOccurred(e);
            }
        } while (!isExiting);
        sc.close();
        exit();
    }

    public void exit() {
        storage.saveToFile(list);
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
