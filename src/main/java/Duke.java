import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line = "Dino:\n";
        String[] ls = new String[100];
        int size = 0;

        System.out.println(line
                + "     Hello! I'm Dino\n"
                + "     What can I do for you?\n");

        Scanner sc = new Scanner(System.in);
        String curr = "";

        while (!Objects.equals(curr, "bye")) {
            curr = sc.nextLine();
            if (!Objects.equals(curr, "bye")) {
                if (Objects.equals(curr, "list")) {
                    System.out.println("Dino:");
                    for (int i = 0; i < size; i++) {
                        System.out.println("     " + (i + 1) + ". " + ls[i]);
                    }
                } else {
                    ls[size] = curr;
                    size++;
                    System.out.println(line
                            + "     added: " + curr + "\n");
                }
            }
        }

        System.out.println(line
                + "     Bye. Hope to see you again soon!");
    }
}
