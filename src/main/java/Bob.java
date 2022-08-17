import java.util.Scanner;
import java.util.ArrayList;

public class Bob {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>(100);
        Scanner scanner = new Scanner(System.in);
        System.out.println("hey, i'm bob!\ndo you need help?");
        String reply = scanner.nextLine();

        while (!reply.equalsIgnoreCase("bye")) {
            if (reply.equalsIgnoreCase("list")) {
                int index = 1;
                for (Task task : tasks) {
                    System.out.println((index++) + ". " + task.toString());
                }
            } else if (reply.toLowerCase().matches("mark(.*)")) {
                try {
                    int index = Integer.valueOf(reply.replaceAll("[^0-9]", ""));
                    Task task = tasks.get(index - 1);
                    task.toMark(true);
                    System.out.println("yay! you've completed a task!\n" + task.toString());
                } catch(NumberFormatException e) {
                    System.out.println("which task to mark?");
                }

            } else if (reply.toLowerCase().matches("unmark(.*)")) {
                try {
                    int index = Integer.valueOf(reply.replaceAll("[^0-9]", ""));
                    Task task = tasks.get(index - 1);
                    task.toMark(false);
                    System.out.println("aw...i guess there's another task.\n" + task.toString());
                } catch(NumberFormatException e) {
                    System.out.println("which task to unmark?");
                }
            } else {
                System.out.println("now in your list: " + reply);
                Task newTask = new Task(reply);
                tasks.add(newTask);
            }
            reply = scanner.nextLine();
        }

        System.out.println("bye \nsee you again!");
    }
}
