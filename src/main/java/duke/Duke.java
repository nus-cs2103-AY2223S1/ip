package duke;

import duke.task.Task;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A chatbot that helps to keep track of tasks.
 */
public class Duke {

    private Storage storage;
    private Ui ui;
    private TaskList listOfTasks;

    public Duke() {
        this.storage = new Storage("./data/duke.txt");
        this.ui = new Ui();
        try {
            listOfTasks = new TaskList(storage.load());
        } catch (FileNotFoundException | DukeException e) {
            System.out.println("Please create a new folder 'data' in the 'iP' folder");
            listOfTasks = new TaskList(new ArrayList<Task>());
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    protected String getResponse(String input) {
        Parser parser = new Parser(listOfTasks, ui, storage);
        if (input.equals("bye")) {
            return ui.endingMsg();
        } else {
            return parser.checkAndExecuteCommand(input);
        }
    }
}
