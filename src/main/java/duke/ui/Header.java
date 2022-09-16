package duke.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * This class encapsulates the header of the chatbot, where
 * the chatbot's name is displayed
 */
public class Header extends VBox {

    @FXML
    private Label name;

    /**
     * Constructs the header for the chatbot
     * @param name
     */
    public Header(String name) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/Header.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.name.setText(name);
    }


}
