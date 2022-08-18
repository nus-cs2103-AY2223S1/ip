import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke\n     What can I do for you?");
        System.out.println("    ____________________________________________________________\n");

        ArrayList<Task> todoList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        while (!input.equals("bye")) {
            System.out.println("    ____________________________________________________________");
            if (input.equals("list")) {
                for (int i = 0; i < todoList.size(); i++) {
                    System.out.println("     " + (i + 1) + ". " + todoList.get(i).toString());
                }
                System.out.println("    ____________________________________________________________\n");
                input = sc.nextLine();
            }
            if (input.startsWith("mark")) {
                int taskID = Integer.parseInt(input.substring(5)) - 1;
                todoList.get(taskID).mark();
                System.out.println("    Nice! I've marked this task as done:");
                System.out.println("    " + todoList.get(taskID).toString());
                System.out.println("    ____________________________________________________________\n");
                input = sc.nextLine();
            }
            if (input.startsWith("unmark")) {
                int taskID = Integer.parseInt(input.substring(7)) - 1;
                todoList.get(taskID).unmark();
                System.out.println("    OK, I've marked this task as not done yet:");
                System.out.println("    " + todoList.get(taskID).toString());
                System.out.println("    ____________________________________________________________\n");
                input = sc.nextLine();
            } else {
                Task task = new Task(input);
                todoList.add(task);
                System.out.println("    " + " added: " + input);
                System.out.println("    ____________________________________________________________\n");
                input = sc.nextLine();
            }
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________\n");
    }
}
