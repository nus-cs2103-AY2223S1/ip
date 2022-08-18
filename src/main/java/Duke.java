import java.util.Locale;
import java.util.Scanner;
public class Duke {
    private static final String greetMessage = "Hello! I'm Duke \nWhat can I do for you?";
    private static final String byeMessage = "Bye. Hope to see you again soon!";
    private static void wrapText(String content) {
        System.out.println("-".repeat(57));
        System.out.println(content);
        System.out.println("-".repeat(57));
    }

    private static void handleUserInputs(Scanner scanner) {
        while(scanner.hasNext()) {
            String[] inputs = scanner.nextLine().split(" ");
            String input = inputs[0];
            if (input.equals("bye")) {
                wrapText(byeMessage);
                break;
            } else {
                wrapText(input);
            }
        }
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        wrapText(greetMessage);
        Scanner scanner = new Scanner(System.in);
        handleUserInputs(scanner);
    }
}
