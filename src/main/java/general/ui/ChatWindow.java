package general.ui;

import java.util.Arrays;
import java.util.Scanner;

/**
 * A {@code ChatWindow} formats messages as chat bubbles. There are two
 * types of messages: commands, which are sent by the user and appear on the left,
 * and responses, which are sent by the program and appear on the right.
 *
 * @author Richard Dominick
 */
public class ChatWindow {
    private final int windowWidth;
    private final Scanner sc;

    public ChatWindow(int windowWidth) {
        this.windowWidth = windowWidth;
        sc = new Scanner(System.in);
    }

    public void dispose() {
        sc.close();
    }

    public String prompt(String helperText) {
        System.out.print(helperText);
        return sc.nextLine().strip();
    }

    private static Text[] breakLines(Text data) {
        // Break strings at the next word after line length hits 30 characters
        return data.lines()
                .flatMap((s) -> Arrays.stream(s.split("(?<=\\G\\b?.{30,}\\s)")))
                .map(Text::strip)
                .toArray(Text[]::new);
    }

    public void printResponse(Text response) {
        final Text[] lines = breakLines(response);
        int maxLength = 3; // prevents negative count
        for (int i = 0; i < lines.length; i++) {
            if (maxLength < lines[i].length()) {
                maxLength = lines[i].length();
            }
        }
        final int paddingLength = windowWidth - maxLength - 4;
        StringBuilder sb = new StringBuilder(" ".repeat(paddingLength))
                .append("╭").append("─".repeat(maxLength + 2)).append("╮\n");
        // Pad lines right
        final String formatString = "%-" + maxLength + "s";
        for (int i = 0; i < lines.length; i++) {
            sb.append(" ".repeat(paddingLength))
                    .append("│ ").append(String.format(formatString, lines[i])).append(" │\n");
        }
        sb.append(" ".repeat(paddingLength))
                .append("╰").append("─".repeat(maxLength)).append("╮┬╯\n")
                .append(" ".repeat(windowWidth - 7)).append("MIA ╰╯ \n");
        System.out.printf(sb.toString());
    }

    public void printCommand(Text command) {
        final Text[] lines = breakLines(command);
        int maxLength = 3; // prevents negative count
        for (int i = 0; i < lines.length; i++) {
            if (maxLength < lines[i].length()) {
                maxLength = lines[i].length();
            }
        }
        StringBuilder sb = new StringBuilder("╭").append("─".repeat(maxLength + 2)).append("╮\n");
        // Pad lines right
        final String formatString = "%-" + maxLength + "s";
        for (int i = 0; i < lines.length; i++) {
            sb.append("│ ").append(String.format(formatString, lines[i])).append(" │\n");
        }
        sb.append("╰┬╭").append("─".repeat(maxLength)).append("╯\n")
                .append(" ╰╯ You").append(" ".repeat(maxLength - 3)).append("\n");
        System.out.printf(sb.toString());
    }
}
