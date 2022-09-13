package duke.gui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


/**
 * DialogContainer is a container to contain dialog boxes generated through user interaction.
 */
public class DialogContainer extends ScrollPane {
    private static final double DIALOG_BOX_BACKGROUND_CORNER_RADII = 10.0;
    private static final Color USER_DIALOG_BACKGROUND_COLOUR = Color.web("84c4f4");
    private static final Color CHATBOT_DIALOG_BACKGROUND_COLOUR = Color.web("ffcb8c");

    private final VBox dialogBoxContainer;

    private final Image userPicture;
    private final Image chatbotPicture;
    private final Background userBackground;
    private final Background chatbotBackground;
    /**
     * Creates a container to contain the dialog boxes generated through user interaction.
     */
    public DialogContainer(MainWindow mainWindow) {
        this.userPicture = new Image(this.getClass().getResourceAsStream("/images/User.jpg"));
        this.chatbotPicture = new Image(this.getClass().getResourceAsStream("/images/Christina.jpg"));
        this.userBackground = new Background(new BackgroundFill(USER_DIALOG_BACKGROUND_COLOUR,
                new CornerRadii(DIALOG_BOX_BACKGROUND_CORNER_RADII), Insets.EMPTY));
        this.chatbotBackground = new Background(new BackgroundFill(CHATBOT_DIALOG_BACKGROUND_COLOUR,
                new CornerRadii(DIALOG_BOX_BACKGROUND_CORNER_RADII), Insets.EMPTY));

        this.dialogBoxContainer = new VBox();
        this.setContent(dialogBoxContainer);

        this.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        this.setVvalue(1.0);
        this.prefWidthProperty().bind(mainWindow.widthProperty());
        this.prefHeightProperty().bind(mainWindow.heightProperty());

        this.dialogBoxContainer.setPadding(new Insets(0, 15, 0, 0));
        this.dialogBoxContainer.prefWidthProperty().bind(this.widthProperty());
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
