package duke.gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * Control for a dialog box using FXML.
 * Consists of a display image and name for speaker, and text for the dialog.
 */
public abstract class DialogBox extends HBox {

    @FXML
    protected Label name;
    @FXML
    protected Text dialog;
    @FXML
    protected ImageView displayImage;

    /**
     * Initializes this dialog box. Uses FXMLLoader to create a dialog box and sets the bindings.
     *
     * @param fxmlLoaderPath Path for the FXML Loader.
     */
    protected void initialize(String fxmlLoaderPath) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(fxmlLoaderPath));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
