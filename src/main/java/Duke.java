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
        checkInput(s.next());
    }

    void checkInput(String inputString) {
        if (inputString.equals("bye")) {
            System.out.println("Goodbye, see you soon!" + divider);
            return;
        } else if (inputString.equals("list")) {
            System.out.println(itemList.getList() + divider);
        } else {
            String output = itemList.addItem(inputString);
            if (!output.equals("")) {
                System.out.println(output + divider);
            }
        }
        checkInput(s.nextLine());
    }

    public static void main(String[] args) {
        Duke instance = new Duke();
        instance.run();
    }
}
