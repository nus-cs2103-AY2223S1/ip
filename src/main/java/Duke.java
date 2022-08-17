import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("-----------------------------------------------");
        System.out.println("| Hi this is Thesh. What can I do for you? |");
        System.out.println("-----------------------------------------------");
        Scanner sc = new Scanner(System.in);
        String[] tasks = new String[100];
        int task_count = 0;
        while (sc.hasNext()) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                break;
            }
            else if (command.equals("list")) {
                for (int i = 0; i < task_count; i++) {
                    System.out.println(String.format("%d. %s", i + 1, tasks[i]));
                }
            } else {
                tasks[task_count++] = command;
                System.out.println("added: " + command);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
