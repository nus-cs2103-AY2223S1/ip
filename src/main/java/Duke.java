import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String command = scanner.next();
            switch (command) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                default:
                    System.out.println(command);
                    break;
            }
        }
    }
}
