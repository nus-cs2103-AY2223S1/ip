package maria.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import maria.Parser;
import maria.TaskManager;
import maria.command.Command;
import maria.command.CommandExecutor;
import maria.command.CommandFindTask;
import maria.task.Task;
import maria.task.TaskList;

/**
 * Represents the controller for the main page of the application.
 */
public class LandingPage extends VBox {

    private TaskManager taskManager;

    @FXML
    private TextArea textAreaDisplay;

    @FXML
    private TextField textFieldCommand;

    @FXML
    private Button buttonExecuteCommand;

    @FXML
    private Button buttonHelp;

    /**
     * Creates a LandingPage object with the associated task manager.
     * @param taskManager The task manager, to be used throughout the UI system.
     */
    public LandingPage(TaskManager taskManager) {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("landing_page.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        this.taskManager = taskManager;
        this.textAreaDisplay.setText("Hi, I am Maria, your personal assistant.\n"
                + "Type 'help' or click the Help button to get started.");

    }

    @FXML
    private void buttonExecuteCommandClicked() {

        String commandString = this.textFieldCommand.getText();
        Command command = Parser.parse(commandString);
        this.taskManager.getCommandExecutor().executeCommand(command);

        pollResultDisplayQueue();

    }

    @FXML
    private void buttonHelpClicked() {

    }

    private void pollResultDisplayQueue() {

        String content = this.taskManager.getResultDisplayQueue().poll();

        if (content == null) {
            return;
        }

        String displayedText = textAreaDisplay.getText();

        if (displayedText.isEmpty()) {
            textAreaDisplay.setText(content);
        } else {
            textAreaDisplay.setText(textAreaDisplay.getText() + "\n\n" + content);
        }

    }

}