import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner dukeScanner = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Hello i'm Duke\nHow may i help you today?");

        String input;
        while (true) {
            input = dukeScanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                System.out.println(input);
            }
        }
    }
}
