import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        while (true) {
            String input = reader.next();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            } else {
                System.out.println(input);
            }
        }
    }
}
