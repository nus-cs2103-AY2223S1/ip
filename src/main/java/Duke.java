import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);

        String command = sc.nextLine();

        ArrayList<Task> items = new ArrayList<>();

        while (!command.toLowerCase().equals("bye")) {
            if (command.toLowerCase().equals("list")) {
                for (int i = 0; i < items.size(); i++) {
                    int j = i + 1;
                    Task item = items.get(i);
                    System.out.println(j + ". " + item.toString());
                }
            } else if (command.split(" ")[0].toLowerCase().equals("done")) {
                int index = Integer.parseInt(command.split(" ")[1]) - 1;
                if (index < 0 || index > items.size() - 1) {
                    System.out.println("Out of range");
                } else {
                    items.get(index).markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(items.get(index).toString());
                }
            } else {
                System.out.println("added: " + command);
                items.add(new Task(command));
            }
            command = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
