package doke;

import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * A class to represent the Doke program
 */
public class Doke {

    protected static final String DOKE_FILE_PATH = "src/main/java/data/doke.txt";

    //anything related to GUI is taken from the website: https://se-education.org/guides/tutorials/javaFx.html
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image doke = new Image(this.getClass().getResourceAsStream("/images/DaDoke.png"));

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    protected String getResponse(String input) {
        return "Doke heard: " + input;
    }


    /**
     * A main method which runs the Doke program.
     *
     * @param args
     */
    public static void main(String[] args) {

        Ui ui = new Ui();
        Scanner sc = new Scanner(System.in);
        Storage storage = new Storage();
        Parser parser = new Parser();
        TaskList taskList;

        ui.printOut("full path: " + Storage.DOKE_FILE.getAbsolutePath());

        ui.printOut("hello");

        taskList = new TaskList(ui, storage);
        ui.printOut("The following is your stored task: \n");
        taskList.listOut(ui);

        String str = sc.nextLine();
        while (true) {
            if (!parser.processString(taskList, storage, str, ui)) {
                break;
            }
            str = sc.nextLine();
        }

        ui.printOut("bye");

    }
}
