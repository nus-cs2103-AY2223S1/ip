package justin;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import justin.command.Command;
import justin.command.NewCommand;

/**
 * Main class to run the application.
 * @author Justin Cheng.
 */
public class Main extends Application {
    private JustinBot justinBot = new JustinBot("justin.txt");

    /**
     * Starts the application.
     * @param stage The stage to start the application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("JustinBot");
            Command newCommand = new NewCommand();
            String welcome = newCommand.execute(justinBot.getTasks(), justinBot.getUi(), justinBot.getStorage());
            fxmlLoader.<MainWindow>getController().setJustinBot(justinBot);
            fxmlLoader.<MainWindow>getController().printWelcome(welcome);
            stage.show();
        } catch (DukeException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
