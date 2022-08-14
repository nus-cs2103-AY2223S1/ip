import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Hello! I'm Duke.\nWhat can I do for you?");
        System.out.print(">> ");
        String command = input.next();
        while (true) {
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                input.close();
                break;
            } else {
                System.out.println(command);
                System.out.print(">> ");
                command = input.next();
            }
        }
    }
}
