import java.util.Scanner;

public class Duke {
    private static final String indentation = "    ";
    private static final String horizontalLine = indentation + "____________________________________________________________";

    public static void main(String[] args) {
        System.out.println(horizontalLine);
        String logo = indentation + "____        _        \n"
                + indentation + "|  _ \\ _   _| | _____ \n"
                + indentation + "| | | | | | | |/ / _ \\\n"
                + indentation + "| |_| | |_| |   <  __/\n"
                + indentation + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(indentation + "Hello I'm\n" + logo);
        System.out.println(indentation + "What can I do for you?");
        System.out.println(horizontalLine);

        Scanner scanner = new Scanner(System.in);

        Task[] tasks = new Task[100];
        int index = 0;

        inputLoop:
        while (true) {
            String input = scanner.nextLine();

            System.out.println(horizontalLine);

            switch (input) {
                case "bye":
                    System.out.println(indentation + "Bye, hope to see you soon!");
                    break inputLoop;
                case "list":
                    for (int i = 0; i < index; i++) {
                        System.out.printf(indentation + "%d: %s\n", i + 1, tasks[i]);
                    }
                    break;
                default:
                    tasks[index++] = new Task(input);
                    System.out.println(indentation + "added: " + input);
            }

            System.out.println(horizontalLine);
        }
    }
}
