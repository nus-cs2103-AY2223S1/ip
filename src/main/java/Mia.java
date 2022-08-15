import java.util.Arrays;

public class Mia {
    public static void main(String[] args) {
        String logo = "┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n" +
                      "┃ You are talking to MIA... ┃\n" +
                      "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛";
        System.out.println(logo);
    }

    private static void printResponse(String response) {
        // Break strings at the next word after line length hits 30 characters
        final String[] lines = response.lines()
                .flatMap((s) -> Arrays.stream(s.split("(?<=\\G\\b?.{30,}\\s)")))
                .map(String::strip)
                .toArray(String[]::new);
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
        sb.append("╰").append("─".repeat(maxLength)).append("╮┬╯\n")
                .append(" ".repeat(maxLength-3)).append("MIA ╰╯ \n");
        System.out.printf(sb.toString());
    }
}
