import java.util.Scanner;

public class BobTheBot {
    public static void main(String[] args) {
        ToDoList list = new ToDoList();
        System.out.println("Hello! I am Bob the Bot, your friendly task manager! \n" +
                           "What can I help you with?");

        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        while (!command.toLowerCase().equals("bye")) {
            if (command.toLowerCase().equals("list")) {
                System.out.println(list.toString());
            } else {
                list.add(command);
                System.out.println(
                        "   --------------------------------------------------------------------------------\n" +
                                "     added: " + command + "\n" +
                                "   --------------------------------------------------------------------------------"
                );
            }
            command = scanner.nextLine();
        }

        System.out.println(
                "   --------------------------------------------------------------------------------\n" +
                        "     Bye! Hope to see you again soon! \n" +
                        "   --------------------------------------------------------------------------------"
        );
    }
}
