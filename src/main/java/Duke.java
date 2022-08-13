import java.util.Scanner;

public class Duke {

    public static void reply() {
        Scanner echo = new Scanner(System.in);
        String response = echo.nextLine();
        if (response.equals("bye")) {
            System.out.println("-----------------------------------------------");
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println("-----------------------------------------------");
            return;
        } else {
            System.out.println("-----------------------------------------------");
            System.out.println(response);
            System.out.println("-----------------------------------------------");
            reply();
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n" );
        reply();
    }
}
