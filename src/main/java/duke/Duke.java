package duke;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;


public class Duke extends Application{

    //duke.duke.Parser
    Parser parser = new Parser();

    public void start(Stage stage) {
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        return parser.parse(input);
    }

}


