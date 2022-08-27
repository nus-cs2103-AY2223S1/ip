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
import maria.command.CommandAddEvent;

public class AddEvent extends AnchorPane {

    private LandingPage landingPage;
    private TaskManager taskManager;

    @FXML
    private Button buttonOk;

    @FXML
    private CheckBox checkBoxDone;

    @FXML
    private TextField textFieldName;

    @FXML
    private DatePicker datePickerStart;

    @FXML
    private DatePicker datePickerEnd;

    public AddEvent(LandingPage landingPage, TaskManager taskManager) {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("add_event.fxml"));
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
     * Executes an add event command.
     *
     * @see CommandAddEvent
     */
    @FXML
    private void buttonAddTaskClicked() {

        String name = this.textFieldName.getText();
        LocalDate start = datePickerStart.getValue();
        LocalDate end = datePickerEnd.getValue();

        if (start != null && end != null && !name.isEmpty()) {

            Command command = new CommandAddEvent(name, this.checkBoxDone.isSelected(), start, end);
            command.execute(this.taskManager);
            this.landingPage.refreshTasks();

            Stage s = (Stage) this.getScene().getWindow();
            s.close();

        } else if (start == null) {
            UiUtils.highlightControlError(this.datePickerStart);
        } else if (end == null) {
            UiUtils.highlightControlError(this.datePickerEnd);
        } else {
            UiUtils.highlightControlError(this.textFieldName);
        }

    }

}
