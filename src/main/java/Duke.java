import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    static List<String> tasks = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Hello I'm Duke\n" + "What can I do for you?");
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    int index = i + 1;
                    System.out.println(index + ". " + tasks.get(i));
                }
            } else {
                tasks.add(command);
                System.out.println("added: " + command);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
