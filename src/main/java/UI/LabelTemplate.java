package UI;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 * encapsulates the Label template that
 * has been abstracted out from the UI
 */
public class LabelTemplate extends Label {
    /**
     * static method to return a "template" label
     * @param text text to input
     * @param color color of the border
     * @return returns a Label object
     */
    public static Label createMessageLabel(String text, Color color) {
        Label label = new Label(text);
        label.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 10));
        label.setWrapText(true);
        label.setPadding(new Insets(5,5,5,5));
        label.setBorder(new Border(new BorderStroke(color,
                BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(5))));
        return label;
    }

}
