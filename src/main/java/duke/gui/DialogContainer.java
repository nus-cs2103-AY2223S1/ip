package duke.gui;

import java.util.Objects;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * DialogContainer is a container to contain dialog boxes generated through user interaction.
 */
public class DialogContainer extends ScrollPane {
    private static final Color USER_DB_OUTER_COLOUR = Color.web("42a5f5");
    private static final Color USER_DB_INNER_COLOUR = Color.web("c3fdff");
    private static final Color USER_TEXT_COLOUR = Color.web("2196f3");

    private static final Color CHATBOT_DB_OUTER_COLOUR = Color.web("ff9100");
    private static final Color CHATBOT_DB_INNER_COLOUR = Color.web("ffc246");
    private static final Color CHATBOT_TEXT_COLOUR = Color.web("ff4081");

    private static final CornerRadii DB_CORNER_RADII = new CornerRadii(10.0);
    private static final Insets DB_INSET = new Insets(2.5);

    private final VBox dialogBoxContainer;

    private final Image userPicture;
    private final Image chatbotPicture;
    private final Image chatbotAnnoyedPicture;
    private final Background userTextBackground;
    private final Background chatbotTextBackground;
    /**
     * Creates a container to contain the dialog boxes generated through user interaction.
     *
     * @param mainWindow a reference to the main window to bind width and height property
     */
    public DialogContainer(MainWindow mainWindow) {
        // DisplayPicture properties
        this.userPicture = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/User.jpg")));
        this.chatbotPicture =
                new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/Christina.jpg")));
        this.chatbotAnnoyedPicture =
                new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/Christina_annoyed.jpg")));

        // DisplayTextBackground properties
        BackgroundFill userOuterFill = new BackgroundFill(USER_DB_OUTER_COLOUR, DB_CORNER_RADII, Insets.EMPTY);
        BackgroundFill userInnerFill = new BackgroundFill(USER_DB_INNER_COLOUR, DB_CORNER_RADII, DB_INSET);
        BackgroundFill chatbotOuterFill = new BackgroundFill(CHATBOT_DB_OUTER_COLOUR, DB_CORNER_RADII, Insets.EMPTY);
        BackgroundFill chatbotInnerFill = new BackgroundFill(CHATBOT_DB_INNER_COLOUR, DB_CORNER_RADII, DB_INSET);
        this.userTextBackground = new Background(userOuterFill, userInnerFill);
        this.chatbotTextBackground = new Background(chatbotOuterFill, chatbotInnerFill);

        // DialogBoxContainer properties
        Image backgroundPicture =
                new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/Background.png")));
        BackgroundImage backgroundImage = new BackgroundImage(backgroundPicture,
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        this.dialogBoxContainer = new VBox();
        this.dialogBoxContainer.setBackground(new Background(backgroundImage));
        this.dialogBoxContainer.setPadding(new Insets(0, 15, 0, 0));
        this.dialogBoxContainer.minHeightProperty().bind(this.heightProperty());
        this.dialogBoxContainer.prefWidthProperty().bind(this.widthProperty());
        this.dialogBoxContainer.heightProperty().addListener((observable) -> this.setVvalue(1.0));

        // DialogContainer properties
        this.setContent(dialogBoxContainer);
        this.prefWidthProperty().bind(mainWindow.widthProperty());
        this.prefHeightProperty().bind(mainWindow.heightProperty());
        this.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        this.setVvalue(1.0);
    }

    /**
     * Initializes the dialog box container with the initialization dialog box.
     *
     * @param initializationText String of the text to indicate initialization of the program
     */
    public void initializeDialog(String initializationText) {
        Label initializationTextLabel = new Label(initializationText);
        initializationTextLabel.setTextFill(CHATBOT_TEXT_COLOUR);
        this.dialogBoxContainer.getChildren().addAll(
                DialogBox.getChatbotDialog(initializationTextLabel, chatbotPicture, chatbotTextBackground));
    }

    /**
     * Updates the dialog box container with dialog boxes corresponding to user interactions.
     *
     * @param userText String of the text input by the user
     * @param chatbotText String of the response by the chatbot
     */
    public void updateDialog(String userText, String chatbotText, boolean isAnnoyed) {
        Label userTextLabel = new Label(userText);
        Label chatbotTextLabel = new Label(chatbotText);
        userTextLabel.setTextFill(USER_TEXT_COLOUR);
        chatbotTextLabel.setTextFill(CHATBOT_TEXT_COLOUR);

        Image currentChatbotPicture = chatbotPicture;
        if (isAnnoyed) {
            currentChatbotPicture = chatbotAnnoyedPicture;
        }

        DialogBox userDialogBox = DialogBox.getUserDialog(userTextLabel, userPicture, userTextBackground);
        DialogBox chatBotDialogBox = DialogBox.getChatbotDialog(chatbotTextLabel,
                currentChatbotPicture, chatbotTextBackground);
        this.dialogBoxContainer.getChildren().addAll(userDialogBox, chatBotDialogBox);
    }
}
