import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hazell {
    public static void reply(String msg) {
        String DIVIDER = "\t____________________________________________________________";
        System.out.println(DIVIDER);
        for (String line : msg.split("\n")) {
            System.out.println("\t" + line);
        }
        System.out.println(DIVIDER);
    }
    public static void main(String[] args) {
        String logo = "  _    _               _ _ \n"
                + " | |  | |             | | |\n"
                + " | |__| | __ _ _______| | |\n"
                + " |  __  |/ _` |_  / _ \\ | |\n"
                + " | |  | | (_| |/ /  __/ | |\n"
                + " |_|  |_|\\__,_/___\\___|_|_|\n";
        System.out.println(logo);

        List<Task> taskList = new ArrayList<>();

        reply("Hello, I am Hazell!\nWhat can I do for you?");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String userinput = scanner.nextLine().strip();
            if (userinput.equals("bye")) {
                reply("Bye. Hope to see you again soon!");
                System.exit(0);
            } else if (userinput.equals("list")) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < taskList.size(); i++) {
                    Task task = taskList.get(i);
                    sb.append(String.format("%d. %s", i + 1, task.toString()));
                    if (i != taskList.size() - 1) sb.append("\n");
                }
                reply(sb.toString());
            } else if (userinput.startsWith("mark ")) {
                int index = Integer.parseInt(userinput.split(" ")[1]) - 1;
                Task task = taskList.get(index);
                task.markAsDone();
                reply(String.format("Nice! I've marked this task as done:\n\t%s", task.toString()));
            } else if (userinput.startsWith("unmark ")) {
                int index = Integer.parseInt(userinput.split(" ")[1]) - 1;
                Task task = taskList.get(index);
                task.markAsUndone();
                reply(String.format("OK, I've marked this task as not done yet:\n\t%s", task.toString()));
            } else {
                taskList.add(new Task(userinput));
                reply(String.format("added: %s", userinput));
            }
        }
    }
}
