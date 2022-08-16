import java.util.Scanner;
import java.util.ArrayList;

public class Anya {
    static final String breakLine = "\n---------------------------------------------------------------------";

    public static void main(String[] args) {
        // Initialising variables
        Scanner sc = new Scanner(System.in);
        String userInput;
        String command;
        ArrayList<Task> tasks = new ArrayList<>();

        // Greet
        System.out.println("Hello! Anya is happy to meet you.\nHow can Anya help?" + breakLine);

        // Get user input
        userInput = sc.nextLine();

        while (!userInput.equals("bye")) {
            try {
                command = userInput.split(" ")[0];
                if (command.equals("list")) {
                    list(tasks);
                } else if (command.equals("mark")) {
                    int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    mark(tasks, index);
                } else if (command.equals("unmark")) {
                    int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    unmark(tasks, index);
                } else if (command.equals("delete")) {
                    int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    delete(tasks, index);
                } else if (command.equals("todo")) {
                    try {
                    String inputTask = userInput.split(" ", 2)[1];
                    Task task = new Todo(inputTask);
                    addTask(tasks, task);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new AnyaException("The description of a todo cannot be empty.");
                    }
                } else if (command.equals("deadline")) {
                    try {
                        String inputTask = userInput.split(" ", 2)[1];
                        String[] details = inputTask.split(" /by ");
                        Task task = new Deadline(details[0], details[1]);
                        addTask(tasks, task);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new AnyaException("The description/cut-off time of a deadline cannot be empty.");
                    }
                } else if (command.equals("event")) {
                    try {
                        String inputTask = userInput.split(" ", 2)[1];
                        String[] details = inputTask.split(" /at ");
                        Task task = new Event(details[0], details[1]);
                        addTask(tasks, task);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new AnyaException("The description/time of an event cannot be empty.");
                    }
                } else {
                    throw new AnyaException("Anya doesn't understand this command.");
                }

            } catch (AnyaException e) {
                System.out.println(e.getMessage() + breakLine);
            }

            userInput = sc.nextLine();
        }

        // Exit
        System.out.println("Anya is sad to see you leave. Please be back soon." + breakLine);
    }

    // Commands
    public static void addTask(ArrayList<Task> tasks, Task task) {
        tasks.add(task);
        System.out.println("Anya added: " + task);
        System.out.println("Anya sees that you have " + tasks.size() + " task(s) in your list." + breakLine);
    }

    public static void list(ArrayList<Task> tasks) {
        System.out.println("Anya is getting you your list...");
        for (int i = 0; i < tasks.size(); i++) {
            int num = i + 1;
            System.out.println(num + ". " + tasks.get(i).toString());
        }
        System.out.println(breakLine);
    }

    public static void mark(ArrayList<Task> tasks, int index) {
        Task task = tasks.get(index);
        task.markDone();
        System.out.println("Anya has marked this task as done: \n  " + task.toString() + breakLine);
    }

    public static void unmark(ArrayList<Task> tasks, int index) {
        Task task = tasks.get(index);
        task.markUndone();
        System.out.println("Anya has marked this task as uncompleted: \n  " + task.toString() + breakLine);
    }

    public static void delete(ArrayList<Task> tasks, int index) {
        Task removedTask = tasks.get(index);
        tasks.remove(index);
        System.out.println("Anya has removed this task : \n" + removedTask.toString() + breakLine);
    }
}
