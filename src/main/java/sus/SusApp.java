package sus;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sus.commands.Command;
import sus.commands.CommandResult;
import sus.commands.InvalidCommand;
import sus.common.Messages;
import sus.parser.Parser;
import sus.storage.StorageFile;
import sus.task.TaskList;
import sus.ui.DialogBox;
import sus.ui.TextUi;

/**
 * Runs the GUI application.
 */
public class SusApp extends Application {

    private enum MessageType {
        USER("#D1D1D1", "#000000"),
        GREETING("#FFA500", "#000000"),
        SUCCESS("#00D100", "#000000"),
        INVALID("#8b0000", "#FFFFFF"),
        EXIT("#ffffff", "#000000");

        private final Color backgroundColour;
        private final Color textColour;

        MessageType(String backgroundColour, String textColour) {
            this.backgroundColour = Color.valueOf(backgroundColour);
            this.textColour = Color.valueOf(textColour);
        }

        public Color getBackgroundColour() {
            return backgroundColour;
        }

        public Color getTextColour() {
            return textColour;
        }
    }

    private static final int UI_HEIGHT = 700;
    private static final int UI_WIDTH = 500;

    private Button sendButton;
    private ScrollPane scrollPane;
    private TextField userInput;
    private VBox dialogContainer;

    private StorageFile storage;
    private TaskList taskList;
    private TextUi textUi;

    @Override
    public void start(Stage stage) {
        load();

        customiseStage(stage);
        stage.setScene(renderScene());
        stage.show();
        showBotMessage(MessageType.GREETING, Messages.MESSAGE_WELCOME);
    }

    private void load() {
        storage = new StorageFile();
        taskList = new TaskList(storage.load());
        textUi = new TextUi();

        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        userInput = new TextField();
        sendButton = new Button("Send");
    }

    /**
     * Renders the GUI component.
     */
    private Scene renderScene() {
        HBox.setMargin(sendButton, new Insets(0, 20, 10, 0));
        HBox.setMargin(userInput, new Insets(0, 5, 10, 5));

        VBox root = new VBox();
        HBox inputWrapper = getStyledInput();
        root.getChildren().addAll(dialogContainer);

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, inputWrapper);
        mainLayout.setPrefSize(UI_WIDTH, UI_HEIGHT);

        scrollPane.setStyle("-fx-font-size: 18px;");
        scrollPane.setContent(root);
        scrollPane.widthProperty().addListener((o) -> {
            Node vp = scrollPane.lookup(".viewport");
            vp.setStyle("-fx-background-color: linear-gradient(to bottom right, #0000A8, #0470de);");
        });
        dialogContainer.setStyle("-fx-font: 12px Verdana;");

        scrollPane.setPrefHeight(UI_HEIGHT);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setRightAnchor(scrollPane, 1.0);
        AnchorPane.setLeftAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(scrollPane, 50.0);

        addEventListeners();

        return new Scene(mainLayout);
    }

    private HBox getStyledInput() {
        HBox inputWrapper = new HBox();
        inputWrapper.getChildren().addAll(userInput, sendButton);
        inputWrapper.setAlignment(Pos.BOTTOM_CENTER);

        userInput.setPrefWidth(400);
        sendButton.setMinWidth(90.0);

        sendButton.disableProperty().bind(Bindings.isEmpty(userInput.textProperty()));
        sendButton.setStyle("-fx-background-color: #20B2AA; -fx-background-radius: 15px; -fx-text-fill: #ffffff");

        AnchorPane.setBottomAnchor(inputWrapper, 1.0);
        AnchorPane.setRightAnchor(inputWrapper, 15.0);
        AnchorPane.setLeftAnchor(inputWrapper, 15.0);

        return inputWrapper;
    }

    private void addEventListeners() {
        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        sendButton.setOnMouseClicked((event) -> handleUserInput());
        userInput.setOnAction((event) -> handleUserInput());
    }

    /**
     * Customises the default JavaFX stage.
     */
    private void customiseStage(Stage stage) {
        stage.setTitle("SUS");
        stage.setResizable(false);
        stage.setMinHeight(UI_HEIGHT);
        stage.setMinWidth(UI_WIDTH);
    }

    private void showBotMessage(MessageType messageType, String message) {
        VBox wrapper = new VBox();
        wrapper.setMaxWidth(600);
        wrapper.getChildren().add(DialogBox.getBotDialog(message, messageType.getTextColour()));
        wrapper.setBackground(new Background(
                new BackgroundFill(
                        messageType.getBackgroundColour(),
                        new CornerRadii(20),
                        new Insets(10, 50, 10, 70)
        )));
        dialogContainer.getChildren().add(wrapper);
    }

    private void showUserMessage(String message) {
        VBox wrapper = new VBox();
        wrapper.setMaxWidth(600);
        wrapper.getChildren().add(DialogBox.getUserDialog(message, MessageType.USER.getTextColour()));
        wrapper.setAlignment(Pos.CENTER_RIGHT);
        wrapper.setBackground(new Background(
                new BackgroundFill(
                        MessageType.USER.getBackgroundColour(),
                        new CornerRadii(20),
                        new Insets(10, 70, 10, 50)
                )));
        dialogContainer.getChildren().add(wrapper);
    }

    private void showTimedExitMessage() {
        showBotMessage(MessageType.EXIT, Messages.MESSAGE_EXIT);
        TimerTask exitApp = new TimerTask() {
            public void run() {
                System.exit(0);
            }
        };
        new Timer().schedule(exitApp, new Date(System.currentTimeMillis() + 3 * 1000));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        final String userCommand = userInput.getText();
        final Command command = new Parser().parseCommand(userCommand);
        final CommandResult result = command.execute(taskList, textUi, storage);
        final String output = result.getCommandResult();

        showUserMessage(userCommand);
        userInput.clear();

        if (command.isExit()) {
            showTimedExitMessage();
            return;
        }

        if (command instanceof InvalidCommand) {
            showBotMessage(MessageType.INVALID, output);
            return;
        }

        showBotMessage(MessageType.SUCCESS, output);
    }
}
