import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("-----------------------------------------------");
        System.out.println("| Hi this is Thesh. What can I do for you? |");
        System.out.println("-----------------------------------------------");
        Scanner sc = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCount = 0;
        while (sc.hasNext()) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(String.format("%d. [%s] %s", i + 1, tasks[i].getStatusIcon(), tasks[i]));
                }
            } else if (command.startsWith("mark")) {
                String taskNumberString = command.split(" ")[1];
                int taskNumber = Integer.parseInt(taskNumberString);
                tasks[taskNumber - 1].markComplete();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(String.format("  [%s] %s", tasks[taskNumber - 1].getStatusIcon(), tasks[taskNumber - 1]));
            } else if (command.startsWith("unmark")) {
                String taskNumberString = command.split(" ")[1];
                int taskNumber = Integer.parseInt(taskNumberString);
                tasks[taskNumber - 1].markIncomplete();
                System.out.println("OK, I've marked this task as not done yet");
                System.out.println(String.format("  [%s] %s", tasks[taskNumber - 1].getStatusIcon(), tasks[taskNumber - 1]));
            } else {
                Task newTask = new Task(command);
                tasks[taskCount++] = newTask;
                System.out.println("added: " + command);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
