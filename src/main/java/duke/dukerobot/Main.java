package duke.dukerobot;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import duke.dukerobot.Duke;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke("data/duke.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
//package duke.dukerobot;
//
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.ScrollPane;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.Region;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//
//import java.awt.*;
//
//public class Main extends Application {
//    private Image userPic = new Image(this.getClass().getResourceAsStream("/images/user.png"));
//    private Image dukePic = new Image(this.getClass().getResourceAsStream("/images/duke.png"));
//
//    private ScrollPane scrollPane;
//    private VBox dialogContainer;
//    private TextField userInput;
//    private Button sendButton;
//    private Scene scene;
//    private Duke duke = new Duke("data/duke.txt");
//
//
//    private void handleUserInput() {
//        Label userText = new Label(userInput.getText());
//        Label dukeText = new Label(duke.getResponse(userInput.getText()));
//        dialogContainer.getChildren().addAll(
//                DialogBox.getUserDialog(userText, new ImageView(userPic)),
//                DialogBox.getDukeDialog(dukeText, new ImageView(dukePic))
//        );
//        userInput.clear();
//    }
//
//
//    @Override
//    public void start(Stage stage) {
//        //The container for the content of the chat to scroll.
//        scrollPane = new ScrollPane();
//        dialogContainer = new VBox();
//        scrollPane.setContent(dialogContainer);
//
//        userInput = new TextField();
//        sendButton = new Button("Send");
//        AnchorPane mainLayout = new AnchorPane();
//        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
//        scene = new Scene(mainLayout);
//        stage.setScene(scene);
//        stage.setTitle("Duke");
//        stage.setResizable(false);
//        stage.setMinHeight(600.0);
//        stage.setMinWidth(400.0);
//        mainLayout.setPrefSize(400.0, 600.0);
//
//        scrollPane.setPrefSize(385, 535);
//        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//
//        scrollPane.setVvalue(1.0);
//        scrollPane.setFitToWidth(true);
//
//        // You will need to import `javafx.scene.layout.Region` for this.
//        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
//
//        userInput.setPrefWidth(325.0);
//
//        sendButton.setPrefWidth(55.0);
//
//        AnchorPane.setTopAnchor(scrollPane, 1.0);
//
//        AnchorPane.setBottomAnchor(sendButton, 1.0);
//        AnchorPane.setRightAnchor(sendButton, 1.0);
//
//        AnchorPane.setLeftAnchor(userInput , 1.0);
//        AnchorPane.setBottomAnchor(userInput, 1.0);
//        sendButton.setOnMouseClicked((event) -> {
//            handleUserInput();
//        });
//
//        userInput.setOnAction((event) -> {
//            handleUserInput();
//        });
//        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
//        stage.show();
//    }
//    private Label getDialogLabel(String text) {
//        // You will need to import `javafx.scene.control.Label`.
//        Label textToAdd = new Label(text);
//        textToAdd.setWrapText(true);
//        return textToAdd;
//    }
//}