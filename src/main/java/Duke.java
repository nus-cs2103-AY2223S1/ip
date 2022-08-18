import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        String logo = "Turtle";
        System.out.println("Hello I am " + logo + "!");
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        boolean run = true;
        String input;
        ArrayList<Task> items = new ArrayList<Task>();

        while (run) {
            System.out.println("\n--------------------");
            input = sc.nextLine();
            String[] segments = input.split(" ");
            try {
                if (input.equals("bye")) {
                    System.out.println("Goodbye!");
                    run = false;
                } else if (input.equals("list")) {
                    System.out.println("Here are the tasks in your list: ");
                    for (int i = 0; i < items.size(); i++) {
                        System.out.println(i + 1 + "." + items.get(i));
                    }
                } else if (segments[0].equals("mark")) {
                    int index = Integer.parseInt(segments[1]);
                    Task t = items.get(index - 1);
                    t.markAsDone();
                    System.out.println("Good! I've marked this task as done: ");
                    System.out.println(t);
                } else if (segments[0].equals("unmark")) {
                    int index = Integer.parseInt(segments[1]);
                    Task t = items.get(index - 1);
                    t.markAsNotDone();
                    System.out.println("ok, I've marked this task as not done yet:  ");
                    System.out.println(t);
                } else if (segments[0].equals("todo")) {
                    input = input.replace("todo", "");
                    if (input.equals("")) {
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    } else {
                        Task t = new Todo(input);
                        items.add(t);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(t);
                        System.out.println("Now you have " + Task.numTask + " tasks in the list.");
                    }
                } else if (segments[0].equals("deadline")) {
                    input = input.replace("deadline", "");
                    if (input.equals("")) {
                        throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                    } else {
                        String[] parts = input.split("/");
                        String by = parts[1].replace("by ", "");
                        Task t = new Deadline(parts[0], by);
                        items.add(t);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(t);
                        System.out.println("Now you have " + Task.numTask + " tasks in the list.");
                    }
                }  else if (segments[0].equals("event")) {
                    input = input.replace("event", "");
                    if (input.equals("")) {
                        throw new DukeException("OOPS!!! The description of a event cannot be empty.");
                    } else {
                        String[] parts = input.split("/");
                        String by = parts[1].replace("at ", "");
                        Task t = new Event(parts[0], by);
                        items.add(t);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(t);
                        System.out.println("Now you have " + Task.numTask + " tasks in the list.");
                    }
                } else if (segments[0].equals("delete")) {
                    int index = Integer.parseInt(segments[1]);
                    Task t = items.get(index-1);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(t);
                    items.remove(t);
                    t.removeTask();
                    System.out.println("Now you have " + Task.numTask + " tasks in the list.");
                } else {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
