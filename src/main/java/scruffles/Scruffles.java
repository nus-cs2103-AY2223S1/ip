package scruffles;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The main class of the program, Scruffles, which is an app that helps track your tasks. With this app you can keep
 * track of deadlines, events, as well as mark them as done or even delete them when you do not need toi track them
 * anymore
 *
 * @author Shamus Tan
 */
public class Scruffles extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    enum Type {
        TODO,
        DEADLINE,
        EVENT,
        DOWITHINPERIOD
    }

    public Scruffles() {}

    Parser parser = new Parser();

    @Override
    public void start(Stage stage) {

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Gets the response from Scruffles when entering input
     *
     * @param input the input given to the program
     * @return the output response
     */
    String getResponse(String input) {
        return parser.parse(input);
    }

}