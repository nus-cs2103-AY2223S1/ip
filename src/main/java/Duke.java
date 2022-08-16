import java.util.Scanner;

public class Duke {
    private static final String line = "____________________________________________________________";
    private static void printResponse(String response) {
        System.out.println(Duke.line);
        System.out.println(response);
        System.out.println(Duke.line);
    }
    public static void main(String[] args) {
        Scanner scanner  = new Scanner(System.in);

        String greetingMessage = "Hello! I'm Duke\nWhat can I do for you?";
        String exitMessage = "Bye. Hope to see you again soon!";
        String exitKeyword = "bye";
        String userInput;

        printResponse(greetingMessage);

        while(true) {
            userInput = scanner.nextLine();
            if (userInput.equals(exitKeyword)) {
                printResponse(exitMessage);
                break;
            }
            printResponse(userInput);
        }
    }
}
