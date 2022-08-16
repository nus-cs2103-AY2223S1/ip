import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        List<String> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (scanner.hasNextLine()) {
            input = scanner.next();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                tasks.forEach(System.out::println);
            } else {
                tasks.add((tasks.size() + 1) + ". " + input);
                System.out.println("added: " + input);
            }
        }
    }
}
