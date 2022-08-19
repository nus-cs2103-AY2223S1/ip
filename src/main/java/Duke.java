import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");

        ArrayList<Task> text = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        while (true) {
            String s = in.nextLine();
            if ("bye".equalsIgnoreCase(s)) {
                break;
            }
            else if (s.length() > 4 && s.substring(0,4).equalsIgnoreCase("mark")) {
                int selectedTask = Integer.parseInt(s.substring(5));
                if (selectedTask > text.size()) {
                    System.out.println("No such task");
                }
                else {
                    Task t = text.get(selectedTask - 1);
                    t.markAsDone();
                    System.out.println("Nice! I've marked this task as done:\n" + t);
                }
            }
            else if (s.length() > 6 && s.substring(0,6).equalsIgnoreCase("unmark")) {
                int selectedTask = Integer.parseInt(s.substring(7));
                if (selectedTask > text.size()) {
                    System.out.println("No such task");
                }
                else {
                    Task t = text.get(selectedTask - 1);
                    t.markAsNotDone();
                    System.out.println("OK, I've marked this task as not done yet:\n" + t);
                }
            }
            else if ("list".equalsIgnoreCase(s)) {
                int length = text.size();
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < length; i++) {
                    System.out.println(i + 1 + ". " + text.get(i));
                }
            }
            else {
                Task t = new Task(s);
                text.add(t);
                System.out.println("added: " + s);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");

        in.close();
    }
}
