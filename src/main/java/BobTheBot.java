import java.util.Scanner;

public class BobTheBot {
    public static void main(String[] args) {
        System.out.println("Hello! I am Bob the Bot, your friendly task managing bot! \n" +
                           "What can I help you with?");

        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        while (!command.toLowerCase().equals("bye")) {
            System.out.println(
                    "   --------------------------------------------------------------------------------\n" +
                    "     " + command + "\n" +
                    "   --------------------------------------------------------------------------------"
            );
            command = scanner.nextLine();
        }

        System.out.println(
                "   --------------------------------------------------------------------------------\n" +
                "     Bye! Hope to see you again soon! \n" +
                "   --------------------------------------------------------------------------------"
        );
    }
}
