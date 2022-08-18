import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke\n     What can I do for you?");
        System.out.println("    ____________________________________________________________\n");

        ArrayList<String> store = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        while (!input.equals("bye")) {
            System.out.println("    ____________________________________________________________");
            if (input.equals("list")) {
                for (int i=0; i < store.size(); i++) {
                    System.out.println("     " + (i+1) + ". " + store.get(i));
                }
                System.out.println("    ____________________________________________________________\n");
                input = sc.nextLine();
            } else {
                store.add(input);
                System.out.println("    " + " added: " + input);
                System.out.println("    ____________________________________________________________\n");
                input = sc.nextLine();
            }
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________\n");
    }
}
