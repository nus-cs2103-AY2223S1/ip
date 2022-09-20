package dukeprogram.userinterface;

import dukeprogram.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;


/**
 * A widget button is a button that occurs within a widget, part of a dialog layout.
 */
public class WidgetButton extends Button {

    /**
     * Constructs a new WidgetButton
     * @param label the label this button bears
     * @param eventHandler the event to trigger once the button is pressed
     */
    public WidgetButton(String label, EventHandler<ActionEvent> eventHandler) {
        super(label);
        getStyleClass().add("header");

        String styleDefault = "-fx-background-color: #FF1493; -fx-background-radius: 5px;";
        String styleHover = "-fx-background-color: #FF69B4; -fx-background-radius: 5px;";

        setStyle(styleDefault);
        setOnMouseEntered(e -> setStyle(styleHover));
        setOnMouseExited(e -> setStyle(styleDefault));

        setMinHeight(40);
        setOnAction(eventHandler);
    }
}
