package duke;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
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
import java.util.Scanner;

/**
 * A Personal Assistant Chatbot that helps a person keep track of several things like tasks.
 *
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    /**
     * Gets a response from the Duke bot
     *
     * @param input
     * @return response String
     */
    public String getResponse(String input) {
        return this.parser.parse(input, this.ui, this.taskList, this.storage);
    }

    /**
     * The constructor of the Duke class.
     *
     */
    public Duke() {
        this.storage = new Storage("data/tasks.txt");
        this.taskList = new TaskList();
        this.storage.load(this.taskList);
        this.ui = new Ui();
        this.parser = new Parser();
    }

    /**
     * Main method which instantiates the Duke chatbot and runs it.
     *
     * @param args
     */
    public static void main(String[] args) {


    }
}
