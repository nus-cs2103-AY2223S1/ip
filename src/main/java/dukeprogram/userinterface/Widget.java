package dukeprogram.userinterface;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;


/**
 * A Widget that is optionally nested in a Dialog Box, showing additional information
 */
public class Widget extends VBox {

    @FXML
    private Label titleLabel;

    @FXML
    private Label descriptionLabel;


    /**
     * Creates a widget
     * @param elements the elements to embed inside the widget
     */
    public Widget(Region... elements) {
        this(List.of(elements));
    }

    /**
     * Creates a widget
     * @param elements the elements to embed inside the widget
     */
    public Widget(Collection<Region> elements) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/Widget.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        setFillWidth(true);
        elements.forEach(e -> e.setMaxWidth(Double.MAX_VALUE));
        getChildren().addAll(elements);
    }
}
