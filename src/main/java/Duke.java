import java.util.Scanner;

public class Duke {
    private static final String line = "____________________________________________________________";
    private static final String indentedLine = "     " + line;
    private static final String initialMessage = messageWithIndentedLines("Hello! I'm Duke\n      What can I do for you?");
    private static final String byeMessage = messageWithIndentedLines("Bye. This doesn't have to be the end!");

    public static String messageWithIndentedLines(String message) {
        return indentedLine + "\n      " + message + "\n" + indentedLine;
    }

    public static void main(String[] args) {
        System.out.println(initialMessage);
        Scanner scanner = new Scanner(System.in);
        // the user has to at least input bye to exit
        String userInput = scanner.nextLine();
        while (true) {
            if (userInput.equals("bye")) {
                System.out.println(byeMessage);
                break;
            }
            System.out.println(messageWithIndentedLines(userInput));
            userInput = scanner.nextLine();
        }
    }
}
