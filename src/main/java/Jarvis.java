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

    private static Command parseUserCommand(Scanner sc) throws JarvisException {
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
            default:
                throw new JarvisException("Unrecognised. Please enter a valid command.");
        }
    }

    public static void run() {
        Jarvis.greet();

        Scanner sc = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();
        boolean terminated = false;

        while (!terminated) {
            try {
                Command command = Jarvis.parseUserCommand(sc);
                String keyCommand = command.getKeyCommand();

                if (keyCommand.equals("bye")) {
                    terminated = true;
                }

                Jarvis.printMessage(command.execute(tasks));
            } catch (JarvisException e) {
                Jarvis.printMessage(e.getMessage());
            }

        }
    }
}
