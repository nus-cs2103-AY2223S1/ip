import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static char triangle = '\u25B8';
    public static String start = triangle + " ";
    public static ArrayList<String> toDo = new ArrayList<>(100);

    public static void printList() {
        int x = 1;
        for (String task : toDo) {
            System.out.println("  " + x + ". " + task);
            x++;
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(start + "Hi, I'm Duke!\n  What would you like to do today?");

        Scanner sc = new Scanner(System.in);
        while (true) {

            String input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println(start + "Bye! I hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                printList();
            } else {
                toDo.add(input);
                System.out.println(start + "added: " + input);
            }
        }
    }
}
