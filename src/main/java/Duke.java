import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm still evolving!\nWhat can I do for you, my highness?");
        Scanner in = new Scanner(System.in);

        Task[] tasks = new Task[100];
        int count = 0;

        while (true) {
            System.out.println("Your highness:");
            String input = in.nextLine();
            String[] elem = input.split(" ");
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= count; i++) {
                    Task task = tasks[i-1];
                    System.out.println(i + ". " + task.toString());
                }
            } else if (elem[0].equals("mark")) {
                int taskNo = Integer.parseInt(elem[1]);
                Task currTask = tasks[taskNo-1];
                currTask.setDone();
                System.out.println("Nice! I've marked this task as done:\n" + "[" + currTask.getStatusIcon() + "] " + currTask.description);
            } else if (elem[0].equals("unmark")) {
                int taskNo = Integer.parseInt(elem[1]);
                Task currTask = tasks[taskNo-1];
                currTask.setUndone();
                System.out.println("OK, I've marked this task as not done yet:\n" + "[" + currTask.getStatusIcon() + "] " + currTask.description);
            } else {
                String[] task = input.split(" ", 2);
                if (task[0].equals("todo")) {
                    tasks[count] = new Todo(task[1]);
                } else if (task[0].equals("deadline")) {
                    String taskDescription = task[1].split("/", 2)[0];
                    String taskDeadline = task[1].split("/", 2)[1].split(" ", 2)[1];
                    tasks[count] = new Deadline(taskDescription, taskDeadline);
                } else {
                    String taskDescription = task[1].split("/", 2)[0];
                    String taskAt = task[1].split("/", 2)[1].split(" ", 2)[1];
                    tasks[count] = new Event(taskDescription, taskAt);
                }
                count++;
                System.out.println("Got it. I've added this task:\n" + tasks[count-1].toString() +
                        "\nNow you have " + count + " tasks in the list.");
            }
        }
    }
}
