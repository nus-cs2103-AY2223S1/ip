import java.util.Scanner;
import java.util.ArrayList;

public class Bob {
    public static void main(String[] args) {
        ArrayList<String> tasks = new ArrayList<>(100);
        Scanner scanner = new Scanner(System.in);
        System.out.println("hey, i'm bob!\ndo you need help?");
        String reply = scanner.nextLine();

        while (!reply.equalsIgnoreCase("bye")) {
            if (reply.equalsIgnoreCase("list")) {
                int index = 1;
                for (String s : tasks) {
                    System.out.println(String.valueOf(index++) + ". " + s);
                }
            }
            else {
                System.out.println("now in your list: " + reply);
                tasks.add(reply);
            }
            reply = scanner.nextLine();
        }

        System.out.println("bye \nsee you again!");
    }
}
