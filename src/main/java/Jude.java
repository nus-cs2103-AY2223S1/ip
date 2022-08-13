import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Jude {
    private static List<Task> tasks = new ArrayList<>();

    /**
     * Runs the chatbot.
     *
     * @param args not used for now
     */
    public static void main(String[] args) {
        System.out.println("Hello! I'm Jude.");
        System.out.println("What can I do for you?");

        // Solution below adapted from
        // https://stackoverflow.com/questions/5287538/how-to-get-the-user-input-in-java
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println();
            System.out.print("> ");
            String str = sc.nextLine();
            String[] tokens = str.split(" ");
            if (tokens[0].equals("mark")) {
                int index = Integer.parseInt(tokens[1])-1;
                Task task = tasks.get(index);

                //Solution below adapted from
                //https://nus-cs2103-ay2223s1.github.io/website/schedule/week2/project.html
                task.markAsDone();

                System.out.println("The following task has been marked as done");
                System.out.printf("[%s] %s\n", task.getStatusIcon(), task.getDescription());
            } else if (tokens[0].equals("unmark")) {
                int index = Integer.parseInt(tokens[1])-1;
                Task task = tasks.get(index);
                task.markAsUndone();
                System.out.println("The following task has been marked as undone");
                System.out.printf("[%s] %s\n", task.getStatusIcon(), task.getDescription());
            } else if (str.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    Task task = tasks.get(i);
                    String description = task.getDescription();
                    String statusIcon = task.getStatusIcon();
                    System.out.printf("%d.[%s] %s\n", i+1, statusIcon, description);
                }
            } else if (str.equals("bye")) {
                System.out.println("Goodbye! Have a nice day!");
                break;
            } else {
                System.out.println("added: " + str);
                tasks.add(new Task(str, false));
            }
        }
    }
}
