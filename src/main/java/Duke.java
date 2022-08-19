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

        ArrayList<Task> tasks = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        while (true) {
            String s = in.next();
            if ("bye".equalsIgnoreCase(s)) {
                break;
            }
            else if (s.equalsIgnoreCase("mark")) {
                int selectedTask = in.nextInt();
                if (selectedTask > tasks.size()) {
                    System.out.println("No such task");
                }
                else {
                    Task t = tasks.get(selectedTask - 1);
                    t.markAsDone();
                    System.out.println("Nice! I've marked this task as done:\n" + t);
                }
            }
            else if (s.equalsIgnoreCase("unmark")) {
                int selectedTask = in.nextInt();
                if (selectedTask > tasks.size()) {
                    System.out.println("No such task");
                }
                else {
                    Task t = tasks.get(selectedTask - 1);
                    t.markAsNotDone();
                    System.out.println("OK, I've marked this task as not done yet:\n" + t);
                }
            }
            else if (s.equalsIgnoreCase("list")) {
                int length = tasks.size();
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < length; i++) {
                    System.out.println(i + 1 + ". " + tasks.get(i));
                }
            }
            else {
                if (s.equalsIgnoreCase("todo")) {
                    s = in.nextLine();
                    s = s.substring(1);
                    Task t = new Todo(s);
                    tasks.add(t);
                    int length = tasks.size();
                    String output = length == 1 ? " task in the list." : " tasks in the list.";
                    System.out.println("Got it. I've added this task:\n" + t +
                            "\nNow you have " + length + output);
                }
                if (s.equalsIgnoreCase("deadline")) {
                    StringBuilder desc = new StringBuilder();
                    String token;
                    while (!(token = in.next()).equals("/by")) {
                        desc.append(" " + token);
                    }
                    s = in.nextLine();
                    s = s.substring(1);
                    Task t = new Deadline(desc.toString().substring(1), s);
                    tasks.add(t);
                    int length = tasks.size();
                    String output = length == 1 ? " task in the list." : " tasks in the list.";
                    System.out.println("Got it. I've added this task:\n" + t +
                            "\nNow you have " + length + output);
                }
                if (s.equalsIgnoreCase("event")) {
                    StringBuilder desc = new StringBuilder();
                    String token;
                    while (!(token = in.next()).equals("/at")) {
                        desc.append(" " + token);
                    }
                    s = in.nextLine();
                    s = s.substring(1);
                    Task t = new Event(desc.toString().substring(1), s);
                    tasks.add(t);
                    int length = tasks.size();
                    String output = length == 1 ? " task in the list." : " tasks in the list.";
                    System.out.println("Got it. I've added this task:\n" + t +
                            "\nNow you have " + length + output);
                }
            }
        }
        System.out.println("Bye. Hope to see you again soon!");

        in.close();
    }
}
