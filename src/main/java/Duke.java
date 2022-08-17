import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        Scanner myObj = new Scanner(System.in);
        String input = myObj.nextLine();  // Read user input
        while (!input.equals("bye")) {
            System.out.println(input);  // Output user input
            input = myObj.nextLine(); // Read next user input
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
