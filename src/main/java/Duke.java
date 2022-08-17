import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Hello there! My name's Duck");
        System.out.println("Please type in a command...");
        Scanner input = new Scanner(System.in);
        while (true) {
            String inputLine = input.nextLine();
            String[] inputArr = inputLine.split(" ");

            if (inputLine.equals("bye")) {
                System.out.println("Bye! See you next time!");
                input.close();
                return;
            } else if (inputLine.equals("list")) {
                int id = 1;
                for (Task task: tasks) {
                    System.out.println(id + "." + task.toString());
                    id += 1;
                }
            } else if (inputArr[0].equals("mark")) {
                int taskIndex = Integer.parseInt(inputArr[1]);
                Task currTask = tasks.get(taskIndex - 1); // label starting from 1
                currTask.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + currTask.toString());
            } else if (inputArr[0].equals("unmark")) {
                int taskIndex = Integer.parseInt(inputArr[1]);
                Task currTask = tasks.get(taskIndex - 1); // label starting from 1
                currTask.markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + currTask.toString());
            } else {
                System.out.println("added: " + inputLine);
                Task newTask = new Task(inputLine);
                tasks.add(newTask);
            }
        }
    }
}
