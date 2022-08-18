import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void displayList() {
        System.out.println("Here are the tasks in your list.");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
    }

    public static void addTask(Task task) {
        tasks.add(task);
        System.out.println("Gotcha! I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in your list.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean hasNextInput = true;

        String logo = "_________                     ___\n"
                + "\\    ___ \\  ___________   ____\\_ |_________  ____\n"
                + "/    \\  \\/_/ __ \\_  __ \\_/ __ \\| __ \\_  __ \\/  _ \\\n"
                + "\\     \\___\\  ___/|  | \\/\\  ___/| \\_\\ \\  | \\(  (_) )\n"
                + " \\________/\\_____>__|    \\_____>_____/__|   \\____/\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");

        while (hasNextInput) {
            System.out.print("--> ");
            String input = scanner.nextLine();
            int taskIndex;

            try {
                switch (input.split(" ")[0]) {
                    case "bye":
                        hasNextInput = false;
                        scanner.close();
                        break;
                    case "list":
                        displayList();
                        break;
                    case "mark":
                        taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                        if (taskIndex < 0 || taskIndex >= tasks.size())
                            throw new DukeException("Please enter a valid task number!");
                        tasks.get(Integer.parseInt(input.split(" ")[1]) - 1).markAsDone();
                        break;
                    case "unmark":
                        taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                        if (taskIndex < 0 || taskIndex >= tasks.size())
                            throw new DukeException("Please enter a valid task number!");
                        tasks.get(Integer.parseInt(input.split(" ")[1]) - 1).markAsNotDone();
                        break;
                    default:
                        addTask(Task.createTask(input));
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please Enter a valid task number!");
            } catch (DukeException e) {
                System.out.println(e.toString());
            }
        }

        System.out.println("Goodbye! See you soon!");
    }
}
