import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        Scanner myObj = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<String> ();
        int number;
        String input = myObj.nextLine();  // Read user input
        while (!input.equals("bye")) {
            if (!input.equals("list")) {
                tasks.add(input);
                System.out.println("added: " + input);  // Output user input

            } else {
                for (String task : tasks) {
                    number = tasks.indexOf(task);
                    System.out.println(String.valueOf(number + 1) + ". " + task);
                }
            }
            input = myObj.nextLine(); // Read next user input
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
