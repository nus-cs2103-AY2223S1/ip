import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Jarvis {
    private static final String GREET_MESSAGE = "Hello! I am Jarvis! What can I do for you?";

    private static void printMessage(String message) {
        System.out.println(">> " + message);
    }

    private static void greet() {
        Jarvis.printMessage(GREET_MESSAGE);
    }

    private static Command parseUserCommand(Scanner sc) {
        System.out.print("<< ");
        String command = sc.nextLine();
        String[] arguments = command.trim().split(" ");
        switch(arguments[0]) {
            case "bye":
                return new ByeCommand(command);
            case "list":
                return new ListCommand(command);
            case "mark":
            case "unmark":
                return new MarkCommand(command);
            case "todo":
            case "deadline":
            case "event":
                return new AddCommand(command);
        }
        return null;
    }

    private static void run() {
        Jarvis.greet();

        Scanner sc = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();
        boolean terminated = false;

        while (!terminated) {
            Command command = Jarvis.parseUserCommand(sc);

            if (command == null) {
                Jarvis.printMessage("Invalid command.");
                continue;
            }

            String keyCommand = command.getKeyCommand();

            if (keyCommand.equals("bye")) {
                terminated = true;
            }

            Jarvis.printMessage(command.execute(tasks));
        }
    }

    public static void main(String[] args) {
        Jarvis.run();
    }
}
