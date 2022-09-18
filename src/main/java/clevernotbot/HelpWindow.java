package clevernotbot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URI;

public class HelpWindow extends AnchorPane {
    @FXML
    public void initialize(){}
    @FXML
    private ScrollPane scrollPane;


    @FXML
    public void visitButtonAction() {
        try {
            Desktop.getDesktop().browse(new URI("https://lolfoollors.github.io/ip/"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void handleCloseButtonAction(ActionEvent event) {
        Stage stage = (Stage) scrollPane.getScene().getWindow();
        stage.close();
    }
}
