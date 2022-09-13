package duke.gui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


/**
 * DialogContainer is a container to contain dialog boxes generated through user interaction.
 */
public class DialogContainer extends ScrollPane {
    private static final double CONTAINER_PREF_WIDTH = 400.0;
    private static final double CONTAINER_PREF_HEIGHT = 550.0;

    private final VBox dialogBoxContainer;

    private final Image userPicture = new Image(this.getClass().getResourceAsStream("/images/User.jpg"));
    private final Image chatbotPicture = new Image(this.getClass().getResourceAsStream("/images/Christina.jpg"));

    private final Background userBackground = new Background(new BackgroundFill(Color.web("#e7e7e7"),
            new CornerRadii(20), Insets.EMPTY));
    private final Background chatbotBackground = new Background(new BackgroundFill(Color.web("#e7e7e7"),
            new CornerRadii(20), Insets.EMPTY));
    /**
     * Creates a container to contain the dialog boxes generated through user interaction.
     */
    public DialogContainer() {
        this.dialogBoxContainer = new VBox();
        this.setContent(dialogBoxContainer);

        this.setPrefSize(CONTAINER_PREF_WIDTH, CONTAINER_PREF_HEIGHT);
        this.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        this.setVvalue(1.0);
        this.setFitToWidth(true);

        this.dialogBoxContainer.setMinHeight(Region.USE_COMPUTED_SIZE);
        this.dialogBoxContainer.heightProperty().addListener((observable) -> this.setVvalue(1.0));
    }

    /**
     * Initializes the dialog box container with the initialization dialog box.
     *
     * @param initializationText String of the text to indicate initialization of the program
     */
    public void initializeDialog(String initializationText) {
        Label initializationTextLabel = new Label(initializationText);
        this.dialogBoxContainer.getChildren().addAll(
                DialogBox.getResponseDialog(initializationTextLabel, chatbotPicture, chatbotBackground));
    }

    /**
     * Updates the dialog box container with dialog boxes corresponding to user interactions.
     *
     * @param userText String of the text input by the user
     * @param responseText String of the response to the user
     */
    public void updateDialog(String userText, String responseText) {
        Label userTextLabel = new Label(userText);
        Label responseTextLabel = new Label(responseText);
        this.dialogBoxContainer.getChildren().addAll(
                DialogBox.getUserDialog(userTextLabel, userPicture, userBackground),
                DialogBox.getResponseDialog(responseTextLabel, chatbotPicture, chatbotBackground)
        );
    }
}
