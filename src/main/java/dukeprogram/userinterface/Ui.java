package dukeprogram.userinterface;

import java.util.Arrays;

/**
 * Maintains the organisation of strings
 */
public class Ui {
    private static final String DECORATOR = "--------------";

    /**
     * Formats the given texts under a banner, separating each text with
     * a new line
     * @param textToShow the text to show
     * @return the formatted version of texts;
     */
    public static String formatTextUnderDecorators(String... textToShow) {
        StringBuilder builder = new StringBuilder();
        builder.append(DECORATOR + "\n");
        Arrays.stream(textToShow).forEach(builder::append);
        builder.append(DECORATOR);
        return builder.toString();
    }
}
