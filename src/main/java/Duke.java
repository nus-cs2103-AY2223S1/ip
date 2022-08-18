import java.util.Scanner;
import java.util.*;

public class Duke {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner input = new Scanner(System.in);
        List<String> l = new ArrayList<String>();

        while (true) {
            String line = input.nextLine();
            System.out.println("____________________________________________________________");
            if ("bye".equals(line)) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            }
            if ("list".equals(line)) {
                int count = 1;
                for (String s : l) {
                    System.out.println(String.format("%d. %s", count, s));
                    count += 1;
                }
            }
            else {
                System.out.println(String.format("added: %s", line));
                l.add(line);
            }
            System.out.println("____________________________________________________________");
        }
    }
}
