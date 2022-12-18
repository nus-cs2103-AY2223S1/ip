package dukeprogram.userinterface;

import java.util.Optional;

/**
 * An association class representing how Duke returned a response to the application
 */
public class DukeResponse {

    private final String text;
    private final TextStyle style;
    private final Optional<Widget> optionalWidget;

    /**
     * Creates a Duke response
     * @param text the text that Duke would say
     * @param widget an optional widget to represent additional information,
     *               if null is passed, then nothing will be rendered
     * @param style the text style to render the text in
     */
    public DukeResponse(String text, Widget widget, TextStyle style) {
        this.text = text;
        this.style = style;
        optionalWidget = widget != null ? Optional.of(widget) : Optional.empty();
    }

    public DukeResponse(String text, TextStyle style) {
        this(text, null, style);
    }

    /**
     * Creates a dialog box for this response
     * @return a dialog box
     */
    public DialogBox createDialogBox() {
        return optionalWidget
                .map(widget -> DialogBox.ofDuke(text, style, widget))
                .orElseGet(() -> DialogBox.ofDuke(text, style));
    }

    @Override
    public String toString() {
        return optionalWidget.isPresent() ? text + " [Widget not shown]" : text;
    }
}
