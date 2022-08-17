import java.util.Scanner;

public class Duke {
    private final Scanner s;
    private static final String intro =
        "┌────────────────────┐\n" +
            "│ Welcome to APOLLO! │\n" +
            "└────────────────────┘\n" +
            "How can I help you today?";
    public static final String divider = "\n──────────────────";
    private final DukeList itemList;

    public Duke() {
        s = new Scanner(System.in);
        itemList = new DukeList();
    }

    void run() {
        System.out.println(intro);
        System.out.println(divider);
        checkInput(s.nextLine());
    }

    void checkInput(String inputString) {
        String[] input = inputString.split(" ");
        String output = "";
        if (input[0].equals("bye")) {
            System.out.println("Goodbye, see you soon!" + divider);
            return;
        } else if (input[0].equals("list")) {
            output = itemList.toString();
        } else if (input[0].equals("mark")) {
            output = itemList.mark(Integer.parseInt(input[1]));
        } else if (input[0].equals("unmark")) {
            output = itemList.unmark(Integer.parseInt(input[1]));
        } else {
            output = itemList.addItem(input);
        }
        System.out.println(output + divider);
        checkInput(s.nextLine());
    }

    public static void main(String[] args) {
        Duke instance = new Duke();
        instance.run();
    }
}
