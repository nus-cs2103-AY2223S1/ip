import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    static List<Task> tasks = new ArrayList<>();

    private static void markTask(String input, boolean done) {
        String[] splitInput = input.split(" ");
        if (splitInput.length < 2) {
            System.out.println("Please specify the number of the task");
            return;
        }
        Task task;
        try {
            int taskIdx = Integer.parseInt(splitInput[1]);
            task = tasks.get(taskIdx - 1);
            if (done) {
                task.mark();
            } else {
                task.unmark();
            }
            String successMessage = done
                    ? "Nice! I've marked this task as done:\n  %s\n"
                    : "OK, I've marked this task as not done yet:\n  %s\n";
            System.out.printf(successMessage, task.toString());
        } catch (Exception e) {
            System.out.println("That is not a valid task number!");
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" +
                           "What can I do for you?\n");
        Scanner s = new Scanner(System.in);
        while (true) {
            String input = s.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                if (tasks.size() == 0) {
                    System.out.println("No tasks to show");
                } else {
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(i + 1 + "." + tasks.get(i));
                    }
                }
            } else if (input.startsWith("mark")) {
                markTask(input, true);
            } else if (input.startsWith("unmark")) {
                markTask(input, false);
            } else {
                tasks.add(new Task(input));
                System.out.println("added: " + input);
            }
        }
    }
}
