import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static ArrayList<String> storage = new ArrayList<String>();

    public static void reply() {
        Scanner echo = new Scanner(System.in);
        String response = echo.nextLine();
        if (response.equals("bye")) {
            System.out.println("-----------------------------------------------");
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println("-----------------------------------------------");
            storage.clear();
            return;
        } else if (response.equals("list")) {
            System.out.println("-----------------------------------------------");
            for (int i = 0; i < storage.size(); i++) {
               System.out.println((i + 1) + ". " + storage.get(i));
            }
            System.out.println("-----------------------------------------------");
            reply();
        } else {
            storage.add(response);
            System.out.println("-----------------------------------------------");
            System.out.println("added: " + response);
            System.out.println("-----------------------------------------------");
            reply();
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n" );
        reply();
    }
}
