//package duke.application;
//
//import duke.Duke;
//import duke.commands.*;
//import duke.parser.Parser;
//import javafx.application.Platform;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.fxml.FXML;
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.ScrollPane;
//import javafx.scene.control.TextField;
//import javafx.scene.image.Image;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.VBox;
//import javafx.stage.Modality;
//import javafx.stage.Stage;
//import javafx.stage.StageStyle;
//import javafx.stage.Window;
//
//import java.util.concurrent.Callable;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.FutureTask;
//
///**
// * Controller for MainWindow. Provides the layout for the other controls.
// */
//public class MainWindow extends AnchorPane {
//    @FXML
//    private ScrollPane scrollPane;
//    @FXML
//    private VBox dialogContainer;
//    @FXML
//    private TextField userInput;
//    @FXML
//    private Button sendButton;
//    @FXML
//    private Button start;
//
//    private Duke duke;
//
//    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
//    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
//
//    @FXML
//    public void initialize() {
//        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
//    }
//
//    public void setDuke(Duke d) {
//        duke = d;
//    }
//
//    /**
//     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
//     * the dialog container. Clears the user input after processing.
//     */
//    @FXML
//    private void handleUserInput() {
////        String input = userInput.getText();
////        final Command command = new Parser().parseCommand(input);
////        if ((command instanceof ListCommand) || (command instanceof HelpCommand) || (command instanceof ExitCommand) ||
////                (command instanceof InvalidCommand)) {
////            String response = duke.getResponse(input);
////            dialogContainer.getChildren().addAll(
////                    DialogBox.getUserDialog(input, userImage),
////                    DialogBox.getDukeDialog(response, dukeImage)
////            );
////            userInput.clear();
////        } else {
////            final String nextText;
////            String response = duke.getResponse(nextText);
////            dialogContainer.getChildren().addAll(
////                    DialogBox.getUserDialog(nextText, userImage),
////                    DialogBox.getDukeDialog(response, dukeImage)
////            );
////        }
//
//    }
//
//}