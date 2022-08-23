import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greeting = "Hello";
        String bye = "Goodbye";
        System.out.println(greeting);

        Task[] list = new Task[100];
        int items = 0;

        String input;
        Scanner sc = new Scanner(System.in);
        input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println("List of tasks:");
                for (int i = 1; i < items + 1; i++) {
                    System.out.println(i + ". " + list[i - 1]);
                }
            } else if (input.startsWith("mark")) {
                int i = Integer.parseInt(input.substring(5)) - 1;
                if (i > items - 1) {
                    System.out.println("No task found");
                } else {
                    list[i].markDone();
                    System.out.println("Task done: " + list[i]);
                }
            } else if (input.startsWith("unmark")) {
                int i = Integer.parseInt(input.substring(7)) - 1;
                if (i > items - 1) {
                    System.out.println("No task found");
                } else {
                    list[i].markNotDone();
                    System.out.println("Task not done: " + list[i]);
                }
            } else if (input.startsWith("todo")) {
                Task t = new Todo(input.substring(5));
                list[items] = t;
                items++;
                System.out.println("Added ToDo: " + t);
            } else if (input.startsWith("deadline")) {
                String[] str = input.split("/", 2);
                String s1 = str[0].substring(9, str[0].length() - 1);
                String s2 = str[1].substring(3);
                Task t = new Deadline(s1, s2);
                list[items] = t;
                items++;
                System.out.println("Added Deadline: " + t);
            } else if (input.startsWith("event")) {
                String[] str = input.split("/", 2);
                String s1 = str[0].substring(6, str[0].length() - 1);
                String s2 = str[1].substring(3);
                Task t = new Event(s1, s2);
                list[items] = t;
                items++;
                System.out.println("Added Event: " + t);
            }
            input = sc.nextLine();
        }
        System.out.println(bye);
    }
}
