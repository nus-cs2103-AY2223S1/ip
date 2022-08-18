import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner input = new Scanner(System.in);
        while (true) {
            String line = input.nextLine();
            System.out.println("____________________________________________________________");
            if ("bye".equals(line)) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            }
            System.out.println(line);
            System.out.println("____________________________________________________________");
        }
    }
}
