import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static ArrayList<String> data = new ArrayList<String>();

    public static void addItem(String item) {
        System.out.println("added: " + item);
        data.add(item);
    }

    public static void listItems() {
        for (int i = 0; i < data.size(); i++) {
            int index = i + 1;
            System.out.println(index + ". " + data.get(i));
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (command.equals("list")) {
                listItems();
            } else {
                addItem(command);
            }
        }
    }
}
