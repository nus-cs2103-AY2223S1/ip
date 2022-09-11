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
    private TaskListPane taskListPane;
    private TaskDescriptionPane taskDescriptionPane;

    public void initialise(Duke duke) {
//        Setup duke
        this.duke = duke;
        this.duke.init();

//        Setup panes
        this.loadTaskCategoryPane();
        this.loadTaskListPane();
        this.loadTaskDescPane();
    }

    private void loadTaskCategoryPane() {
        this.taskCategoryPane = new TaskCategoryPane();
        this.contentBox.getChildren().add(this.taskCategoryPane);
    }

    private void loadTaskListPane() {
        this.taskListPane = new TaskListPane(this.duke.getTasks());
        this.contentBox.getChildren().add(this.taskListPane);
    }

    private void loadTaskDescPane() {
        this.taskDescriptionPane = new TaskDescriptionPane(this.duke.getTasks().get(0));
        this.contentBox.getChildren().add(this.taskDescriptionPane);
    }

    @FXML
    public void handleUserInput() {
        String input = userInput.getText();
        String response = duke.execCommand(input);
//        dialogContainer.getChildren().addAll(
//                DialogBox.getUserDialog(input, userImage),
//                DialogBox.getDukeDialog(response, dukeImage)
//        );
        System.out.println(response);
        userInput.setText("");
    }
}
