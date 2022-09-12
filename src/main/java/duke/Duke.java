package duke;

import command.ByeCommand;
import command.Command;
import duke.DialogBox;
import task.DukeTask;

import java.util.ArrayList;
import java.util.Scanner;
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

/**
 * A chat bot that tracks your tasks!
 */
public class Duke {
    private Storage storage;
    private ArrayList<DukeTask> tasklist;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/User.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/Duke.jpg"));
    public static boolean isterminated = false;


    public Duke() {
        ui = new Ui();
        storage = new Storage();
        tasklist = new ArrayList<DukeTask>();
        Storage.setOnce(tasklist, "data/list.txt");
        run();
    }

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage();
        tasklist = new ArrayList<DukeTask>();
        Storage.setOnce(tasklist, filePath);
    }


    /**
     * 
     */
    protected String getResponse(String input) {
        while(!isterminated){
            try {
                return Parser.parse(input).deconstruct(tasklist, ui, storage);
            } catch (Exception e) {
                return "Sorry something went wrong: " + e;
            }
        }
        System.exit(0);
        return null;
    }


    /**
     * Run the program
     */
    public void run() {
        storage.read();
        Scanner input = new Scanner(System.in);
//        Ui.printIntro();
        boolean isRunning = true;
        while(isRunning) {
            if (input.hasNext()) {
                String str = input.nextLine();
                Command cmd = Parser.parse(str);
                if (cmd instanceof ByeCommand) {
//                    Ui.printExit();
                    isRunning = false;
                    input.close();
                    break;
                }
                try {
                    cmd.deconstruct(tasklist, ui, storage);
                } catch (Exception e) {
                    System.out.println(e);
                }
                
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/list.txt").run();
    }
}
