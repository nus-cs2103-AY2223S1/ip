package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Duke extends Application {

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke() {
        this.storage = new Storage("./data/duke.txt");
        this.tasks = new TaskList();
        this.ui = new Ui();
        this.parser = new Parser();
    }

    @Override
    public void start(Stage stage) {
        try {
            this.tasks = new TaskList(storage.load(ui));
        } catch (DukeException e) {
            this.tasks = new TaskList();
            this.tasks.createFile();
        }
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        stage.setTitle("Baymax");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(500.0);

        mainLayout.setPrefSize(500.0, 600.0);

        scrollPane.setPrefSize(485, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(425.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);


        ImageView dukeImage = new ImageView(duke);

        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(new Label(this.ui.welcomeMessage() + "\n" +
                        ui.tasksLoadedMsg(tasks.size())), dukeImage)
        );

        sendButton.setOnMouseClicked(event -> handleUserInput());

        userInput.setOnAction((event) -> handleUserInput());
    }

    private String getResponse(String input) {
        if (input.equals("bye")) {
            tasks.addTasksToFile();
            return ui.exitMessage();
        } else {
            return parser.commandParser(input, tasks, ui);
        }
    }


    private void handleUserInput() {
        Label userText = new Label(userInput.getText() + "\t");
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }
}
