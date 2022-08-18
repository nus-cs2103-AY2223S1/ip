import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        List<Task> list = new ArrayList<>();
        System.out.println("Hello I'm Duke\n" + "What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            try {
                String input = scanner.nextLine();
                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (input.equals("list")) {
                    int length = list.size();
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < length; i++) {
                        Task currentTask = list.get(i);
                        System.out.println((i + 1) + "." + currentTask);
                    }
                } else if (input.startsWith("mark")) {
                    Task t = list.get(Integer.parseInt(input.substring(5)) - 1);
                    t.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(t);
                } else if (input.startsWith("unmark")) {
                    Task t = list.get(Integer.parseInt(input.substring(7)) - 1);
                    t.markAsUndone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(t);
                } else if (input.startsWith("todo")) {
                    if (input.substring(4).isBlank()) {
                        throw new DukeException("The description of a todo cannot be empty.");
                    }
                    Task t = new ToDo(input.substring(5));
                    list.add(t);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(t);
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                } else if (input.startsWith("deadline")) {
                    if (input.substring(8).isBlank()) {
                        throw new DukeException("The description of a deadline cannot be empty.");
                    }
                    String curr = input.substring(9, input.indexOf("/"));
                    Deadline d = new Deadline(curr, input.substring(input.lastIndexOf("/") + 3));
                    list.add(d);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(d);
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                } else if (input.startsWith("event")) {
                    if (input.substring(5).isBlank()) {
                        throw new DukeException("The description of a event cannot be empty.");
                    }
                    String curr = input.substring(6, input.indexOf("/"));
                    Event e = new Event(curr, input.substring(input.lastIndexOf("/") + 3));
                    list.add(e);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(e);
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
    }

}
