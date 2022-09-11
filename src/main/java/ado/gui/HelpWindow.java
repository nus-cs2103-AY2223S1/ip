package ado.gui;

import ado.Constants;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * Help window to show all commands
 */
public class HelpWindow extends AnchorPane {
    @FXML
    private Label helpMessage;

    @FXML
    public void initialize() {
        helpMessage.setText(Constants.HELP_DETAILED_MESSAGE);
    }
}
