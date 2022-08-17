import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner reply = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");

        String currreply = reply.nextLine();

        while (!currreply.equals("bye")) {
            System.out.println(currreply + "\n");
            currreply = reply.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
