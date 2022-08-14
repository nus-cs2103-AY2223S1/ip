import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // greet the user
        System.out.println("Hello! I'm Pip :)\nWhat can I do for you?");

        // echo commands entered by the user
        Scanner in = new Scanner(System.in);
        while (true) {
            String input = in.nextLine();

            if (input.equals("bye")) {
                // exit when the user types "bye"
                in.close();
                System.out.println("Goodbye and see you again soon!");
                break;
            } else {
                System.out.println(input);
            }
        }
    }
}
