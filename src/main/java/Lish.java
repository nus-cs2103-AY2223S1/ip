import java.util.ArrayList;
import java.util.Scanner;

public class Lish {

    public static String breakLine = "____________________________________________________________\n";

    public static void printResponse(String response) {
        System.out.println(breakLine);
        System.out.println(response);
        System.out.println(breakLine);
    }

    public static ArrayList<Task> taskList = new ArrayList<Task>();

    public static void printTaskList() {
        System.out.println(breakLine);
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= taskList.size(); ++i) {
            System.out.println(i + ". " + taskList.get(i - 1));
        }
        System.out.println(breakLine);
    }

    public static void markTaskAsDone(int index) {
        System.out.println(breakLine);
        taskList.get(index).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskList.get(index));
        System.out.println(breakLine);
    }

    public static void markTaskAsNotDone(int index) {
        System.out.println(breakLine);
        taskList.get(index).markAsNotDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(taskList.get(index));
        System.out.println(breakLine);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        boolean stopLish = false;
        String greeting = "Hello! I'm Lish\n" + "What can I do for you?\n";
        printResponse(greeting);



        while (!stopLish) {
            String input = sc.nextLine();

            switch (input) {
                case "":
                    break;
                case "bye":
                    printResponse(input);
                    stopLish = true;
                    break;
                case "list":
                    printTaskList();
                    break;
                default:
                    String[] commands = input.split(" ");

                    if (commands[0].equals("mark") || commands[0].equals("unmark")) {
                        int index = Integer.parseInt(commands[1]) - 1;
                        if (commands[0].equals("mark")) {
                            markTaskAsDone(index);
                        } else {
                            markTaskAsNotDone(index);
                        }
                    } else {
                        Task newTask = new Task(input);
                        taskList.add(newTask);
                        printResponse("added: " + input);
                    }
            }
        }
    }
}
