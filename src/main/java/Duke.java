import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static final String GREETING =
            "##################################################\n" +
                    "||                                              ||\n" +
                    "||                Hello! I'm Ted                ||\n" +
                    "||            What can I do for you?            ||\n" +
                    "||                                              ||\n" +
                    "##################################################";

    private static final String INPUT_PREFIX = "> ";

    ArrayList<String> tasks = new ArrayList<String>();

    private void list() {
        for (int inputIndex = 0; inputIndex < tasks.size(); inputIndex++) {
            System.out.printf("%d. %s\n", inputIndex + 1, tasks.get(inputIndex));
        }
    }

    private void add(String input) {
        this.tasks.add(input);
        System.out.printf("Added: %s\n", input);
    }

    private void run() {
        System.out.println(GREETING);

        Scanner scanner = new Scanner(System.in);
        System.out.print(INPUT_PREFIX);

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            String command = input.split(" ")[0];

            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again!");
                break;
            } else if (command.equals("list")) {
                this.list();
            } else {
                this.add(input);
            }

            System.out.print(INPUT_PREFIX);
        }

        scanner.close();
    }

    public static void main(String[] args) {
        Duke bot = new Duke();
        bot.run();
    }
}
