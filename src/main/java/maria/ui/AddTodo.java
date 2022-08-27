package maria.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import maria.TaskManager;
import maria.command.Command;
import maria.command.CommandAddTodo;

public class AddTodo extends AnchorPane {

    private LandingPage landingPage;
    private TaskManager taskManager;

    @FXML
    private Button buttonAddTask;

    @FXML
    private CheckBox checkBoxDone;

    @FXML
    private TextField textFieldName;

    /**
     * Executes an add todo command.
     *
     * @see CommandAddTodo
     */
    public AddTodo(LandingPage landingPage, TaskManager taskManager) {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("add_todo.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        this.landingPage = landingPage;
        this.taskManager = taskManager;

    }

    @FXML
    private void buttonAddTaskClicked() {

        String name = this.textFieldName.getText();

        if (!name.isEmpty()) {

            Command command = new CommandAddTodo(name, this.checkBoxDone.isSelected());
            command.execute(this.taskManager);
            this.landingPage.refreshTasks();

            Stage s = (Stage) this.getScene().getWindow();
            s.close();

        } else {
            UiUtils.highlightControlError(this.textFieldName);
        }

    }

}
