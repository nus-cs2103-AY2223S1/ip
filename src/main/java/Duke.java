import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {

    // String array used to store tasks
    private static final List<Task> tasks = new ArrayList<>();

    // Currently, the main function takes in user input and echoes it to the user
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello, I am Duke. \nWhat can I do for you? :)\n=======================");
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();

            // Splits the input of the string into a string array
            String[] splitInput = input.split(" ");

            // For mark and unmark, the string array should be of length 2 only
            if (splitInput.length == 2 && (splitInput[0].equals("mark") || splitInput[0].equals("unmark"))) {
                int index = Integer.valueOf(splitInput[1]) - 1;
                if (splitInput[0].equals("mark")) {
                    tasks.get(index).markAsDone();
                    System.out.println("Nice! I've marked this task as having been completed:");
                    System.out.println(tasks.get(index));
                } else {
                    tasks.get(index).markAsUndone();
                    System.out.println("Okay, I've marked this task as not done yet:");
                    System.out.println(tasks.get(index));
                }
                continue;
            }

            if (input.toLowerCase().equals("bye")) {
                System.out.println("Bye! Hope to see you again soon!");
                break;
            } else if (input.toLowerCase().equals("list")) {
                int counter = 1;
                System.out.println("=======================");
                System.out.println("Here are the tasks that you have added to the list: ");
                for (Task task: tasks) {
                    if (task != null) {
                        System.out.println(counter + ". " + task);
                        counter++;
                    }
                }
                System.out.println("=======================");
            } else {
                tasks.add(new Task(input));
                System.out.println("=======================");
                System.out.println("added: " + input);
                System.out.println("=======================");
            }


        }

        return;

    }
}
