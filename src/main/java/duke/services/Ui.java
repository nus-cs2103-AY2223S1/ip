package duke.services;

/**
 * Handles Duke's dialog
 */
public class Ui {
    /** What Duke will tell the user after a command */
    private static String reply;

    /**
     * Updates duke's reply to the given lines, separated by newline
     */
    public static void setReply(String[] lines) {
        StringBuilder replyBuilder = new StringBuilder();
        System.out.println("____________________________________________________________");
        for (String line : lines) {
            System.out.println(line);
            replyBuilder.append(line).append("\n");
        }
        System.out.println("____________________________________________________________\n");
        reply = replyBuilder.toString();
    }

    public static String getReply() {
        return reply;
    }
}
