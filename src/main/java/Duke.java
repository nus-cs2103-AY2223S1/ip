import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String input;
        String print;
        String[] segments;
        ArrayList<Task> items = new ArrayList<Task>();
        String space = " ";

        Scanner sc= new Scanner(System.in);

        String line = "________________________________________________________________\n";
        System.out.println(line + "Hello! I'm Shanice:)");
        System.out.println("What can I do for you?\n" + line);

        //level 3:
        input = sc.nextLine();
        segments = input.split(space);
        while (!input.toLowerCase(Locale.ROOT).equals("bye")) {
            if (input.toLowerCase(Locale.ROOT).equals("list")) {
                if (items.size() != 0) {
                    System.out.println(line + "Here are the tasks in your list: ");
                    for (int i = 0; i < items.size(); i++) {
                        System.out.println(i+1 + ". " + items.get(i).getStatusIcon() + space + items.get(i).description);
                    }
                    System.out.println(line);
                }
            }
            else if (segments[0].toLowerCase(Locale.ROOT).equals("mark") || segments[0].toLowerCase(Locale.ROOT).equals("unmark")) {
                String input1 = segments[0].toLowerCase(Locale.ROOT);
                int index = Integer.parseInt(segments[1]);
                Task t = items.get(index - 1);
                if (input1.equals("mark")) {
                    t.markAsDone();
                    System.out.println(line + "Nice! I've marked this task as done: ");
                }
                else {
                    t.markAsNotDone();
                    System.out.println(line + "OK, I've marked this task as not done yet:");
                }
                System.out.println(t.getStatusIcon() + space + t.description );
                System.out.println(line);
            }
            else {
                Task t = new Task(input);
                items.add(t);
                System.out.println(line + "added: " + input + "\n" + line);
            }
            input = sc.nextLine();
            segments = input.split(space);
        }

        print = "Bye. Hope to see you again soon!";
        System.out.println(line + print + "\n" + line);
    }

}

