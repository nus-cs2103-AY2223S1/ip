import java.util.Scanner;

public class Duke {

    private static boolean isClosed = false;

    public static void main(String[] args) {
        greetingMessage();
        Scanner sc = new Scanner(System.in);
        while (!isClosed) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                bye();
            } else {
                echo(command);
            }
        }
    }

    private static void greetingMessage() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    private static void bye() {
        isClosed = true;
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void echo(String msg) {
        System.out.println(msg);
    }
}