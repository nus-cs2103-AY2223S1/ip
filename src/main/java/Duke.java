import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        // Variables
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        ArrayList<Task> taskList = new ArrayList<>();

        // Introduction message
        greetUser();

        while (true) {
            // Print out goodbye message and exits the program
            if (input.equals("bye")) {
                System.out.println("Bye~ Hope to see you again!");
                break;
            }

            // Print out the task list
            if (input.equals("list")) {
                printList(taskList);
                input = sc.nextLine();
            }

            else {
                System.out.println("added: " + input);
                taskList.add(new Task(input));
                input = sc.nextLine();
            }
        }
    }

    // Message to greet the user
    private static void greetUser() {
        System.out.println("Hello! I'm Jukebox\n" + "What can I do for you?");
    }

    // Print out task list iteratively
    private static void printList(ArrayList<Task> taskList) {
        int i = 1;
        for (Task t : taskList) {
            String s = String.format("%d. %s", i, t);
            System.out.println(s);
            i++;

            if (t == null) {
                break;
            }
        }
    }
}

