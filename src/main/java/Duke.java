import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);

        String command = sc.nextLine();

        ArrayList<String> items = new ArrayList<>();

        while (!command.toLowerCase().equals("bye")) {
            if (command.toLowerCase().equals("list")) {
                for (int i = 0; i < items.size(); i++) {
                    int j = i + 1;
                    System.out.println(j + ". " + items.get(i));
                }
            } else {
                System.out.println("added: " + command);
                items.add(command);
            }
            command = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
