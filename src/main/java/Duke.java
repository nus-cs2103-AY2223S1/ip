import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static int count = 0;
    private static String lineBreak
            = "____________________________________________________________";

    public static int getCount() {
        return count;
    }

    public static ArrayList<Task> getTasks() {
        return tasks;
    }

    public static void printBot(String s) {
        System.out.println(lineBreak);
        System.out.println(s);
        System.out.println(lineBreak);
        System.out.println();
    }

    public static void addTask(Task t) {
        tasks.add(t);
        ++count;

        System.out.println(lineBreak);
        System.out.println("Got it. I've added this task:\n"
                           + "  " + t);
        System.out.println("Now you have " + count + " tasks in the list.");
        System.out.println(lineBreak);
        System.out.println();
    }

    public static void deleteTask(int index) {
        Task t = tasks.remove(index);
        --count;

        System.out.println(lineBreak);
        System.out.println("Noted. I've removed this task:\n"
                           + "  " + t);
        System.out.println("Now you have " + count + " tasks in the list.");
        System.out.println(lineBreak);
        System.out.println();
    }

    public static void listTasks() {
        System.out.println(lineBreak);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); ++i) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println(lineBreak);
        System.out.println();
    }

    public static void markTask(Task t, boolean b) {
        t.setMarked(b);
        if (b) {
            printBot("Nice! I've marked this task as done: \n"
                     + "  " + t);
        } else {
            printBot("OK, I've marked this task as not done: \n"
                    + "  " + t);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Nuke");
        System.out.println("What can I do for you?");
        String s;
        while(true) {
            s = scanner.nextLine();
            String[] words = s.split(" ", 2);
            try {
                Command command = Command.valueOf(words[0].toUpperCase());
                command.run(words.length == 2 ? words[1] : "");
            } catch (IllegalArgumentException e) {
                if (s.equals("bye")) {
                    printBot("Bye. Hope to see you again soon!");
                    return;
                } else {
                    printBot("----Error----\nPlease enter a valid command:\n\n"
                            + "mark\n" + "unmark\n" + "list\n" + "todo\n" + "deadline\n" + "event");
                }
            }
        }
    }
}
