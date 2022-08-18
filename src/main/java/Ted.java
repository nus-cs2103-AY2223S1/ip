import java.util.ArrayList;
import java.util.Scanner;

public class Ted {

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

    private void addTask(Task task) {
        this.tasks.add(task);
        System.out.printf("Got it. I've added this task:\n" +
                "%s\n" +
                "Now you have %d tasks in the list.\n",
                task.toString(),
                tasks.size()
        );
    }

    private void mark(String arg) {
        int index = Integer.parseInt(arg);
        this.tasks.get(index - 1).markAsDone();
    }

    private void unmark(String arg) {
        int index = Integer.parseInt(arg);
        this.tasks.get(index - 1).unmark();
    }

    private void todo(String arg) {
        this.addTask(new ToDo(arg));
    }

    private void deadline(String arg) {
        String[] inputs = arg.split(" /by ");
        String description = inputs[0];
        String by = inputs[1];
        this.addTask(new Deadline(description, by));
    }

    private void event(String arg) {
        String[] inputs = arg.split(" /at ");
        String description = inputs[0];
        String at = inputs[1];
        this.addTask(new Event(description, at));
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
            } else if (command.equals("todo")) {
                this.todo(argument);
            } else if (command.equals("deadline")) {
                this.deadline(argument);
            } else if (command.equals("event")) {
                this.event(argument);
            }

            System.out.print(INPUT_PREFIX);
        }

        scanner.close();
    }

    public static void main(String[] args) {
        Ted bot = new Ted();
        bot.run();
    }
}
