package dukechatbot.duke;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainWindow extends AnchorPane {
    @FXML
    private VBox dialogContainer;
    @FXML
    private ScrollPane sp;
    @FXML
    private TextField userInput;
    @FXML
    private Button enterButton;

    private Image userImg = new Image(getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImg = new Image(getClass().getResourceAsStream("/images/DaDuke.png"));
    private Duke duke;

    public void setDuke(Duke dk) {
        this.duke = dk;
    }

    @FXML
    public void initialize() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog("Hello from\n" + logo
                + "Hello I'm Duke, what can I do for you?", dukeImg));
    }

    @FXML
    public void handleUserInput() throws IOException {
        String input = userInput.getText();
        String dukeResponse = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImg), DialogBox.getDukeDialog(dukeResponse, dukeImg)
        );
        userInput.clear();
    }
}
