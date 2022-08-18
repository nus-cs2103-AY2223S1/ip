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

    ArrayList<Task> tasks = new ArrayList<Task>();

    private void list(String arg) {
        for (int inputIndex = 0; inputIndex < tasks.size(); inputIndex++) {
            System.out.printf("%d.%s\n", inputIndex + 1, tasks.get(inputIndex));
        }
    }

    private void add(String arg) {
        this.tasks.add(new Task(arg));
        System.out.printf("Added: %s\n", arg);
    }

    private void mark(String arg) {
        int index = Integer.parseInt(arg);
        this.tasks.get(index - 1).markAsDone();
    }

    private void unmark(String arg) {
        int index = Integer.parseInt(arg);
        this.tasks.get(index - 1).unmark();
    }

    private void run() {
        System.out.println(GREETING);

        Scanner scanner = new Scanner(System.in);
        System.out.print(INPUT_PREFIX);

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            String command = input.split(" ")[0].toLowerCase();
            String argument = input.length() > command.length() + 1 ? input.substring(command.length() + 1) : null;

            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again!");
                break;
            } else if (command.equals("list")) {
                this.list(argument);
            } else if (command.equals("mark")) {
                this.mark(argument);
            }  else if (command.equals("unmark")) {
                this.unmark(argument);
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
