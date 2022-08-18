import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static final String line = "____________________________________________________________\n";

    private static ArrayList<Task> tasks = new ArrayList<>();

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

            String[] command = text.split(" ");

            System.out.println(line);
            switch (command[0]) {
                case "bye": {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                }
                case "list": {
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                    break;
                }
                case "mark": {
                    if (command.length != 2) {
                        System.out.println("Usage: 'mark n'");
                        break;
                    }

                    // Get index to mark as complete
                    int idx = Integer.parseInt(command[1]);

                    if (idx <= 0 || idx > tasks.size()) {
                        System.out.println("Invalid task number, please check your task number again.");
                        break;
                    }

                    Task task = tasks.get(idx - 1);
                    task.markAsDone();
                    System.out.println("Marked '" + task.getDescription() + "' as done.");
                    break;
                }

                case "unmark": {
                    if (command.length != 2) {
                        System.out.println("Usage: 'unmark n'");
                        break;
                    }

                    // Get index to unmark
                    int idx = Integer.parseInt(command[1]);

                    if (idx <= 0 || idx > tasks.size()) {
                        System.out.println("Invalid task number, please check your task number again.");
                        break;
                    }

                    Task task = tasks.get(idx - 1);
                    task.unmark();
                    System.out.println("Unmarked '" + task.getDescription() + "'.");
                    break;
                }
                default:
                    tasks.add(new Task(text));
                    System.out.println("added: " + text);
                    break;
            }
            System.out.println(line);
        } while (!text.equals("bye"));
    }
}

