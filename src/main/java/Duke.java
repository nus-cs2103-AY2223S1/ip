import java.util.Scanner;

public class Duke {

    private static final String ROW_INDENT = "    ";
    private static final String WORD_INDENT = " ";
    private static final String HORIZONTAL_LINE = "____________________________________________________________";

    private static final String WELCOME_MSG = "Hello! I'm Duke \n" + Duke.ROW_INDENT + Duke.WORD_INDENT + "What can I do for you?";
    private static final String BYE_MSG = "Bye. Hope to see you again soon!";

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    public void run() {
        boolean hasEnded = false;
        Scanner sc = new Scanner(System.in);

        greet();

        while (!hasEnded) {
            String userInput = getInput(sc);
            switch (userInput.toUpperCase()) {
                case "BYE":
                    goodBye();
                    hasEnded = true;
                    break;
                default:
                    printMessage(userInput);
            }
        }
    }

    public String getInput(Scanner sc) {
        System.out.println();
        return sc.nextLine();
    }

    public void greet() {
        printMessage(Duke.WELCOME_MSG);
    }

    public void goodBye() {
        printMessage(Duke.BYE_MSG);
    }

    public void printMessage(String message) {
        System.out.println(Duke.ROW_INDENT + Duke.HORIZONTAL_LINE);
        System.out.println(Duke.ROW_INDENT + Duke.WORD_INDENT + message);
        System.out.println(Duke.ROW_INDENT + Duke.HORIZONTAL_LINE);
    }
}
