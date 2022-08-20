import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        List<String> storage = new ArrayList<>();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (command.equals("list")) {
                ListIterator<String> listIterator = storage.listIterator();
                while (listIterator.hasNext()) {
                    System.out.println(listIterator.nextIndex() + 1 + ". " + listIterator.next());
                }
            } else {
                storage.add(command);
                System.out.println("added: " + command);
            }
        }
    }
}
