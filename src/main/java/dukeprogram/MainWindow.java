package dukeprogram;

import java.util.LinkedList;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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

    private boolean hasStoppedResponse = true;
    private final LinkedList<DukeResponse> queuedResponses = new LinkedList<>();
    private boolean wasUserDialogPreviously = true;
    private VBox groupedDialogBubbles;

    /**
     * Initialises the main window
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        duke = new Duke(this);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        getDialogGroup(true).getChildren().add(DialogBox.ofUser(input, duke.getUser()));
        duke.parseInput(input);

        userInput.clear();
    }

    private void consumeResponse() {
        hasStoppedResponse = false;
        if (!queuedResponses.isEmpty()) {
            DukeResponse nextResponse = queuedResponses.poll();
            PauseTransition pause = new PauseTransition(Duration.millis(500));
            pause.setOnFinished(event -> {
                getDialogGroup(false).getChildren().add(nextResponse.createDialogBox());
                consumeResponse();
            });
            pause.play();
        } else {
            hasStoppedResponse = true;
        }
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

    private VBox getDialogGroup(boolean isCurrentDialogFromUser) {
        if (isCurrentDialogFromUser ^ wasUserDialogPreviously) {
            groupedDialogBubbles = new VBox();
            groupedDialogBubbles.setSpacing(5);
            HBox pictureAndDialogDivider = new HBox();
            setDisplayPicture(pictureAndDialogDivider, isCurrentDialogFromUser);
            groupedDialogBubbles.setAlignment(isCurrentDialogFromUser ? Pos.TOP_RIGHT : Pos.TOP_LEFT);
            dialogContainer.getChildren().add(pictureAndDialogDivider);
        }
        wasUserDialogPreviously = isCurrentDialogFromUser;
        return groupedDialogBubbles;
    }

    private void setDisplayPicture(HBox groupContainer, boolean isClientDialog) {
        ImageView displayPicture = new ImageView();

        if (isClientDialog) {
            groupContainer.setAlignment(Pos.BOTTOM_RIGHT);
            displayPicture.setImage(duke.getUser().getUserImage());
            groupContainer.getChildren().addAll(groupedDialogBubbles, displayPicture);
        } else {
            groupContainer.setAlignment(Pos.BOTTOM_LEFT);
            displayPicture.setImage(User.DUKE.getUserImage());
            groupContainer.getChildren().addAll(displayPicture, groupedDialogBubbles);
        }

        new LayoutAnimator(false, true, 800)
                .observe(groupContainer.getChildren());

        new LayoutAnimator(true, false, 500)
                .observe(groupContainer);

        new DropShadowCircleFrame(25, 25, 1, 1)
                .frame(displayPicture);
    }
}
