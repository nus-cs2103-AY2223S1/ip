import java.util.Scanner;
import java.util.ArrayList;

public class Skyler {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();

        // declare scanner object and initialise with
        // predefined standard input object
        Scanner sc = new Scanner(System.in);

        System.out.println("Good day, mate! I'm Skyler\nHow can I help you?\n");

        while(sc.hasNext()) {
            String description = sc.nextLine();

            if (description.equals("bye")) {
                System.out.println("Bye! See you again soon!");
                break;
            } else if (description.equals("list")) {
                System.out.println("Tasks:");
                for (int i = 0; i < tasks.size(); i++) {
                    String str = String.format("%d.[%s] %s", i + 1, tasks.get(i).getStatusIcon(), tasks.get(i));
                    System.out.println(str);
                }
            } else if (description.startsWith("mark")) {
                int item = Integer.parseInt(description.substring(description.length() - 1));
                Task currTask = tasks.get(item - 1);
                currTask.markAsDone();
                System.out.println("You have completed this task:");
                String show = String.format("  [%s] %s", currTask.getStatusIcon(), currTask);
                System.out.println(show);
            } else if (description.startsWith("unmark")) {
                int item = Integer.parseInt(description.substring(description.length() - 1));
                Task currTask = tasks.get(item - 1);
                currTask.markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                String show = String.format("  [%s] %s", currTask.getStatusIcon(), currTask);
                System.out.println(show);
            } else {
                Task newTask = new Task(description);
                tasks.add(newTask);
                String str = String.format("added: %s", description);
                System.out.println(str);
            }
        }

        sc.close();
    }
}
