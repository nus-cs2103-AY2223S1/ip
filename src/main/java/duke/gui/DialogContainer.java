package duke.gui;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * DialogContainer is a container to contain dialog boxes generated through user interaction.
 */
public class DialogContainer extends ScrollPane {
    private final VBox dialogBoxContainer;

    private Image userPicture = new Image(this.getClass().getResourceAsStream("/images/User.jpg"));
    private Image chatbotPicture = new Image(this.getClass().getResourceAsStream("/images/Christina.jpg"));

    /**
     * Creates a container to contain the dialog boxes generated through user interaction.
     *
     * @param windowMinWidth minimum width of the window to set preferred width
     * @param windowMinHeight minimum height of the window to set preferred height
     */
    public DialogContainer(double windowMinWidth, double windowMinHeight) {
        this.dialogBoxContainer = new VBox();
        this.setContent(dialogBoxContainer);

        this.setPrefSize(windowMinWidth, windowMinHeight - 30.0);
        this.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        this.setVvalue(1.0);
        this.setFitToWidth(true);

        this.dialogBoxContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        this.dialogBoxContainer.heightProperty().addListener((observable) -> this.setVvalue(1.0));
    }

    /**
     * Initializes the dialog box container withe the initialization dialog box.
     *
     * @param initializationText String of the text to indicate initialization of the program
     */
    public void initializeDialog(String initializationText) {
        Label initializationTextLabel = new Label(initializationText);
        this.dialogBoxContainer.getChildren().addAll(
                DialogBox.getResponseDialog(initializationTextLabel, new ImageView(chatbotPicture)));
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
                DialogBox.getUserDialog(userTextLabel, new ImageView(userPicture)),
                DialogBox.getResponseDialog(responseTextLabel, new ImageView(chatbotPicture))
        );
    }
}
