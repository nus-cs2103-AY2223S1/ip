import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String input = "";
        Scanner scan = new Scanner(System.in);
        System.out.println("Hello! I'm Duke" + "\n" + "What can I do for you?");

        while (!input.equals("bye")) {
            input = scan.nextLine(); // Reads user input
            System.out.println("\t" + input); // Prints user input
        }

        System.out.println("Bye. Hope to see you again soon!"); // Exits when user types "bye"
    }
}
