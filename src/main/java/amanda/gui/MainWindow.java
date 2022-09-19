package amanda.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import amanda.Amanda;
import amanda.ui.Ui;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
	@FXML
	private ScrollPane scrollPane;
	@FXML
	private VBox dialogContainer;
	@FXML
	private TextField userInput;
	@FXML
	private Button sendButton;

	private Amanda amanda;

	private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
	private final Image amandaImage = new Image(this.getClass().getResourceAsStream("/images/amanda.png"));

	@FXML
	public void initialize() {
		scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
		dialogContainer.getChildren().add(
				DialogBox.getAmandaDialog("Amanda here. What do you want?", amandaImage));

		if (!Ui.getAmandaResponse().isEmpty()) {
			dialogContainer.getChildren().add(
					DialogBox.getAmandaDialog(Ui.getAmandaResponse(), amandaImage));
			Ui.resetResponse();
		}
	}

	public void setAmanda(Amanda a) {
		amanda = a;
	}

	/**
	 * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
	 * the dialog container. Clears the user input after processing.
	 */
	@FXML
	private void handleUserInput() {
		String input = userInput.getText();
		String response = amanda.getResponse(input);
		dialogContainer.getChildren().addAll(
				DialogBox.getUserDialog(input, userImage),
				DialogBox.getAmandaDialog(response, amandaImage)
		);
		userInput.clear();
		Ui.resetResponse();
	}
}