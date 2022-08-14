import java.util.Scanner;
public class Duke {
    private static String[] list = new String[100];
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
            } else if (command.equals("list")) {
                for (int pos = 0; pos < 100; pos++) {
                    if (list[pos] == null) break;
                    System.out.println(pos + 1 + ". " + list[pos]);
                }
                System.out.print(">> ");
                command = input.next();
            } else {
                System.out.println("Added: " + command);
                for (int pos = 0; pos < 100; pos++) {
                    if (list[pos] == null) {
                        list[pos] = command;
                        break;
                    }
                }
                System.out.print(">> ");
                command = input.next();
            }
        }
    }
}
