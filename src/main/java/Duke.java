import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Task[] tasks = new Task[100];
        String name = "Duke";
        int pointer = 0;
        System.out.println("Hello! I'm " + name + "\nWhat can I do for you?");
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= pointer; i++) {
                    System.out.println(i + "." + tasks[i - 1]);
                }
            } else if (input.startsWith("mark")) {
                int taskID = Integer.parseInt(input.split(" ")[1]);
                System.out.println("Nice! I've marked this task as done:");
                tasks[taskID - 1].markAsDone();
                System.out.println(tasks[taskID - 1]);
            } else if (input.startsWith("unmark")) {
                int taskID = Integer.parseInt(input.split(" ")[1]);
                System.out.println("OK, I've marked this task as not done yet:");
                tasks[taskID - 1].unmarkAsNotDone();
                System.out.println(tasks[taskID - 1]);
            } else {
                String taskName;
                String by;
                String at;
                if (input.startsWith("todo")) {
                    taskName = input.replace("todo", "").strip();
                    tasks[pointer++] = new ToDos(taskName);
                } else if (input.startsWith("deadline")) {
                    String[] temp = input.replace("deadline", "").strip().split("/by");
                    taskName = temp[0].strip();
                    by = temp[1].strip();
                    tasks[pointer++] = new Deadlines(taskName, by);
                } else if (input.startsWith("event")) {
                    String[] temp = input.replace("event", "").strip().split("/at");
                    taskName = temp[0].strip();
                    at = temp[1].strip();
                    tasks[pointer++] = new Events(taskName, at);
                } else {
                    tasks[pointer++] = new Task(input);
                }
                System.out.println("Got it. I've added this task:\n"
                    + tasks[pointer - 1] + "\n"
                    + "Now you have " + pointer + " tasks in the list.");
            }
        }
    }
}
