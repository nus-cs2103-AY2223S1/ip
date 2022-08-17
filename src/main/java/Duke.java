import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private Scanner sc;
    private static String botName = "DIGITAL DADDY";
    private static String emoji = "\uD83E\uDD16";
    private List<String> list = new ArrayList<>();

    Duke(Scanner sc) {
        this.sc = sc;
    }

    private static void botReply(String input) {
        String lineSeparator = "____________________________________________________________";
        String reply = String.format("%s\n%s %s %s \n%s \n%s", lineSeparator, emoji, botName, emoji, input, lineSeparator);
        System.out.println(reply);
    }

    private String listToString(List<String> list) {
        if (list.isEmpty()) {
            return "You haven't added anything to your list!";
        }

        String listString = "";

        for (int index = 1; index <= list.size(); index++) {
            String listItem = list.get(index - 1);
            String listItemString = index + ". " + listItem;
            if (index != list.size()) {
                listItemString += "\n";
            }
            listString += listItemString;
        }

        return listString;
    }

    public void start() {
        botReply("Hello! I'm " + botName + "\nWhat can I do for you?");
        while (true) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                botReply("Bye. Hope to see you again soon!");
                break;
            }

            if (input.equals("list")) {
                botReply(this.listToString(this.list));
                continue;
            }

            this.addToList(input);
        }
    }

    private void addToList(String item) {
        this.list.add(item);
        botReply("added: " + item);
    }

    public static void main(String[] args) {
        // Create a scanner to read from standard input.
        Scanner sc = new Scanner(System.in);

        Duke duke = new Duke(sc);
        duke.start();

        sc.close();
    }


}
