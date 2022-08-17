import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        List<Task> list = new ArrayList<>();
        System.out.println("Hello I'm Duke\n" + "What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                int length = list.size();
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < length; i++) {
                    Task currentTask = list.get(i);
                    System.out.println((i + 1) + "." + "[" + currentTask.getStatusIcon()
                    + "] " + currentTask);
                }
            } else if (input.startsWith("mark")) {
                Task t = list.get(Integer.parseInt(input.substring(5)) - 1);
                t.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  [" + t.getStatusIcon() + "] " + t.description);
            } else if (input.startsWith("unmark")) {
                Task t = list.get(Integer.parseInt(input.substring(7)) - 1);
                t.markAsUndone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  [" + t.getStatusIcon() + "] " + t.description);
            } else {
                Task t = new Task(input);
                list.add(t);
                System.out.println("added: " + input);
            }
        }
    }

}
