import java.util.Scanner;
import java.util.ArrayList;

public class Anya {
    static final String breakLine = "\n---------------------------------------------------------------------";

    public static void main(String[] args) {
        // Initialising variables
        Scanner sc = new Scanner(System.in);
        String command;
        ArrayList<Task> tasks = new ArrayList<>();

        // Greet
        System.out.println("Hello! Anya is happy to meet you.\nHow can Anya help?" + breakLine);

        // Get command
        command = sc.nextLine();

        while (!command.equals("bye")) {
            if (command.equals("list")) {
                list(tasks);
            } else if (command.split(" ")[0].equals("mark")) {
                int index = Integer.parseInt(command.split(" ")[1]) - 1;
                mark(tasks, index);
            } else if (command.split(" ")[0].equals("unmark")) {
                int index = Integer.parseInt(command.split(" ")[1]) - 1;
                unmark(tasks, index);
            } else {
                add(tasks, command);
            }

            command = sc.nextLine();
        }

        // Exit
        System.out.println("Anya is sad to see you leave. Please be back soon." + breakLine);
    }

    // Commands
    public static void add(ArrayList<Task> tasks, String task) {
        tasks.add(new Task(task));
        System.out.println("Anya added: " + task + breakLine);
    }

    public static void list(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            int num = i + 1;
            System.out.println(num + ". " + tasks.get(i).toString());
        }
        System.out.println(breakLine);
    }

    public static void mark(ArrayList<Task> tasks, int index) {
        Task task = tasks.get(index);
        task.markDone();
        System.out.println("Anya has marked this task as done: \n  " + task.toString());
    }

    public static void unmark(ArrayList<Task> tasks, int index) {
        Task task = tasks.get(index);
        task.markUndone();
        System.out.println("Anya has marked this task as uncompleted: \n  " + task.toString());
    }
}
