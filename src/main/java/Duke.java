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
                    System.out.println(String.format("%d. %s", i + 1, tasks[i]));
                }
            } else if (command.startsWith("mark")) {
                String taskNumberString = command.split(" ")[1];
                int taskNumber = Integer.parseInt(taskNumberString);
                tasks[taskNumber - 1].markComplete();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(String.format("  %s", tasks[taskNumber - 1]));
            } else if (command.startsWith("unmark")) {
                String taskNumberString = command.split(" ")[1];
                int taskNumber = Integer.parseInt(taskNumberString);
                tasks[taskNumber - 1].markIncomplete();
                System.out.println("OK, I've marked this task as not done yet");
                System.out.println(String.format("  %s", tasks[taskNumber - 1]));
            } else {
                Task newTask = null;
                if (command.startsWith("todo")) {
                    command = command.replace("todo ", "");
                    newTask = new ToDo(command);

                } else if (command.startsWith("deadline")) {
                    command = command.replace("deadline ", "");
                    String[] commands = command.split(" /by ");
                    newTask = new Deadline(commands[0], commands[1]);
                } else  if (command.startsWith("event")) {
                    command = command.replace("event ", "");
                    String[] commands = command.split(" /at ");
                    newTask = new Event(commands[0], commands[1]);
                }
                tasks[taskCount++] = newTask;
                System.out.println("Got it. I've added this task:");
                System.out.println(newTask);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
