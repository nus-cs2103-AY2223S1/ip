import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Display all stored tasks
     */
    public static void displayList() {
        System.out.println("Here are the tasks in your list.");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + tasks.get(i).toString());
        }
    }

    /**
     * Add task to tasks list
     * 
     * @param task Task to be stored in tasks list
     */
    public static void addTask(Task task) {
        tasks.add(task);
        System.out.println("Gotcha! I've added this task:");
        System.out.println("\t" + task);
        System.out.println("Now you have " + tasks.size() + " tasks in your list.");
    }

    /**
     * Removes specified task from tasks list
     * 
     * @param taskIndex Index of task to be removed
     * @throws DukeException if given index is out of bounds
     */
    public static void removeTask(int taskIndex) throws DukeException {
        if (taskIndex < 0 || taskIndex >= tasks.size())
            throw new DukeException("Please enter a valid task number!");
        Task task = tasks.remove(taskIndex);
        System.out.println("Noted. I've removed this task:");
        System.out.println("\t" + task);
    }

    /**
     * Changes the completed status of specified task
     * 
     * @param taskIndex Index of task to be changed
     * @param isDone    true if task is completed, false otherwise
     * @throws DukeException if given index is out of bounds
     */
    public static void changeTaskStatus(int taskIndex, boolean isDone) throws DukeException {
        if (taskIndex < 0 || taskIndex >= tasks.size())
            throw new DukeException("Please enter a valid task number!");
        if (isDone)
            tasks.get(taskIndex).markAsDone();
        else
            tasks.get(taskIndex).markAsNotDone();
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
                switch (Command.valueOf(input.split(" ")[0])) {
                    case bye:
                        hasNextInput = false;
                        scanner.close();
                        break;
                    case list:
                        displayList();
                        break;
                    case mark:
                        taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                        changeTaskStatus(taskIndex, true);
                        break;
                    case unmark:
                        taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                        changeTaskStatus(taskIndex, false);
                        break;
                    case delete:
                        taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                        removeTask(taskIndex);
                        break;
                    default:
                        addTask(Task.createTask(input));
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please Enter a valid task number!");
            } catch (IllegalArgumentException e) {
                System.out.println("🙁 OOPS! I'm sorry but I don't know what that means.");
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Goodbye! See you soon!");
    }
}
