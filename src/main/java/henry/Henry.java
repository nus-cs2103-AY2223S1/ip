package henry;

import java.io.IOException;
import java.nio.file.Path;

import command.Command;
import command.CommandResult;
import command.TaskCommand;
import components.MainWindow;
import exceptions.HenryException;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * The base class of the Henry application.
 * All functions of Henry pass through this class.
 */
public class Henry extends Application {

    private static final String home = System.getProperty("user.home");
    // The text file is created on the user's Desktop
    private static final Path FILE_PATH = java.nio.file.Paths.get(home, "Desktop", "henry.txt");
    private final Storage storage;
    private final TaskList taskList;
    private final Parser parser;

    /**
     * The constructor for the logical component of Henry.
     * When Henry is instantiated, a new Storage, TaskList
     * and Parser classes are also created.
     */
    public Henry() {
        storage = new Storage(FILE_PATH.toString());
        taskList = new TaskList(storage.load());
        parser = new Parser();
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Henry.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setHenry(this);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Generates a response based on user input.
     *
     * @param input the given user input
     * @return a String representing the {@link CommandResult} of the user input
     */
    public String getResponse(String input) {
        assert input != null : "Input is null!";

        if (input.equalsIgnoreCase("bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
            return "GOODBYE! YOUR TASK LIST HAS BEEN SAVED!";
        }

        Command parsed = parser.parseCommand(input.toLowerCase());
        CommandResult result = executeCommand(parsed);
        return result.toString();
    }

    private CommandResult executeCommand(Command command) {
        try {
            assert taskList != null : "TaskList is null!";
            if (command instanceof TaskCommand) {
                Task tempTask = ((TaskCommand) command).getTask();
                if (isTaskInTaskList(tempTask)) {
                    throw new HenryException("TASK ALREADY EXISTS!");
                }
            }
            command.setData(taskList);
            CommandResult result = command.execute();
            if (result.getTaskList().isPresent()) {
                storage.appendToFile(result.getTaskList().get().toFileEncodedString());
            }
            return result;
        } catch (HenryException he) {
            throw he;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isTaskInTaskList(Task task) {
        for (Task t : taskList.getTasks()) {
            if (t.getDescription().equals(task.getDescription())
                && t.getLocalDateTime().equals(task.getLocalDateTime())) {
                return true;
            }
        }
        return false;
    }
}
