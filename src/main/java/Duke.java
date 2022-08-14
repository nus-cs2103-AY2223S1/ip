import java.util.Scanner;
public class Duke {
    private static String[] list = new String[100];
    private static boolean[] isComplete = new boolean[100];
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
                    if (list[pos] == null) break;
                    System.out.println(
                            pos + 1 + ". " + (isComplete[pos] ? "[X] " : "[ ] ") + list[pos]
                    );
                }
                System.out.print(">> ");
                command = input.nextLine();
            } else if (command.startsWith("mark")) {
                int pos = Integer.parseInt(command.split(" ")[1]);
                if (list[pos - 1] == null) {
                    System.out.println("No such task.");
                } else {
                    isComplete[pos - 1] = true;
                    System.out.println(
                            "Nice! I've marked this task as done:\n" +
                                    "[X] " + list[pos - 1]
                    );
                }
                System.out.print(">> ");
                command = input.nextLine();
            } else if (command.startsWith("unmark")) {
                int pos = Integer.parseInt(command.split(" ")[1]);
                if (list[pos - 1] == null) {
                    System.out.println("No such task.");
                } else {
                    isComplete[pos - 1] = false;
                    System.out.println(
                            "Alright! I've marked this task as not done:\n" +
                                    "[ ] " + list[pos - 1]
                    );
                }
                System.out.print(">> ");
                command = input.nextLine();
            } else {
                System.out.println("Added: " + command);
                for (int pos = 0; pos < 100; pos++) {
                    if (list[pos] == null) {
                        list[pos] = command;
                        break;
                    }
                }
                System.out.print(">> ");
                command = input.nextLine();
            }
        }
    }
}
