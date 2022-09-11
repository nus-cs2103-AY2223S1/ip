package ado.gui;

import java.io.IOException;

import ado.Ado;
import ado.Response;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;



/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    @FXML
    private Button helpButton;

    private Ado ado;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser_cat_image.png"));
    private Image adoImage = new Image(this.getClass().getResourceAsStream("/images/Ado.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setAdo(Ado a) {
        ado = a;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Ado's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Response response = ado.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAdoDialog(response, adoImage)
        );
        userInput.clear();
    }

    /**
     * Creates a help window
     */
    @FXML
    private void handleHelpButton(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/HelpWindow.fxml"));
        AnchorPane ap = fxmlLoader.load();
        Scene scene = new Scene(ap);

        Stage stage = new Stage();
        stage.setTitle("Help");
        stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/help_icon.png")));
        stage.setResizable(false);
        stage.sizeToScene();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    /**
     * Prints out welcome message on GUI
     */
    public void showWelcome() {
        Response welcome = ado.getWelcome();
        dialogContainer.getChildren().add(
                DialogBox.getAdoDialog(welcome, adoImage)
        );
    }
}
