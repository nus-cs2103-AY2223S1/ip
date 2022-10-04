package org.Olivia.IO;

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
import org.Olivia.Dispatchers.GuiEventDispatcher;

public class Gui {

    private Stage stage;
    private GuiEventDispatcher dispatcher;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user;
    private Image olivia;
    private AnchorPane mainLayout;

    public Gui(Stage stage, GuiEventDispatcher dispatcher){
        assert stage!=null;
        assert dispatcher!=null;
        this.stage=stage;
        this.dispatcher=dispatcher;
    }

    public void initialize(){
        this.initializeIcons();
        this.initializeScene();
        this.initializeStage();
        this.initializeInputs();
        this.setUpEventHandling();
    }

    private void initializeIcons(){
        user = GuiApplicationInitializer.getUserIcon();
        olivia = GuiApplicationInitializer.getOliviaIcon();
    }

    private void initializeScene(){
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        scrollPane.setStyle("-fx-background: #303030; -fx-border-color: #A0A0A0;");

        userInput = new TextField();
        sendButton = new Button("Send");

        mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);
    }

    /**
     * Can only be called after this.initializeScene()
     */
    private void initializeStage(){
        assert stage!=null;
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Olivia");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        assert mainLayout!=null;
        mainLayout.setPrefSize(400.0, 600.0);

        assert scrollPane!=null;
        scrollPane.setPrefSize(400, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        assert dialogContainer!=null;
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
    }

    private void initializeInputs(){
        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    private void setUpEventHandling(){
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        userText.setStyle("-fx-text-fill:WHITE");
        Label oliviaText = new Label(getResponse(userInput.getText()));
        oliviaText.setStyle("-fx-text-fill:WHITE");
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getOliviaDialog(oliviaText, new ImageView(olivia))
        );
        userInput.clear();
    }


    private String getResponse(String input) {
        try {
            return dispatcher.dispatchCommand(input);
        }
        catch (Exception e){
            return e.getMessage();
        }
    }
}
