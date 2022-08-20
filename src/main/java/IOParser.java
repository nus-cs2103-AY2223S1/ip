import org.apache.commons.text.WordUtils;

import java.util.Arrays;

public class IOParser {

    private static final String LINE = "──────────────────────────────────────────\n";

    /**
     * Pretty-prints an output string
     *
     * @param output The string representing the output.
     */
    public static void printMsg(String output) {
        String[] lines = output.split("\n");
        String newStr = Arrays.stream(lines).map(line ->
                        String.format("\t %s%s\n", line.replace(line.stripLeading(), ""),
                                WordUtils.wrap(line, 40, "\n\t ", false)))
                .reduce("", String::concat);
        System.out.printf("\t%s%s\t%s%n", LINE, newStr, LINE);
    }
}
