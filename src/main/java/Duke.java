import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        // Variables
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        ArrayList<Task> taskList = new ArrayList<>();
        int counter = 1;

        // Introduction message
        greetUser();

        while (true) {
            input = sc.nextLine();
            String[] inputWords = input.split(" ", 2);
            String commandWord = inputWords[0];

            // Print out goodbye message and exits the program
            if (input.equals("bye")) {
                exitJukebox();
                break;
            }

            // Print out the task list
            if (input.equals("list")) {
                printList(taskList);
                continue;
            }

            // Mark the given task
            if (commandWord.equals("mark")) {
                String contents = inputWords[1];
                int taskNumberInt = Integer.parseInt(contents);
                taskList.get(taskNumberInt - 1).mark();

                System.out.println(String.format("Goodjob! This task is now completed :)\n" + "%s", taskList.get(taskNumberInt - 1)));
                continue;
            }

            // Unmark the given task
            if (commandWord.equals("unmark")) {
                String contents = inputWords[1];
                int taskNumberInt = Integer.parseInt(contents);
                taskList.get(taskNumberInt - 1).unmark();

                System.out.println(String.format("Oh... OK, I'll mark this task as uncompleted!\n" + "%s", taskList.get(taskNumberInt - 1)));
                continue;
            }

            // Add a new task to the list
            else {
                System.out.println("Okay!\n" + "Added: " + input);
                taskList.add(new Task(input, counter));
                counter++;
            }
        }
    }

    // Prints message to greet the user
    private static void greetUser() {
        System.out.println("Hello! I'm Jukebox :)\n" + "What can I do for you today?");
    }

    // Prints message to exit the program
    private static void exitJukebox() {
        System.out.println("Aww... OK, Hope to see you again!");
    }

    // Print out task list iteratively
    private static void printList(ArrayList<Task> taskList) {
        System.out.println("These are your current tasks! :)");
        int i = 1;
        for (Task t : taskList) {
            String s = String.format("%d.%s", t.getTaskNumber(), t);
            System.out.println(s);
            i++;

            if (t == null) {
                break;
            }
        }
    }
}

