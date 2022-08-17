import java.util.Objects;
import java.util.Scanner;

public class Duke {

    public static void echoUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\t-----------------------------------------------");
        System.out.println("\tHello! I'm Duke Dukem\n\tWhat can I do for you?");
        System.out.println("\t-----------------------------------------------");

        while (scanner.hasNext()) {
            String line = scanner.nextLine();

            if (line.equals("bye")) {
                scanner.close();
                System.out.println("\t-----------------------------------------------");
                System.out.println("\tBye. Hope to see you again soon!");
                System.out.println("\t-----------------------------------------------");
                break;
            } else {
                System.out.println("\t-----------------------------------------------");
                System.out.println("\t" + line);
                System.out.println("\t-----------------------------------------------");
            }
        }
    }
    public static void main(String[] args) {
        echoUser();
    }
}
