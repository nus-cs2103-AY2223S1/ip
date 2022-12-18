package dukeprogram.userinterface;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

/**
 * WidgetLabel defines a label for a task that is to be fitted into a widget
 */
public class WidgetTaskLabel extends HBox {

    private final VBox taskInfoBox;

    /**
     * Creates a new WidgetLabel
     * @param taskType the type of task
     * @param taskName the text to display into the label
     * @param style the TextStyle to render the text as
     */
    public WidgetTaskLabel(String taskType, String taskName, TextStyle style, boolean isComplete) {
        super();
        setSpacing(5);
        setFillHeight(true);
        Label typeLabel = createTaskInfoLabel(taskType, TextStyle.Header, Color.HOTPINK);

        taskInfoBox = new VBox(3);
        Label taskLabel = createTaskNameLabel(taskName, style);

        this.setPadding(new Insets(5, 5, 5, 5));
        this.setBackground(new Background(new BackgroundFill(
                Color.DARKVIOLET,
                new CornerRadii(5),
                Insets.EMPTY
        )));
        this.setStyle("-fx-border-width: 0 0 1 0; -fx-border-radius: 5; -fx-border-color: #002654;");
        taskInfoBox.getChildren().add(typeLabel);

        CheckBox checkBox = new CheckBox();
        checkBox.getStylesheets().add("css/CheckBox.css");
        checkBox.setDisable(true);
        checkBox.disarm();
        checkBox.setSelected(isComplete);

        getChildren().addAll(taskInfoBox, taskLabel, checkBox);
        setHgrow(taskLabel, Priority.ALWAYS);
    }

    private static Label createTaskNameLabel(String taskName, TextStyle style) {
        Label taskLabel = new Label(taskName);
        taskLabel.getStyleClass().add(style.label);
        taskLabel.setWrapText(true);
        taskLabel.setMaxWidth(Double.MAX_VALUE);
        taskLabel.setAlignment(Pos.CENTER_LEFT);
        taskLabel.setMaxHeight(Double.MAX_VALUE);
        taskLabel.setPadding(new Insets(5, 10, 5, 10));
        return taskLabel;
    }

    private static Label createTaskInfoLabel(String infoString, TextStyle style, Color color) {
        Label typeLabel = new Label(infoString);
        typeLabel.getStyleClass().add(style.label);
        typeLabel.setWrapText(true);
        typeLabel.setPadding(new Insets(5, 10, 5, 10));
        typeLabel.setAlignment(Pos.CENTER);
        typeLabel.setTextAlignment(TextAlignment.CENTER);
        typeLabel.setMinWidth(120);
        typeLabel.setBackground(new Background(new BackgroundFill(
                color,
                new CornerRadii(20),
                Insets.EMPTY
        )));
        return typeLabel;
    }

    public void addInfoLabel(String s) {
        taskInfoBox.getChildren().addAll(createTaskInfoLabel(s, TextStyle.Tag, Color.DEEPPINK));
    }
}
