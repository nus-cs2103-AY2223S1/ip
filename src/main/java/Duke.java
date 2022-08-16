import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Scanner in = new Scanner(System.in);

        while(true) {
            String input = in.next();
            String exitCode = "bye";
            if (input.equals(exitCode)) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else {
                System.out.println(input);
            }
        }

    }
}
