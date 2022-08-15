import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Task> list = new ArrayList<>();

        System.out.println("Hello! I'm Piggy");
        System.out.println("What can I oink for you?");

        String input;
        while (true) {
            input = sc.nextLine();
            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.printf("%d.[%s] %s\n", i + 1, list.get(i).getStatusIcon(),
                            list.get(i).description);
                }
            } else if (input.matches("^mark \\d+$")) {
                Task item = list.get(Integer.parseInt(input.substring(5)) - 1);
                item.markDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.printf("  [%s] %s\n", item.getStatusIcon(), item.description);
            } else if (input.matches("^unmark \\d+$")) {
                Task item = list.get(Integer.parseInt(input.substring(7)) - 1);
                item.markNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.printf("  [%s] %s\n", item.getStatusIcon(), item.description);
            } else if (input.equals("bye")) {
                break;
            } else {
                list.add(new Task(input));
                System.out.println("added: " + input);
            }
        }

        System.out.println("Bye. Hope to oink you again soon!");
        sc.close();
    }
}
