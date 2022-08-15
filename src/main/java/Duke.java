import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static String line = "    ----------------------------------------";
    public static ArrayList<String> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("    Hello from\n" + logo);
        System.out.println(line);
        System.out.println("    Hello! I'm Duke\n    What can I do for you?");
        System.out.println(line);

        String current = sc.nextLine();
        while (!current.equals("bye")) {
            if (current.equals("list")) {
                System.out.println(line);
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println("    " + (i + 1) + ". " + tasks.get(i));
                }
                System.out.println(line);
            } else {
                System.out.println(line);
                System.out.println("    added: " + current);
                tasks.add(current);
                System.out.println(line);
            }
            current = sc.nextLine();
        }

        System.out.println(line);
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
