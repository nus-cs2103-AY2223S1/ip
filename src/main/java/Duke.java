import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static List<String> tasks = new ArrayList<>();

    public static void printLine() {
        System.out.println("-".repeat(100));
    }

    public static void greetings() {
        printLine();
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        printLine();
    }

    public static void printBye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    public static void listAllItems() {
        printLine();
        for(int i = 0; i < tasks.size(); i++) {
            int number = i + 1;
            System.out.println(number + ". " + tasks.get(i));
        }
        printLine();
    }

    public static void addAnItem(String item) {
        tasks.add(item);
        printLine();
        System.out.println("added: " + item);
        printLine();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

       greetings();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            switch(input) {
                case "bye":
                    printBye();
                    return;
                case "list":
                    listAllItems();
                    break;
                default:
                    addAnItem(input);
                    break;
            }
        }
    }
}
