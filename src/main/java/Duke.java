import java.util.Scanner;

public class Duke {
    private static final String startUpMessage = "Hello! I'm Duke\n"
                                        + "What can I do for you?";
    private static final String exitMessage = "Bye. Hope to see you again soon!";
    private static final String exitCmd = "bye";
    private static final String logo =
              " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static void main(String[] args) {
        System.out.println(startUpMessage);
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        while (!userInput.equals(exitCmd)) {
            System.out.println(userInput);
            userInput = sc.nextLine();
            System.out.println("Message changed to " + userInput);
        }
        System.out.println(exitMessage);
    }
}
