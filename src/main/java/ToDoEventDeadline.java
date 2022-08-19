import java.util.ArrayList;
import java.util.Scanner;

public class ToDoEventDeadline {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        ArrayList<Task> toDo = new ArrayList<Task>();

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while(!input.equals("bye")) {

            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                toDo.forEach((n) -> {
                    String result = String.format("%d: %s", toDo.indexOf(n) + 1, n);
                    System.out.println(result);
                });
            } else if (input.startsWith("mark")) {
                Task task = toDo.get(Integer.parseInt(input.substring(5)) - 1);
                task.mark();

                System.out.println("Nice! I've marked this task as done:");
                System.out.println(task);

            } else if (input.startsWith("unmark")) {
                Task task = toDo.get(Integer.parseInt(input.substring(7)) - 1);
                task.unmark();

                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(task);


            } else {
                Task task = null;
                
                if (input.startsWith("todo")) {
                    task = new Todo(input);
                } else if (input.startsWith("event")) {

                    String taskName = input.substring(6, input.indexOf("/") - 1);
                    String at = input.substring(input.indexOf("/") + 1);

                    task = new Event(taskName, at);
                } else if (input.startsWith("deadline")) {

                    String taskName = input.substring(9, input.indexOf("/") - 1);
                    String by = input.substring(input.indexOf("/") + 1);

                    task = new Deadline(taskName, by);

                }
                toDo.add(task);

                System.out.println("Got it. I've added this task:");
                System.out.println(task);
                System.out.printf("Now you have %d tasks in the list.%n", toDo.size());
            }

            input = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");

    }
}
