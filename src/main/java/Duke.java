import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello I'm Duke\n" + "What can I do for you?");
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                break;
            }
            System.out.println(command);
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
