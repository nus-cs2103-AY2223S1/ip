package duke;

import javafx.application.Application;
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

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
//testing
public class Duke  {
    private static final List<Task> ListofMessages  = new ArrayList<>();

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;



    public Duke () {

    }


    /**
     * Runs Parser and the entire code
     * @param args
     * @throws DukeException
     * @throws IOException
     * @throws ParseException
     */
    public static void main(String[] args) throws DukeException, IOException, ParseException {
        Parser.Parser(ListofMessages);
    }


    public String getResponse(String input) {
        return "Duke heard: " + input;
    }




}
