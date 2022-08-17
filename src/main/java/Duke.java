import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String name = "Duke";
        System.out.println("Hello! I'm " + name + "\nHow can I help you?");

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                System.out.println("Bye! See you again :)");
                return;
            }
            System.out.println(command);
        }

    }
}
