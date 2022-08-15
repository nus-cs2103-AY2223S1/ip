import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    /**
     * Main class used to handle inputs
     */
    public static void main(String[] args) {
        Messages.welcome();

        String in = "";
        TaskList list = new TaskList();
        Scanner sc = new Scanner(System.in);

        while (true) {
            in = sc.nextLine();
            if (in.equals("bye")) {
                break;
            } else if (in.equals("list")) {
                list.printTasks();
            } else if (in.startsWith("mark")) {
                int index = Integer.valueOf(in.split(" ")[1]) - 1;
                list.mark(index);
            } else if (in.startsWith("unmark")) {
                int index = Integer.valueOf(in.split(" ")[1]) - 1;
                list.unmark(index);
            } else {
                Task task = TaskCreator.CreateTask(in);
                if (task == null) {
                    System.out.println("Invalid command. Please try again.");
                } else {
                    list.add(task);
                    System.out.println("added: " + task.toString());
                }
            }
            System.out.println("-------------------------------------------");
        }
        Messages.bye();
    }
}
