import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String name = "Duke";
        System.out.println("Hello! I'm " + name + "\nHow can I help you?");

        String[] tasks = new String[100];
        int currIndex = 0;

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                System.out.println("Bye! See you again :)");
                return;
            }
            if (command.equals("list")) {
                for (int i = 0; i < currIndex; i++) {
                    System.out.println(i + 1 + ". " + tasks[i]);
                }
            }
            else {
                tasks[currIndex++] = command;
                System.out.println("added: " + command);
            }
        }

    }
}
