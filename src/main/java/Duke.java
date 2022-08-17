import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line = "____________________________________________________________";

        System.out.println(line + "\n"
                + "Dino:\n"
                + "     Hello! I'm Dino\n"
                + "     What can I do for you?\n");

        Scanner sc = new Scanner(System.in);
        String curr = "";

        while (!Objects.equals(curr, "bye")) {
            curr = sc.nextLine();
            if (!Objects.equals(curr, "bye")) {
                System.out.println("Dino:\n"
                        + "     " + curr + "\n");
            }
        }

        System.out.println("Dino:\n"
                + "     Bye. Hope to see you again soon!");
    }
}
