import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("------------------------------");
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println("------------------------------");
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String command = scanner.next();
            System.out.println("------------------------------");
            switch (command) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                default:
                    System.out.println(command);
                    break;
            }
            System.out.println("------------------------------");
        }
    }
}
