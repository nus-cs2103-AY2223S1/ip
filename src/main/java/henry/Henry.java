package henry;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;

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
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import javafx.util.Duration;
import util.TextUtils;

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
    private final int exitDelay = 2;

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
            stage.setTitle("Henry, the Helpful Chatbot");
            stage.getIcons().add(new Image(
                Objects.requireNonNull(this.getClass().getResourceAsStream("/images/henry.png"))));
            fxmlLoader.<MainWindow>getController().setHenry(this);
            stage.show();
        } catch (Exception e) {
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
            PauseTransition delay = new PauseTransition(Duration.seconds(exitDelay));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
            return TextUtils.TASKS_SAVED_MESSAGE;
        }

        Command parsed = parser.parseCommand(input);
        CommandResult result = executeCommand(parsed);
        return result.toString();
    }

    private CommandResult executeCommand(Command command) {
        try {
            performSanityChecks(command);
            return appendResultToFile(getCommandResult(command));
        } catch (HenryException he) {
            throw he;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private CommandResult getCommandResult(Command command) {
        command.setData(taskList);
        return command.execute();
    }

    private CommandResult appendResultToFile(CommandResult result) throws IOException {
        if (result.getTaskList().isPresent()) {
            storage.appendToFile(result.getTaskList().get().toFileEncodedString());
        }
        return result;
    }

    private void performSanityChecks(Command command) {
        assert taskList != null : "TaskList is null!";

        if (command instanceof TaskCommand) {
            Task tempTask = ((TaskCommand) command).getTask();
            if (isTaskInTaskList(tempTask)) {
                throw new HenryException(TextUtils.DUPLICATE_TASK_ERROR);
            }
        }
    }

    /**
     * A Task is considered duplicate if it shares the same
     * description and date with another Task in the TaskList.
     *
     * @param task the Task to be checked
     * @return true if the Task is a duplicate, false otherwise
     */
    private boolean isTaskInTaskList(Task task) {
        for (Task t : taskList) {
            if (t.equals(task)) {
                return true;
            }
        }
        return false;
    }
}
