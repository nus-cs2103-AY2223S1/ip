package dukeprogram.userinterface;

import java.util.LinkedList;

import dukeprogram.Duke;
import javafx.animation.PauseTransition;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Application app;

    private boolean hasStoppedResponse = true;
    private final LinkedList<DukeResponse> queuedResponses = new LinkedList<>();
    private boolean wasUserDialogPreviously = true;
    private DialogBubbleGroup groupedDialogBubbles;
    private boolean isScrolling = false;
    private int numberOfBubbles = 0;

    /**
     * Initialises the main window
     */
    @FXML
    public void initialize() {
        userInput.setBackground(new Background(new BackgroundFill(
                Color.AQUAMARINE,
                new CornerRadii(10),
                Insets.EMPTY))
        );

        dialogContainer.heightProperty().addListener(c -> scrollSmoothly());
        duke = new Duke(this);
    }

    private void scrollSmoothly() {
        Transition smoothScrollTransition = new Transition() {
            private double startValue = scrollPane.getVvalue();
            private double difference = 1 - startValue;

            {
                setCycleDuration(Duration.millis(300));
            }
            @Override
            protected void interpolate(double fraction) {
                scrollPane.setVvalue(this.startValue + fraction * difference);
            }
        };

        if (!isScrolling) {
            isScrolling = true;
            smoothScrollTransition.play();
            smoothScrollTransition.setOnFinished(e -> isScrolling = false);
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        getDialogGroup(true).getChildren().add(DialogBox.ofUser(input, duke.getUser()));
        incrementAndClearExcessDialogBubbles();
        duke.parseInput(input);
        userInput.clear();
    }

    /**
     * Sends a dialog from Duke to the user
     * @param response the response to show to the user
     */
    public void sendDukeDialog(DukeResponse response) {
        queuedResponses.addLast(response);

        if (hasStoppedResponse) {
            consumeResponse();
        }
    }

    private void consumeResponse() {
        hasStoppedResponse = false;
        if (!queuedResponses.isEmpty()) {
            DukeResponse nextResponse = queuedResponses.poll();
            PauseTransition pause = new PauseTransition(Duration.millis(500));
            pause.setOnFinished(event -> {
                getDialogGroup(false).getChildren().add(nextResponse.createDialogBox());
                incrementAndClearExcessDialogBubbles();
                consumeResponse();
            });
            pause.play();
        } else {
            hasStoppedResponse = true;
        }
    }

    private VBox getDialogGroup(boolean isCurrentDialogFromUser) {
        if (isCurrentDialogFromUser ^ wasUserDialogPreviously) {
            if (isCurrentDialogFromUser) {
                groupedDialogBubbles = new DialogBubbleGroup(5,
                        duke.getUser().getUserImage(), dialogContainer);
            } else {
                groupedDialogBubbles = new DialogBubbleGroup(5, dialogContainer);
            }
        }

        wasUserDialogPreviously = isCurrentDialogFromUser;
        return groupedDialogBubbles.getDialogBubbleContainer();
    }

    private void incrementAndClearExcessDialogBubbles() {
        numberOfBubbles++;
        if (numberOfBubbles > 60) {
            if (dialogContainer.getChildren().size() == 1) {
                groupedDialogBubbles.getDialogBubbleContainer().getChildren().remove(0);
            } else {
                DialogBubbleGroup firstGroup = (DialogBubbleGroup) dialogContainer.getChildren().get(0);
                VBox firstGroupDialogContainer = firstGroup.getDialogBubbleContainer();
                if (firstGroupDialogContainer.getChildren().size() > 1) {
                    firstGroupDialogContainer.getChildren().remove(0);
                } else {
                    dialogContainer.getChildren().remove(0);
                }
            }
            numberOfBubbles--;
        }
    }
}
