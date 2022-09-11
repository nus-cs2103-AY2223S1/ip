package duke.fxwindows;

import duke.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class Window extends AnchorPane {

    @FXML
    private HBox contentBox;
    @FXML
    private TextField userInput;
    @FXML
    private Button helpButton;

    private Duke duke;

    private TaskCategoryPane taskCategoryPane;

    public void initialise(Duke duke) {
//        Setup duke
        this.duke = duke;
        this.duke.init();

//        Setup panes
        this.loadTaskCategoryPane();
    }

    private void loadTaskCategoryPane() {
        this.taskCategoryPane = new TaskCategoryPane();
        this.contentBox.getChildren().add(this.taskCategoryPane);
    }

    @FXML
    public void handleUserInput() {
        String input = userInput.getText();
        String response = duke.execCommand(input);
//        dialogContainer.getChildren().addAll(
//                DialogBox.getUserDialog(input, userImage),
//                DialogBox.getDukeDialog(response, dukeImage)
//        );
        System.out.println("response");
        userInput.setText("");
    }
}
