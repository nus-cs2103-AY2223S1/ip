package justin;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import justin.command.Command;
import justin.command.ExitCommand;

public class MainWindow extends AnchorPane {
    private static final String JUSTIN_DESCRIPTION = "Justin, the creator of this bot";
    private static final String YOUR_DESCRIPTION = "You, a nerd";
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private JustinBot justinBot;

    private Image justin = new Image(this.getClass().getResourceAsStream("/images/gigachad.jpeg"));
    private Image user = new Image(this.getClass().getResourceAsStream("/images/nerd.jpeg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setJustinBot(JustinBot j) {
        justinBot = j;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        try {
            Command c = Parser.parse(input);
            c.execute(justinBot.getTasks(), justinBot.getUi(), justinBot.getStorage(), this);
            String response = c.getMessage(justinBot.getTasks(), justinBot.getUi());
            DialogBox userBox = DialogBox.getUserDialog(input, user, YOUR_DESCRIPTION);
            DialogBox justinBox = DialogBox.getJustinDialog(response, justin, JUSTIN_DESCRIPTION);
            dialogContainer.getChildren().addAll(userBox, justinBox);
            if (c instanceof ExitCommand) {
                System.exit(0);
            }
        } catch (DukeException e) {
            DialogBox userBox = DialogBox.getUserDialog(input, user, YOUR_DESCRIPTION);
            DialogBox justinBox = DialogBox.getJustinDialog(e.getMessage(), justin, JUSTIN_DESCRIPTION);
            dialogContainer.getChildren().addAll(userBox, justinBox);
        } catch (Exception e) {
            e.printStackTrace();
        }
        userInput.clear();
    }

    public void printMessage(String msg) {
        DialogBox justinBox = DialogBox.getJustinDialog(msg, justin, JUSTIN_DESCRIPTION);
        dialogContainer.getChildren().add(justinBox);
    }

    public void handleException(String str) {
        DialogBox justinBox = DialogBox.getJustinDialog(str, justin, JUSTIN_DESCRIPTION);
        dialogContainer.getChildren().add(justinBox);
    }
}
