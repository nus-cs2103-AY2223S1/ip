import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Roger {
    private List<Task> tasks = new ArrayList<>();

    private static void sayGoodbye() {
        System.out.println("Bye bye niece and nephew.");
    }

    private static void echo(String input) {
        System.out.println(input);
    }

    private static void sayHello() {
        String logo = "██████╗░░█████╗░░██████╗░███████╗██████╗░ \n"
                    + "██╔══██╗██╔══██╗██╔════╝░██╔════╝██╔══██╗ \n"
                    + "██████╔╝██║░░██║██║░░██╗░█████╗░░██████╔╝ \n"
                    + "██╔══██╗██║░░██║██║░░╚██╗██╔══╝░░██╔══██╗ \n"
                    + "██║░░██║╚█████╔╝╚██████╔╝███████╗██║░░██║ \n"
                    + "╚═╝░░╚═╝░╚════╝░░╚═════╝░╚══════╝╚═╝░░╚═╝ ";
        System.out.println("Hello its \n" + logo);
        System.out.println("What you wan? What you wan?");
    }

    private void list() {
        System.out.println("Nephew got a lot of things to do:");

        int i = 1;
        for (Task task: tasks) {
            System.out.println(String.valueOf(i) + ". " + task.toString());
            ++i;
        }
    }

    private void add(String taskName) {
        Task task = new Task(taskName);
        this.tasks.add(task);
        System.out.println("Nephew got new task to do:");
        System.out.println(task);
    }

    private void markAsDone(int taskNum) {
        Task task = tasks.get(taskNum - 1);
        task.markAsDone();

        System.out.println("Fuiyoh, nephew so efficient! Finished this task:");
        System.out.println(task);
    }

    private void unmarkAsDone(int taskNum) {
        Task task = tasks.get(taskNum - 1);
        task.unmarkAsDone();

        System.out.println("Haven't done yet, mark what mark? Unmarked this task:");
        System.out.println(task);
    }

    public static void main(String[] args) {
        Roger roger = new Roger();
        roger.sayHello();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                roger.sayGoodbye();
                break;
            } else if (input.equals("list")) {
                roger.list();
            } else if (input.startsWith("mark")) {
                // Handle invalid input
                int idx = Integer.parseInt(input.substring(5));
                roger.markAsDone(idx);
            } else if (input.startsWith("unmark")) {
                // Handle invalid input
                int idx = Integer.parseInt(input.substring(7));
                roger.unmarkAsDone(idx);
            } else {
                roger.add(input);
            }

        }

    }
}
