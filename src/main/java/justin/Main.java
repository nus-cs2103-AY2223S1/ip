package justin;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import justin.command.Command;
import justin.command.NewCommand;

public class Main extends Application {
    private JustinBot justinBot = new JustinBot("justin.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("JustinBot");
            Ui ui = justinBot.getUi();
            TaskList list = justinBot.getTasks();
            Command newCommand = new NewCommand();
            String welcome = newCommand.getMessage(list, ui);
 //           justinBot.setMw(mw);
//            mw.printMessage(welcome);
            fxmlLoader.<MainWindow>getController().setJustinBot(justinBot);
            fxmlLoader.<MainWindow>getController().printMessage(welcome);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
