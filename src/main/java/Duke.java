import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner reply = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");

        String currreply = reply.nextLine();
        ArrayList<String> list = new ArrayList<>(100);

        while (!currreply.equals("bye")) {
            if (currreply.equals("list")) {
                list.forEach(n -> System.out.println((list.indexOf(n) + 1) + ". " + n));
                System.out.println("\n");
                currreply = reply.nextLine();
            } else {
                list.add(currreply);
                System.out.println("added: " + currreply + "\n");
                currreply = reply.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
