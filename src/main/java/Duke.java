import java.util.*;
public class Duke {
    public static void run(Task[] lst, int count) {
        Scanner sc = new Scanner(System.in);
        String choice = sc.nextLine();
        Scanner sc2 = new Scanner(choice);
        String call = sc2.next();

        if (choice.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            return;
        } else if (choice.equals("list")) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < count; i++) {
                int curr = i + 1;
                System.out.println(curr + ". " + lst[i].toString());
            }
        } else if (call.equals("mark")) {
            int pos = sc2.nextInt() - 1;
            lst[pos].mark();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(lst[pos].toString());
        } else if (call.equals("unmark")) {
            int pos = sc2.nextInt() - 1;
            lst[pos].unmark();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(lst[pos].toString());
        } else if (call.equals("todo")) {
            String description = sc2.nextLine();
            ToDo todo = new ToDo(description);
            lst[count++] = todo;
            System.out.println("Got it. I've added this task:");
            System.out.println("\t" + todo.toString());
            System.out.println("Now you have " + count + " tasks in the list.");
        } else if (call.equals("deadline")) {
            String description = "";
            while (!sc2.hasNext("/by")) {
                String next = sc2.next();
                description += next + " ";
            }
            String skip = sc2.next();
            String date = sc2.nextLine();
            Deadline deadline = new Deadline(description, date);
            lst[count++] = deadline;
            System.out.println("Got it. I've added this task: ");
            System.out.println("\t" + deadline.toString());
            System.out.println("Now you have " + count + " tasks in the list.");
        } else if (call.equals("event")) {
            String description = "";
            while (!sc2.hasNext("/at")) {
                String next = sc2.next();
                description += next + " ";
            }
            String skip = sc2.next();
            String date = sc2.nextLine();
            Event event = new Event(description, date);
            lst[count++] = event;
            System.out.println("Got it. I've added this task: ");
            System.out.println("\t" + event.toString());
            System.out.println("Now you have " + count + " tasks in the list.");
        }
        run(lst, count);
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Task[] lst = new Task[100];
        System.out.println("Hello! I'm Justin, your personal helper.");
        System.out.println("What can I do for you?");
        run(lst, 0);
    }
}
