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
    public static void addTask(String details, String type) {
        switch (type) {
            case "todo":
                for (int pos = 0; pos < 100; pos++) {
                    if (tasks[pos] == null) {
                        tasks[pos] = new ToDo(details);
                        System.out.println(
                                "Got it. I've added this task:\n" +
                                        getTaskDetails(pos) +
                                        "\nNow you have " +
                                        (pos + 1) +
                                        " tasks in the list."
                                );
                        break;
                    }
                }
                break;
            case "deadline":
            case "event":
                String description = details.split("/")[0];
                String timing = details.split("/")[1].split(" ", 2)[1];
                for (int pos = 0; pos < 100; pos++) {
                    if (tasks[pos] == null) {
                        if (type.equals("deadline")) {
                            tasks[pos] = new Deadline(description, timing);
                        } else {
                            tasks[pos] = new Event(description, timing);
                        }
                        System.out.println(
                                "Got it. I've added this task:\n" +
                                        getTaskDetails(pos) +
                                        "\nNow you have " +
                                        (pos + 1) +
                                        " tasks in the list."
                        );
                        break;
                    }
                }
        }
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Hello! I'm Duke.\nWhat can I do for you?");
        System.out.print(">> ");
        String command = input.nextLine();
        while (!command.equals("bye")) {
            String cmdMain = command.split(" ", 2)[0];
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
                case "todo":
                case "deadline":
                case "event":
                    String cmdDetails = command.split(" ", 2)[1];
                    addTask(cmdDetails, cmdMain);
                    break;
                }
            System.out.print(">> ");
            command = input.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        input.close();
    }
}
