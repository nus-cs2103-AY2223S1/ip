import java.util.Scanner;
public class Duke {
    private static Task[] tasks = new Task[100];
    public static String getTaskDetails(int pos) {
        return "[" +
                tasks[pos].getType() +
                "][" +
                tasks[pos].getStatus() +
                "] " +
                tasks[pos].getDescription();
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Hello! I'm Duke.\nWhat can I do for you?");
        System.out.print(">> ");
        String command = input.nextLine();
        while (!command.equals("bye")) {
            String cmdMain = command.split(" ", 2)[0];
//            String cmdParams = command.split(" ", 2)[1];
            switch (cmdMain) {
                case "list":
                    for (int pos = 0; pos < 100; pos++) {
                        if (tasks[pos] == null) break;
                        System.out.println(pos + 1 + ". " + getTaskDetails(pos));
                    }
                    break;
                case "mark":
                case "unmark": {
                        int pos = Integer.parseInt(command.split(" ", 2)[1]) - 1;
                        if (cmdMain.equals("mark")) {
                            tasks[pos].markAsDone();
                        } else {
                            tasks[pos].markAsUndone();
                        }
                        System.out.println(
                                "Nice! I've marked this task as " +
                                        (cmdMain.equals("mark") ? "done" : "undone") +
                                        "\n" +
                                        getTaskDetails(pos)
                        );
                    }
                    break;
                default:
                    System.out.println("Added: " + command);
                    for (int pos = 0; pos < 100; pos++) {
                        if (tasks[pos] == null) {
                            tasks[pos] = new ToDo(command);
                            break;
                        }
                    }
                }
            System.out.print(">> ");
            command = input.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        input.close();
    }
}
