import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<String> taskList = new ArrayList<>();

    private static String getOutput(String input) {
        if (input.equals("bye")) {
            return "Bye. Hope to see you again soon!";
        } else if (input.equals("list")) {
            return listTasks();
        } else {
            addTask(input);
            return "added: " + input;
        }
    }

    private static void addTask(String task) {
        taskList.add(task);
    }

    private static String listTasks() {
        String tasks = "";
        for (int i = 0; i < taskList.size(); i++) {
            tasks += (i + 1) + ". " + taskList.get(i) + "\n";
        }
        return tasks;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(logo + "\nHello! I'm Duke\n" + "What can I do for you?");
        String command = scanner.nextLine();
        while (true) {
            String output = getOutput(command);
            System.out.println(output);
            if (command.equals("bye")) {
                break;
            } else {
                command = scanner.nextLine();
            }
        }
    }
}
