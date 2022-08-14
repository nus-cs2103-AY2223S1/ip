import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void printOut(String str) {
        String line = "____________________________________________________________\n";
        System.out.println(line + str + "\n" + line);
    }

    public static void main(String[] args) {
        ArrayList<String> tasks = new ArrayList<>();
        Scanner input = new Scanner(System.in);

        printOut("Hello! I'm Duke.\n" +
                "What can I do for you?");
        String next = input.nextLine();

        while (!next.equals("bye")) {
            if (next.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
                System.out.println("____________________________________________________________\n");
            } else {
                tasks.add(next);
                printOut("added: " + next);
            }
            next = input.nextLine();
        }

        printOut("See you later. Bye!");
    }
}