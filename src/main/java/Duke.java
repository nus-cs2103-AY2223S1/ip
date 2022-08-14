import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke What can I do for you?");
        boolean flag = false;
        Scanner in = new Scanner(System.in);
        while (!flag) {
            String output = in.nextLine();
            if (output.equals("bye")) {
                System.out.println("_________________________________________________________________________");
                System.out.println("        Bye. Hope to see you again soon!");
                System.out.println("_________________________________________________________________________");
                flag = true;

            }
            else {
                System.out.println("_________________________________________________________________________");
                System.out.println("       Da myth: " + output);
                System.out.println("_________________________________________________________________________");
            }
        }
    }
}
