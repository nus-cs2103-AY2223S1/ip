package dukeprogram.userinterface;

import java.util.Optional;

/**
 * An association class representing how Duke returned a response to the application
 */
public class DukeResponse {

    private final String text;
    private final Optional<Widget> optionalWidget;

    /**
     * Creates a Duke response
     * @param text the text that Duke would say
     * @param widget an optional widget to represent additional information,
     *               if null is passed, then nothing will be rendered
     */
    public DukeResponse(String text, Widget widget) {
        this.text = text;
        optionalWidget = widget != null ? Optional.of(widget) : Optional.empty();
    }

    public DukeResponse(String text) {
        this(text, null);
    }

    /**
     * Creates a dialog box for this response
     * @return a dialog box
     */
    public DialogBox createDialogBox() {
        return optionalWidget
                .map(widget -> DialogBox.ofDuke(text, widget))
                .orElseGet(() -> DialogBox.ofDuke(text));
    }

    @Override
    public String toString() {
        return optionalWidget.isPresent() ? text + " [Widget not shown]" : text;
    }
}
