import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> db = new ArrayList<>(10);
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        greet();
        while (true) {
            String input = getInput(sc);
            if (processInput(input) == 0) {
                break;
            }
            System.out.println();
        }
    }

    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public static String getInput(Scanner sc) {
        System.out.println("What can I do for you?");
        return sc.nextLine();
    }

    public static int processInput(String s) {
        if (s.equals("bye")) {
            System.out.println("Bye. Hope to see you again!");
            return 0;
        } else if (s.equals("list")) {
            for (int i = 0; i < db.size(); i++) {
                System.out.println(i + 1 + ". " + db.get(i).toString());
            }
            return 1;
        } else {
            db.add(new Task(s));
            System.out.println("added: " + s);
            return 1;
        }
    }
}
