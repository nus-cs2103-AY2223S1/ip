package dukeprogram.userinterface;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


/**
 * A Widget that is optionally nested in a Dialog Box, showing additional information
 */
public class Widget extends Pane {

    @FXML
    private Label titleLabel;

    @FXML
    private Label descriptionLabel;


    /**
     * Creates a widget
     */
    public Widget(String title, String description) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/Widget.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setBackground(new Background(new BackgroundFill(
                Color.color(0.3, 0.3, 0.3),
                new CornerRadii(3),
                new Insets(5, 5, 5, 5)))
        );

        titleLabel.setText(title);
        descriptionLabel.setText(description);
    }
}
