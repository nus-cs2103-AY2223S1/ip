package command;

import exception.DukeException;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import storage.Storage;
import task.TaskList;
import ui.DialogBox;
import ui.Ui;

public class ExitCommand extends Command {
    public ExitCommand(TaskList tasks, Ui ui) {
        super(tasks, "", ui);
    }

    @Override
    public void execute(VBox dialogContainer, DialogBox userDialog, Storage storage) {
        try {
            storage.writeToFile(tasks);
            ui.sayGoodbye(dialogContainer, userDialog);
            executeDelay();
        } catch (DukeException e) {
            ui.sayErrorMessageWithUserInput(e.getMessage(), dialogContainer, userDialog);
        }
    }

    private void executeDelay() {
        PauseTransition delay = new PauseTransition(Duration.seconds(2.0));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();
    }
}
