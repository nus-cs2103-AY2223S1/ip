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

        ArrayList<Task> store = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        String input = sc.next();

        while (!input.equals("bye")) {
            System.out.println("    ____________________________________________________________");
            if (input.equals("list")) {
                for (int i = 0; i < store.size(); i++) {
                    System.out.println("     " + (i + 1) + ".[" +  store.get(i).getStatusIcon() + "] " + store.get(i).getTask());
                }
                System.out.println("    ____________________________________________________________\n");
                input = sc.next();
            } else if (input.equals("mark")) {
                int taskNo = sc.nextInt();
                store.get(taskNo - 1).doTask();
                System.out.println("     [X] " + store.get(taskNo - 1).getTask());
                input = sc.next();
            } else if (input.equals("unmark")) {
                int taskNo = sc.nextInt();
                store.get(taskNo - 1).undoTask();
                System.out.println("     [ ] " + store.get(taskNo - 1).getTask());
                input = sc.next();
            } else {
                if (input.equals("todo")) {
                    Task curr = new Todo(input + sc.nextLine());
                    store.add(curr);
                    System.out.println("    " + " added: " + curr);
                    System.out.println("    ____________________________________________________________\n");
                    input = sc.next();
                } else if (input.equals("deadline")) {
                    String task = sc.next();
                    while (!sc.hasNext("/by")) {
                        task += " " + sc.next();
                    }
                    sc.next();
                    Task curr = new Deadline(task, sc.nextLine());
                    store.add(curr);
                    System.out.println("     Got it. I've added this task:");
                    System.out.println("        [" + curr.);
                    System.out.println("     Now you have 6 tasks in the list.");
                    System.out.println("    ____________________________________________________________\n");
                    input = sc.next();
                } else {
                    
                }

            }
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________\n");
    }
}
