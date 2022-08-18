import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int count = 0;
        while (true) {
            String s = sc.next();
            if (s.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (s.equals("list")) {
                for (int a = 0; a < count; a++) {
                    System.out.println((a + 1) + "." + tasks[a]);
                }
                sc.nextLine();
            } else if (s.equals("mark")) {
                int index = sc.nextInt() - 1;
                tasks[index].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks[index]);
                sc.nextLine();
            } else if (s.equals("unmark")) {
                int index = sc.nextInt() - 1;
                tasks[index].markNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(tasks[index]);
                sc.nextLine();
            } else if (s.equals("todo")) {
                String task = sc.nextLine();
                tasks[count++] = new Task(task);
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks[count - 1]);
                System.out.println("Now you have " + count + " tasks in the list.");
            } else if (s.equals("deadline")) {
                String rest = sc.nextLine();
                String[] split = rest.split("/");
                String task = split[0];
                String deadline = split[1];
                deadline = deadline.substring(deadline.indexOf(' ') + 1);
                tasks[count++] = new Deadline(task, deadline);
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks[count - 1]);
                System.out.println("Now you have " + count + " tasks in the list.");
            } else if (s.equals("event")) {
                String rest = sc.nextLine();
                String[] split = rest.split("/");
                String task = split[0];
                String time = split[1];
                time = time.substring(time.indexOf(' ') + 1);
                tasks[count++] = new Event(task, time);
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks[count - 1]);
                System.out.println("Now you have " + count + " tasks in the list.");
            } else {
                String rest = sc.nextLine();
                String whole = " " + s + rest;
                tasks[count++] = new Task(whole);
                System.out.println("added: " + whole);
            }
        }
        sc.close();
    }
}
