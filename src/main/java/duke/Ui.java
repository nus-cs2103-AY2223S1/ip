package duke;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Ui {

    private static final int WINDOW_HEIGHT = 600;
    private static final int WINDOW_WIDTH = 400;
    private static final double SCROLL_PANE_HEIGHT_PROPORTION = 0.93;
    private static final double TEXT_FIELD_WIDTH_PROPORTION = 0.80;

    private static final String LINE_STR = "-".repeat(50); // deprecated
    private final String chatBotName;

    public Ui(String chatBotName) {
        this.chatBotName = chatBotName;
    }

    /**
     * @deprecated
     */
    public void welcomeUser() {
        replyUser(
            String.join("\n", String.format("Hi I'm %s", chatBotName), "What can I do for you?"));
    }

    /**
     * @deprecated
     */
    public void replyUser(String response) {
        System.out.printf("\t%s\n", LINE_STR);
        System.out.printf("\t%s\n", response.replaceAll("\\n", "\n\t"));
        System.out.printf("\t%s\n", LINE_STR);
    }

    /**
     * @deprecated
     */
    public void raiseError(String errorMessage) {
        replyUser(String.format("X %s", errorMessage));
    }

    public void createUi(Stage stage) {
        stage.setTitle(chatBotName);
        stage.setMinHeight(WINDOW_HEIGHT);
        stage.setMinWidth(WINDOW_WIDTH);
        stage.setResizable(false);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefSize(WINDOW_WIDTH, WINDOW_HEIGHT * SCROLL_PANE_HEIGHT_PROPORTION);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        VBox dialogContainer = new VBox();
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        scrollPane.setContent(dialogContainer);
        TextField userInput = new TextField();
        userInput.setPrefHeight(WINDOW_HEIGHT * (1 - SCROLL_PANE_HEIGHT_PROPORTION));
        userInput.setPrefWidth(WINDOW_WIDTH * TEXT_FIELD_WIDTH_PROPORTION);
        Button sendButton = new Button("Send");
        sendButton.setPrefHeight(WINDOW_HEIGHT * (1 - SCROLL_PANE_HEIGHT_PROPORTION));
        sendButton.setPrefWidth(WINDOW_WIDTH * (1 - TEXT_FIELD_WIDTH_PROPORTION));

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.setPrefSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        // Anchor scrollPane
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        // Anchor sendButton
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        // Anchor user text field / input
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        stage.setScene(new Scene(mainLayout));
    }
}
