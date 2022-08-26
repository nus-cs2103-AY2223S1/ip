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
        String[] command;

        do {
            text = scanner.nextLine();

            command = text.split(" ");

            System.out.println(line);
            switch (command[0]) {
            case "bye": {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            case "todo": {
                if (text.length() <= 5) {
                    System.out.println("Invalid todo command. Example: todo borrow book");
                    break;
                }
                try {
                    Task task = new Todo(text.substring(5));
                    tasks.add(task);
                    System.out.println(task.getAddMessage(tasks.size()));
                } catch (DukeException e) {
                    System.out.println("Unable to create new todo item: " + e);
                }
                break;
            }

            case "deadline": {
                int idx = text.indexOf("/by");

                if (command.length < 4 || idx < 0 || idx == 9) {
                    System.out.println("Invalid deadline command. Example: deadline return book /by 2022-04-02");
                    break;
                }

                String description = text.substring(9, idx - 1);
                String by = text.substring(idx + 4);

                try {
                    Task task = new Deadline(description, by);
                    tasks.add(task);
                    System.out.println(task.getAddMessage(tasks.size()));
                } catch (DukeException e) {
                    System.out.println("Unable to create new deadline item: " + e);
                }
                break;
            }

            case "event": {
                int idx = text.indexOf("/at");

                if (command.length < 4 || idx < 0 || idx == 6) {
                    System.out.println("Invalid deadline command. Example: event project meeting /at 2020-02-29");
                    break;
                }

                String description = text.substring(6, idx - 1);
                String by = text.substring(idx + 4);

                try {
                    Task task = new Event(description, by);
                    tasks.add(task);
                    System.out.println(task.getAddMessage(tasks.size()));
                } catch (DukeException e) {
                    System.out.println("Unable to create new deadline item: " + e);
                }
                break;
            }

            case "list": {
                System.out.println("Here are the tasks in your list:");
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

            case "delete": {
                if (command.length != 2) {
                    System.out.println("Usage: 'delete 3'");
                    break;
                }

                // Get index to delete
                int idx = Integer.parseInt(command[1]);

                if (idx <= 0 || idx > tasks.size()) {
                    System.out.println("Invalid task number, please check your task number again.");
                    break;
                }

                Task task = tasks.remove(idx - 1);
                System.out.println(
                        "Deleted this task:\n" + task + "\nYou have " + tasks.size() + " tasks remaining."
                );
                break;
            }

            default:
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                break;
            }
            System.out.println(line);
        } while (!command[0].equals("bye"));
    }
}

