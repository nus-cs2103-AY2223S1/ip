import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("------------------------------");
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println("------------------------------");
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> commands = new ArrayList<>();
        while(true) {
            String command = scanner.next();
            System.out.println("------------------------------");
            switch (command) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                case "list":
                    for (int i = 0; i < commands.size(); i++) {
                        System.out.printf("%d. %s\n", i + 1, commands.get(i));
                    }
                    break;
                default:
                    commands.add(command);
                    System.out.printf("added: %s\n", command);
                    break;
            }
            System.out.println("------------------------------");
        }
    }
}
