import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String GREET_MESSAGE = "Hello! I am Duke! \n" + "What can I do for you?";
    private static final String EXIT_MESSAGE = "Bye! Hope to see you again soon!";

    private static void printMessage(String message) {
        System.out.println(">> " + message);
    }

    private static void greet() {
        Duke.printMessage("Hello from\n" + LOGO);
        Duke.printMessage(GREET_MESSAGE);
    }

    private static String getUserInput(Scanner sc) {
        System.out.print("<< ");
        return sc.nextLine();
    }

    private static void run() {
        Duke.greet();

        Scanner sc = new Scanner(System.in);
        List<String> tasks = new ArrayList<>();
        boolean terminated = false;

        while (!terminated) {
            String command = Duke.getUserInput(sc);

            switch (command.toLowerCase()) {
                case "list":
                    StringBuilder output = new StringBuilder();
                    output.append("Here are your tasks:\n");
                    for (int i = 0; i < tasks.size(); i++) {
                        output.append(String.format("\t %d. %s", i + 1, tasks.get(i)));
                        if (i + 1 < tasks.size()) {
                            output.append("\n");
                        }
                    }
                    Duke.printMessage(output.toString());
                    break;
                case "bye":
                    Duke.printMessage(EXIT_MESSAGE);
                    terminated = true;
                    break;
                default:
                    tasks.add(command);
                    Duke.printMessage("Added task: " + command);
            }
        }
    }

    public static void main(String[] args) {
        Duke.run();
    }
}
