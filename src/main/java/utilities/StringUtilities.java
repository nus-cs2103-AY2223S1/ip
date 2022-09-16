package utilities;

import java.util.Iterator;
import java.util.stream.IntStream;

import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

/**
 * StringUtilities provides utilities for strings and collections containing strings
 */
public class StringUtilities {

    private static final Text HELPER = new Text();
    private static final double DEFAULT_WRAPPING_WIDTH = HELPER.getWrappingWidth();
    private static final double DEFAULT_LINE_SPACING = HELPER.getLineSpacing();
    private static final String DEFAULT_TEXT = HELPER.getText();
    private static final TextBoundsType DEFAULT_BOUNDS_TYPE = HELPER.getBoundsType();

    /**
     * Helper method to split a String array by a delimiter.
     * If two delimiters sit adjacent, an empty array is returned.
     * @param input The string array to split
     * @param delimiter The string to split by
     * @return A nested array containing string arrays that are
     *      partitioned by the delimiter, excluding all instances of the delimiter
     */
    public static String[][] splitStringArray(String[] input, String delimiter) {
        if (input.length == 0) {
            return new String[0][];
        }

        int splitStart = 0;
        int splitEnd = input.length - 1;

        while (input[splitStart].equals(delimiter)) {
            splitStart++;
        }
        while (input[splitEnd].equals(delimiter)) {
            splitEnd--;
        }

        int[] splitIndices = IntStream
                .range(splitStart, splitEnd + 1)
                .filter(i -> input[i].equals(delimiter))
                .toArray();

        String[][] splitArrays = new String[
                (int) IntStream
                .range(0, splitIndices.length)
                .filter(i -> i == 0 || splitIndices[i] - 1 != splitIndices[i - 1])
                .count() + 1][];

        int startingIndex = splitStart;
        int j = 0;
        for (int splitIndex : splitIndices) {
            if (startingIndex != splitIndex) {
                splitArrays[j] = new String[splitIndex - startingIndex];
                System.arraycopy(input, startingIndex, splitArrays[j], 0, splitArrays[j].length);
                j++;
            }
            startingIndex = splitIndex + 1;
        }

        splitArrays[splitArrays.length - 1] = new String[splitEnd + 1 - startingIndex];
        System.arraycopy(input, startingIndex, splitArrays[splitArrays.length - 1],
                0, splitArrays[splitArrays.length - 1].length);

        return splitArrays;
    }

    /**
     * Concats an iterator into a single string separated by a delimiter for each element
     * @param iterator the iterator to concat
     * @param delimiter the delimiter between each element
     * @return the joined String
     */
    public static String concatByDelimiter(Iterator<String> iterator, String delimiter) {
        StringBuilder sb = new StringBuilder();

        while (iterator.hasNext()) {
            sb.append(iterator.next());
            if (iterator.hasNext()) {
                sb.append(delimiter);
            }
        }

        return sb.toString();
    }

    /**
     * Calculates the width of a text font
     * @param font the font of the text
     * @param text the text in the text field
     * @param buffer the amount of width to add to the final width
     * @return
     */
    public static double computeTextWidth(Font font, String text, double buffer) {
        HELPER.setText(text);
        HELPER.setFont(font);

        HELPER.setWrappingWidth(0.0D);
        HELPER.setLineSpacing(0.0D);
        double d = Math.min(HELPER.prefWidth(-1.0D), 0);
        HELPER.setWrappingWidth((int) Math.ceil(d));
        d = Math.ceil(HELPER.getLayoutBounds().getWidth());

        HELPER.setWrappingWidth(DEFAULT_WRAPPING_WIDTH);
        HELPER.setLineSpacing(DEFAULT_LINE_SPACING);
        HELPER.setText(DEFAULT_TEXT);
        return d + buffer;
    }
}
