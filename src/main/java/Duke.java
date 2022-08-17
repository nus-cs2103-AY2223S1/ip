import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private Scanner sc;
    private List<String> history;
    private static String NAME = "DoiMoiBot: ";
    private static String COLON = "added: ";

    public Duke() {
        sc = new Scanner(System.in);
        history = new ArrayList<>(100);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        String input;

        duke.greet();
        duke.storeInList();

        duke.farewell();
    }

    private void greet() {
        System.out.println(NAME + "Hello! I'm doimoibot\n" + "What can I do you for?");
    }

    private void farewell() {
        System.out.println(NAME + "Goodbye! See you soon!");
    }

    private void parrot(String input) {
        while (!input.equals("bye")) {
            System.out.println(NAME + input);
            input = this.getInput();
        }
    }

    private void storeInList() {
        String input;
        while (true) {
            input = this.getInput();
            if (input.equals("bye")) {
                break;
            }
            if (input.equals("list")) {
                this.printList();
                continue;
            }
            System.out.println(COLON + input);
            this.history.add(input);
        }

    }

    private void printList() {
        for (int i = 1; i < history.size() + 1; i++) {
            System.out.println(i + ": " + history.get(i - 1));
        }
    }

    private String getInput() {
        return this.sc.nextLine();
    }
}
