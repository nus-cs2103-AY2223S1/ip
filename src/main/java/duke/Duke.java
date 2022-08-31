package duke;


//import javafx.scene.control.Label;
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.ScrollPane;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//import javafx.scene.layout.Region;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;

public class Duke {

    private Storage storage;
    private Ui ui;
    private Parser parser;
    private boolean isProcess;
    private static final String FILEPATH = "data/duke.txt";

    /**
     * Constructs an instance of Duke
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(FILEPATH);
        parser = new Parser(this.ui, this.storage);
    }

    /**
     * Runs the instance of Duke
     */
    public void run() {
        System.out.println(this.ui.intro());
        this.isProcess = true;
        while (this.isProcess) {
            String cmd = this.ui.getCommand();
            String tempRes = this.parser.process(cmd);
            System.out.println(tempRes);
            if (tempRes == this.ui.end()){
                this.isProcess = false;
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return this.parser.process(input);
    }
}
