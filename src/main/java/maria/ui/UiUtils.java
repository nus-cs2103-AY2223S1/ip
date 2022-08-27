package maria.ui;

import javafx.scene.control.Control;

/**
 * Provides the utilities for the user interface.
 */
public class UiUtils {

    /**
     * Changes the control to have a red tint and highlights the control.
     * @param control The control (eg. Button, TextField etc.)
     */
    public static void highlightControlError(Control control) {
        control.setStyle("-fx-focus-color:rgba(255, 0, 0);"); // Change outline to red
        control.requestFocus();
    }

}
