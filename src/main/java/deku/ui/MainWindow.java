package deku.ui;

import deku.Deku;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;

/**
 * Setup for main display window for JavaFX
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private ScrollPane listPane;
    @FXML
    private VBox listContainer;

    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Deku deku;

    // Image taken from https://pixabay.com/vectors/person-individually-alone-icon-1824144/
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));

    // Image taken from https://pixabay.com/vectors/robot-icon-flat-flat-design-2192617/
    private Image dekuImage = new Image(this.getClass().getResourceAsStream("/images/bot.png"));

    //image taken from https://unsplash.com/photos/zA7I5BtFbvw
    private Image backgroundImage = new Image(this.getClass().getResourceAsStream("/images/background.jpg"));
    private Image backgroundImageFlipped = new Image(this.getClass()
            .getResourceAsStream("/images/backgroundFlipped.jpg")
    );
    private Label previousNode;

    /**
     * Initializes the dialog panel and active list panel
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        listPane.vvalueProperty().bind(listContainer.heightProperty());
        dialogContainer.setBackground(getBackground(backgroundImage));
        listContainer.setBackground(getBackground(backgroundImageFlipped));
    }


    // adapted from
    // https://stackoverflow.com/questions/54876509/how-to-fit-the-background-size-into-the-window-size-in-javafx
    private Background getBackground(Image background) {
        BackgroundImage bImg = new BackgroundImage(background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(1.0,
                        1.0,
                        true,
                        true,
                        false,
                        false
                )
        );

        return new Background(bImg);
    }

    /**
     * Sets the logic for the input Deku class.
     *
     * @param d A new instance for Deku bot class
     */
    public void setDeku(Deku d) {
        deku = d;
        updateList(deku.getList());
    }

    /**
     * Refreshes the list panel on the right whenever user inputs a
     * command.
     *
     * @param list Updated list to display to the screen
     */
    public void updateList(String list) {
        if (previousNode != null) {
            listContainer.getChildren().remove(previousNode);
        }
        Label listDisplay = new Label(list);
        listDisplay.setWrapText(true);
        listContainer.getChildren().add(listDisplay);
        previousNode = listDisplay;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws InterruptedException {
        String input = userInput.getText();
        String response = deku.getResponse(input);
        if (response.contains("Bye!")) {
            Platform.exit();
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dekuImage)
        );
        updateList(deku.getList());
        userInput.clear();
    }
}
