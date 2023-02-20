package dukeprogram.userinterface;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

/**
 * WidgetLabel defines a label for a task that is to be fitted into a widget
 */
public class WidgetLoanLabel extends HBox {

    /**
     * Creates a new WidgetLoanLabel
     * @param creditorName the name of the creditor
     * @param amountOwed the signed amount of money owed
     */
    public WidgetLoanLabel(String creditorName, double amountOwed) {
        super();
        setAlignment(Pos.CENTER_LEFT);
        setSpacing(5);
        setFillHeight(true);

        Label nameLabel = createNameLabel(creditorName);
        Label standing = creatingStandingLabel(amountOwed);
        Label amountLabel = createAmountLabel(amountOwed);

        Region spaceBetweenNameAndStanding = new Region();

        getChildren().addAll(nameLabel, spaceBetweenNameAndStanding, standing, amountLabel);
        setHgrow(nameLabel, Priority.ALWAYS);
        setHgrow(spaceBetweenNameAndStanding, Priority.ALWAYS);
    }

    private Label createNameLabel(String creditorName) {
        Label nameLabel = new Label(creditorName);
        nameLabel.setBackground(new Background(new BackgroundFill(
                Color.DARKVIOLET,
                new CornerRadii(5),
                Insets.EMPTY
        )));
        nameLabel.setPadding(new Insets(5, 15, 5, 15));
        nameLabel.setAlignment(Pos.CENTER_LEFT);
        nameLabel.getStyleClass().add(TextStyle.Header.label);
        nameLabel.setWrapText(true);
        nameLabel.setMaxHeight(Double.MAX_VALUE);
        return nameLabel;
    }

    private Label createAmountLabel(double amountOwed) {
        Label amountLabel = new Label(String.format("$%.2f", Math.abs(amountOwed)));
        amountLabel.setMinWidth(80);
        amountLabel.setAlignment(Pos.CENTER_LEFT);
        amountLabel.getStyleClass().add(TextStyle.Header.label);
        return amountLabel;
    }

    private Label creatingStandingLabel(double amountOwed) {
        Label standing;
        if (amountOwed > 0) {
            standing = new Label("Owed");
            standing.setBackground(new Background(new BackgroundFill(
                    Color.RED,
                    new CornerRadii(5),
                    Insets.EMPTY
            )));
        } else if (amountOwed < 0) {
            standing = new Label("Lent");
            standing.setBackground(new Background(new BackgroundFill(
                    Color.GREEN,
                    new CornerRadii(5),
                    Insets.EMPTY
            )));
        } else {
            standing = new Label("Cleared");
            standing.setBackground(new Background(new BackgroundFill(
                    Color.GREY,
                    new CornerRadii(5),
                    Insets.EMPTY
            )));
        }
        standing.setAlignment(Pos.CENTER);
        standing.setPadding(new Insets(5, 5, 5, 5));
        standing.getStyleClass().add("tag");
        standing.setMinWidth(50);
        return standing;
    }
}
