import java.util.*;
import java.lang.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Task[] tasks = new Task[100];
        int taskIndex = 0;
        String indent = "    ";
        String line = "-------------------------------------------------";
        System.out.println(line);
        System.out.println(indent + "Hello! I'm Duke");
        System.out.println(indent + "What can I do for you?");
        System.out.println(line);
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            System.out.println(line);
            if (input.equals("bye")) {
                System.out.println(indent + "Bye. Hope to see you again soon!");
            } else if (input.equals("list")) {
                for (int j = 0; j < taskIndex; j++) {
                    int taskNum = j + 1;
                    System.out.println(indent + taskNum + ". " + tasks[j]);
                }
            } else if (input.startsWith("mark")){
                Task task = tasks[Integer.valueOf(input.substring(5)) - 1];
                task.mark();
                System.out.println(indent + "Nice! I've marked this task as done:");
                System.out.println("      " + task);
            } else if (input.startsWith("unmark")) {
                Task task = tasks[Integer.valueOf(input.substring(7)) - 1];
                task.unmark();
                System.out.println(indent + "Ok, I've marked this task as not done yet:");
                System.out.println("      " + task);
            } else if (input.startsWith("todo")) {
                tasks[taskIndex] = new Todo(input);
                System.out.println(indent + "Got it. I've added this task:");
                System.out.println("      " + tasks[taskIndex]);
                taskIndex += 1;
                System.out.println(indent + "Now you have " +  taskIndex + " tasks in the list");
            } else if (input.startsWith("event")) {
                String event = input.substring(0, input.indexOf("/"));
                String time = input.substring(input.indexOf("/") + 1);
                tasks[taskIndex] = new Event(event, time);
                System.out.println(indent + "Got it. I've added this task:");
                System.out.println("      " + tasks[taskIndex]);
                taskIndex += 1;
                System.out.println(indent + "Now you have " + taskIndex + " tasks in the list");
            } else if (input.startsWith("deadline")){
                String deadlineTask = input.substring(0, input.indexOf("/"));
                String deadline = input.substring(input.indexOf("/") + 1);
                tasks[taskIndex] = new Deadline(deadlineTask, deadline);
                System.out.println(indent + "Got it. I've added this task:");
                System.out.println("      " + tasks[taskIndex]);
                taskIndex += 1;
                System.out.println(indent + "Now you have " + taskIndex + " tasks in the list");
            }
            System.out.print(line);
            System.out.println();
            if (input.equals("bye")) {
                System.exit(0);
            }
        }
    }
}
