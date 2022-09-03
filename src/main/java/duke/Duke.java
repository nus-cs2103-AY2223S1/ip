package duke;

import duke.ui.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Represents Duke - an interactive virtual assistant to organize tasks.
 */
public class Duke extends Application {

    private final Ui ui;

    /**
     * Constructs a Duke object and creates a new Ui object for user interaction
     */
    public Duke() {
        this.ui = new Ui(System.in, System.out);
    }

    /**
     * Introduces Duke and initiates interactive conversation with user
     */
    public void interact() {
        this.ui.introduceDuke();
        this.ui.readAndRespond();
    }

    @Override
    public void start(Stage stage){
        Label helloWorld = new Label("Hello World!");
        Scene scene = new Scene(helloWorld);
        stage.setScene(scene);
        stage.show();


    }

    /**
     * Creates a new Duke object and begins interaction 
     */
    public static void main(String[] args) {
       Duke AemonT = new Duke();
       AemonT.interact();
    }

}