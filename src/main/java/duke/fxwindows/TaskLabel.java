package duke.fxwindows;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import static java.lang.Double.MAX_VALUE;

public class TaskLabel extends Label {
    public TaskLabel(String str) {
        super(str);

        // CSS Classes
        this.getStyleClass().add("label-skin");

        this.setMaxWidth(MAX_VALUE);
        VBox.setVgrow(this, Priority.ALWAYS);
        this.setAlignment(Pos.CENTER_LEFT);
        this.setPadding(new Insets(5, 30, 0, 10));
    }
}
