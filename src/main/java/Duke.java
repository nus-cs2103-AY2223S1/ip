import java.util.Scanner;

public class Duke {

    private static String line = "____________________________________________________________\n";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String logo = line
                + " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n"
                + "\nWhat can I do for you?\n"
                + line;
        System.out.println("Hello from\n" + logo);

        String text = "";

        do {
            text = scanner.nextLine();

            System.out.println(line);
            switch (text) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                default:
                    System.out.println(text);
                    break;
            }
            System.out.println(line);
        } while (!text.equals("bye"));
    }
}
