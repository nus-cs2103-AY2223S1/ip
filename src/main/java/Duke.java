import java.util.Scanner;

public class Duke {
    private Scanner sc;
    private static String botName = "DIGITAL DADDY";
    private static String emoji = "\uD83D\uDE00";

    Duke(Scanner sc) {
        this.sc = sc;
    }

    private static void botReply(String input) {
        String lineSeparator = "____________________________________________________________";
        String reply = String.format("%s\n%s %s %s \n%s \n%s", lineSeparator, emoji, botName, emoji, input, lineSeparator);
        System.out.println(reply);
    }

    public void start() {
        botReply("Hello! I'm " + botName + "\nWhat can I do for you?");
        while (true) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                botReply("Bye. Hope to see you again soon!");
                break;
            }

            botReply(input);
        }
    }

    public static void main(String[] args) {
        // Create a scanner to read from standard input.
        Scanner sc = new Scanner(System.in);

        Duke duke = new Duke(sc);
        duke.start();

        sc.close();
    }


}
