import java.util.*;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm SmartBot\nWhat can I do for you?");
        Task[] lst = new Task[100];
        int counter = 0;
        while(true) {
            Scanner sc = new Scanner(System.in);
            String first = sc.nextLine();
            if(first.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if(first.equals("list")) {
                for (int i = 1; i < counter + 1; i++) {
                    System.out.println((i) + ". " + lst[i - 1]);
                }
            }
            else if(first.length() == 6 && first.substring(0, 4).equals("mark")) {
                char index = first.charAt(5);
                int number = Integer.parseInt(String.valueOf(index));
                Task t = lst[number - 1];
                t.mark();
                System.out.println("Nice! I've marked this task as done:\n  [X] " + t.description);
            }
            else if(first.length() == 8 && first.substring(0, 6).equals("unmark")) {
                char index = first.charAt(7);
                int number = Integer.parseInt(String.valueOf(index));
                Task t = lst[number - 1];
                t.unmark();
                System.out.println("OK, I've marked this task as not done yet:\n  [ ] " + t.description);
            }
            else if(first.length() >= 6 && first.substring(0, 4).equals("todo")) {
                String description = first.substring(5);
                Todo d = new Todo(description);
                lst[counter] = d;
                counter++;
                System.out.println("Got it. I've added this task:\n " + d +
                        "\nNow you have " + counter + " tasks in the list.");
            }
            else if(first.length() >= 10 && first.substring(0, 8).equals("deadline")) {
                int sepPos = first.indexOf("/");
                String description = first.substring(9, sepPos);
                String by = first.substring(sepPos + 4);
                Deadline l = new Deadline(description, by);
                lst[counter] = l;
                counter++;
                System.out.println("Got it. I've added this task:\n " + l +
                        "\nNow you have " + counter + " tasks in the list.");
            }
            else if(first.length() >= 7 && first.substring(0, 5).equals("event")) {
                int sepPos = first.indexOf("/");
                String description = first.substring(6, sepPos);
                String at = first.substring(sepPos + 4);
                Event e = new Event(description, at);
                lst[counter] = e;
                counter++;
                System.out.println("Got it. I've added this task:\n " + e +
                        "\nNow you have " + counter + " tasks in the list.");
            }
        }
    }
}
