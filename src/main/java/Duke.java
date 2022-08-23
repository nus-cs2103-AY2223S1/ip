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
            } else {
                list[items] = new Task(input);
                items++;
                System.out.println("added: " + input);
            }
            input = sc.nextLine();
        }
        System.out.println(bye);
    }
}
