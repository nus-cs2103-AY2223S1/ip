import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static ArrayList<String> storage = new ArrayList<>();

    public static void printLine() {
        System.out.println("\t" + "____________________________________________________________");
    }

    public static void greet() {
        printLine();
        String logo = "\t" + "  ____        _        \n"
                + "\t" + " |  _ \\ _   _| | _____ \n"
                + "\t" + " | | | | | | | |/ / _ \\\n"
                + "\t" + " | |_| | |_| |   <  __/\n"
                + "\t" + " |____/ \\__,_|_|\\_\\___|\n";
        System.out.println("\t" + " Hello from\n" + logo);
        System.out.println("\t" +" Hello! I'm Duke\n");
        System.out.println("\t" +" What can I do for you?");
        printLine();
    }

    public static void store(String toStore) {
        storage.add(toStore);
        printLine();
        System.out.println("\t" + " added: " + toStore);
        printLine();
    }

    public static boolean isExit(String text) {
        return text.equals("bye");
    }

    public static boolean isList(String text) {
        return text.equals("list");
    }

    public static void exit() {
        printLine();
        System.out.println("\t" + " Bye. Hope to see you again soon!");
        printLine();
    }

    public static void listStorage() {
        printLine();
        for (int i = 0; i < storage.size(); i++) {
            System.out.println("\t" + " " + String.valueOf(i + 1) + ". " + storage.get(i));
        }
        printLine();
    }

    public static void startDuke() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            if (isExit(input)) {
                exit();
                break;
            } else if (isList(input)) {
                listStorage();
            } else {
                store(input);
            }
        }
    }

    public static void main(String[] args) {
        greet();
        startDuke();
    }
}
