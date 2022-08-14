import java.util.Scanner;
public class Duke {
    private static Task[] tasks = new Task[100];
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Hello! I'm Duke.\nWhat can I do for you?");
        System.out.print(">> ");
        String command = input.nextLine();
        while (true) {
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                input.close();
                break;
            } else if (command.equals("list")) {
                for (int pos = 0; pos < 100; pos++) {
                    if (tasks[pos] == null) break;
                    System.out.println(
                            pos + 1 + ". [" + tasks[pos].getStatus() + "] " + tasks[pos].getDescription()
                    );
                }
                System.out.print(">> ");
                command = input.nextLine();
            } else if (command.startsWith("mark") || command.startsWith("unmark")) {
                int pos = Integer.parseInt(command.split(" ")[1]) - 1;
                if (tasks[pos] == null) {
                    System.out.println("No such task.");
                } else {
                    if (command.startsWith("mark")) {
                        tasks[pos].markAsDone();
                    } else {
                        tasks[pos].markAsUndone();
                    }
                    System.out.println(
                            "Nice! I've marked this task as " +
                                    (command.startsWith("mark") ? "done" : "undone") +
                                    "\n [" +
                                    tasks[pos].getStatus() +
                                    "] " +
                                    tasks[pos].getDescription()
                    );
                }
                System.out.print(">> ");
                command = input.nextLine();
            } else {
                System.out.println("Added: " + command);
                for (int pos = 0; pos < 100; pos++) {
                    if (tasks[pos] == null) {
                        tasks[pos] = new Task(command);
                        break;
                    }
                }
                System.out.print(">> ");
                command = input.nextLine();
            }
        }
    }
}
