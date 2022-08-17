import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("hey, i'm bob!\ndo you need help?");
        String reply = scanner.nextLine();

        while (!reply.equalsIgnoreCase("bye")) {
            System.out.println(reply);
            reply = scanner.nextLine();
        }

        System.out.println("bye \nsee you again!");
    }
}
