import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "Turtle";
        System.out.println("Hello I am " + logo +"!");
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        boolean run = true;
        String input;
        ArrayList<Task> items = new ArrayList<Task>();

        while(run) {
            System.out.println("\n--------------------");
            input = sc.nextLine();
            String[] segments = input.split(" ");
            if (input.equals("bye")) {
                System.out.println("Goodbye!");
                run = false;
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list: ");
                for ( int i = 0; i < items.size(); i++) {
                    System.out.println(i+1 + ".["+ items.get(i).getStatusIcon()+"] " + items.get(i).getDescription());
                }
            } else if (segments[0].equals("mark")) {
                int index = Integer.parseInt(segments[1]);
                Task t = items.get(index - 1);
                t.markAsDone();
                System.out.println("Good! I've marked this task as done: ");
                System.out.println("[" + t.getStatusIcon() + "] " + t.getDescription());
            } else if (segments[0].equals("unmark")) {
                int index = Integer.parseInt(segments[1]);
                Task t = items.get(index - 1);
                t.markAsNotDone();
                System.out.println("ok, I've marked this task as not done yet:  ");
                System.out.println("[" + t.getStatusIcon() + "] " + t.getDescription());
            } else {
                Task t = new Task(input);
                items.add(t);
                System.out.print("added: " + input);
            }
        }
    }
}
