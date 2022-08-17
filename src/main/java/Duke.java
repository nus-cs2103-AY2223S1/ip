import java.util.Scanner;

public class Duke {
    Scanner s;
    static final String intro =
        "\nWelcome to Apollo.\nHow can I help you today?";

    static final String newIntro =
        "┌────────────────────┐\n" +
            "│ Welcome to APOLLO! │\n" +
        "└────────────────────┘\n" +
            "How can I help you today?";


    public Duke() {
        s = new Scanner(System.in);
    }

    void run() {
        System.out.println(newIntro);
        checkInput(s.next());
    }

    void checkInput(String inputString) {
        if (inputString.equals("bye")) {
            System.out.println("Goodbye, see you soon!");
        } else {
            System.out.println(inputString);
            checkInput(s.next());
        }
    }

    public static void main(String[] args) {
        Duke instance = new Duke();
        instance.run();
    }
}
