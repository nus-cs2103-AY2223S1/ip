package maria.ui;

import java.io.IOException;
import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import maria.TaskManager;
import maria.command.Command;
import maria.command.CommandAddDeadline;

public class AddDeadline extends AnchorPane {

    private LandingPage landingPage;
    private TaskManager taskManager;

    @FXML
    private Button buttonOk;

    @FXML
    private CheckBox checkBoxDone;

    @FXML
    private TextField textFieldName;

    @FXML
    private DatePicker datePickerDeadline;

    public AddDeadline(LandingPage landingPage, TaskManager taskManager) {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("add_deadline.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        this.landingPage = landingPage;
        this.taskManager = taskManager;

    }

    /**
     * Executes an add deadline command.
     *
     * @see CommandAddDeadline
     */
    @FXML
    private void buttonAddTaskClicked() {

        String name = this.textFieldName.getText();
        LocalDate deadline = this.datePickerDeadline.getValue();

        if (deadline != null && !name.isEmpty()) {

            Command command = new CommandAddDeadline(name, this.checkBoxDone.isSelected(), deadline);
            command.execute(this.taskManager);
            this.landingPage.refreshTasks();

            Stage s = (Stage) this.getScene().getWindow();
            s.close();

        } else if (deadline == null) {
            UiUtils.highlightControlError(this.datePickerDeadline);
        } else {
            UiUtils.highlightControlError(this.textFieldName);
        }

    }

}
