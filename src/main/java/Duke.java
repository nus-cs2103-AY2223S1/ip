import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String input;
        String print;
        String[] segments;
        String[] segments2;
        ArrayList<Task> items = new ArrayList<Task>();
        String space = " ";

        Scanner sc= new Scanner(System.in);

        String line = "________________________________________________________________\n";
        System.out.println(line + "Hello! I'm Shanice:)");
        System.out.println("What can I do for you?\n" + line);

        //level 4:
        input = sc.nextLine();
        segments = input.split("/");
        segments2 = segments[0].split(space);
        while (!input.equals("bye")) {
            if (input.equals("list") && items.size() != 0) {
                    System.out.println(line + "Here are the tasks in your list: ");
                    for (int i = 0; i < items.size(); i++) {
                        System.out.println(i+1 + ". " + items.get(i));
                    }
                    System.out.println(line);
            }
            else if (segments2[0].equals("mark") || segments2[0].equals("unmark")) {
                int index = Integer.parseInt(segments2[1]);
                Task t = items.get(index - 1);
                if (segments2[0].equals("mark")) {
                    System.out.println(line + "Nice! I've marked this task as done: ");
                    t.markAsDone();
                }
                else {
                    System.out.println(line + "OK, I've marked this task as not done yet:");
                    t.markAsNotDone();
                }
                System.out.println(t);
                System.out.println(line);
            }
            else if (segments2[0].equals("todo") || segments2[0].equals("deadline") || segments2[0].equals("event")) {
                Task t;
                segments[0] = segments[0].replace(segments2[0], "");
                if (segments2[0].equals("todo")) {
                    t = new Todo(segments[0]);
                }
                else if (segments2[0].equals("deadline")) {
                    segments[1] = segments[1].replace("by ", "");
                    t = new Deadline(segments[0], segments[1]);
                }
                else {
                    segments[1] = segments[1].replace("at ", "");
                    t = new Events(segments[0], segments[1]);
                }

                items.add(t);
                System.out.println(line + "Got it. I've added this task:");
                System.out.println(t);
                System.out.println("Now you have " + items.size() + " tasks in the list.\n" + line);
            }
            else {
                Task t = new Task(input);
                items.add(t);
                System.out.println(line + "added: " + input + "\n" + line);
            }
            input = sc.nextLine();
            segments = input.split("/");
            segments2 = segments[0].split(space);
        }

        print = "Bye. Hope to see you again soon!";
        System.out.println(line + print + "\n" + line);
    }

}

