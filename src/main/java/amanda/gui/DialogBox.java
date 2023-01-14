package amanda.gui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {

	private static final Background amandaBg = new Background(new BackgroundFill(Paint.valueOf("FFF8DC"),
			CornerRadii.EMPTY, Insets.EMPTY));
	private static final Background userBg = new Background(new BackgroundFill(Paint.valueOf("CDCDCD"),
			CornerRadii.EMPTY, Insets.EMPTY));

	@FXML
	private Label dialog;
	@FXML
	private ImageView displayPicture;
	@FXML
	private Circle clip = new Circle(50, 50, 50);

	private DialogBox(String text, Image img) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
			fxmlLoader.setController(this);
			fxmlLoader.setRoot(this);
			fxmlLoader.load();
			this.setSpacing(50);
		} catch (IOException e) {
			e.printStackTrace();
		}

		dialog.setText(text);
		dialog.setFont(Font.font("Times New Roman",15));
		displayPicture.setImage(img);
	}

	/**
	 * Flips the dialog box such that the ImageView is on the left and text on the right.
	 */
	private void flip() {
		ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
		Collections.reverse(tmp);
		getChildren().setAll(tmp);
		setAlignment(Pos.TOP_LEFT);
	}

	public static DialogBox getUserDialog(String text, Image img) {
		var db = new DialogBox(text, img);
		db.displayPicture.setClip(db.clip);
		db.setBackground(userBg);
		return new DialogBox(text, img);
	}

	public static DialogBox getAmandaDialog(String text, Image img) {
		var db = new DialogBox(text, img);
		db.displayPicture.setClip(db.clip);
		db.flip();
		db.setBackground(amandaBg);
		return db;
	}

}