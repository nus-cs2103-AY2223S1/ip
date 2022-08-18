import java.util.Locale;
import java.util.Scanner;

public class Duke {
    private static String botName = "Duke";
    private static int lineLength = 80;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.printf("Hello, I'm %s\n", botName);
        System.out.println("What can I do for you?");

        Scanner scanner = new Scanner(System.in);

        boolean done = false;
        while (!done) {
            GenerateLine();
            String input = scanner.nextLine();
            GenerateLine();

            switch (input.toLowerCase()) {
                case "bye":
                    done = true;
                    break;
                default:
                    Echo(input);
            }
        }

        System.out.println("Goodbye.");
    }

    public static void GenerateLine() {
        System.out.println(String.format("%" + lineLength + "s", "").replace(" ", "-"));
    }

    public static void Echo(String message) {
        System.out.println(message);
    }
}
