package john;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import john.commands.Command;
import john.data.TaskList;
import john.data.exception.JohnException;
import john.parser.Parser;
import john.storage.Storage;
import john.ui.MainWindow;
import john.ui.Ui;


/**
 * The main logic of the John chatbot.
 */
public class John extends Application {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    private John(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.taskList = new TaskList(storage.load());
        } catch (JohnException e) {
            this.taskList = new TaskList();
            e.printStackTrace();
        }
    }

    public John() {
        this("data/tasks.txt");
    }

    @Override
    public void start(Stage stage) {
        John john = new John();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(John.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("John");
            fxmlLoader.<MainWindow>getController().setJohn(john);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String executeCommand(Command command) {
        try {
            command.setData(taskList, ui);
            String result = command.execute();
            storage.saveAllTasks(taskList.getTasksToStore());
            return result;
        } catch (JohnException e) {
            return e.getMessage();
        }
    }

    /**
     * Returns a string representation of the command executed.
     *
     * @param input The command to execute.
     * @return A string representation of the command executed.
     */
    public String getResponse(String input) {
        Command command = new Parser().parseCommand(input);
        return executeCommand(command);
    }

}
