package Duke;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;

/**
 * The Duke.Duke class stores Storage and TaskList as parameters
 * @author LimWeiJun
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private MainWindow mainWindow;

    public Duke(String filePath, FXMLLoader fxmlLoader) {
        ui = new Ui();
        mainWindow = fxmlLoader.<MainWindow>getController();
        String[] seperates = filePath.split("/");
        storage = new Storage(ui, seperates[0], seperates[1], mainWindow);
        try {
            tasks = new TaskList(ui, storage, mainWindow);
        } catch (Exception e) {
            mainWindow.printErrorMessage(e.toString());
        }
    }

    public void takeCommand(String userCommand) {
        Parser.readLine(ui, userCommand, tasks, mainWindow);
    }

    public static void main(String[] args) {
        Application.launch(Ui.class, args);
    }
}
