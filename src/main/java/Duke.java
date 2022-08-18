import java.util.Scanner;
import java.util.ArrayList; 

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Duke.addList();
    }

    public static void echo() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Duke\n \tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");
        Scanner scanner = new Scanner(System.in);
        String input = "";

        while (true) {
            input = scanner.nextLine();
            if (input.toLowerCase().equals("bye")) {
                break;
            }
            System.out.println("\t____________________________________________________________");
            System.out.println("\t" + input);
            System.out.println("\t____________________________________________________________");
        }

        System.out.println("\t____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");
        scanner.close();
        return;
    }

    public static void addList() {
        ArrayList<String> list = new ArrayList<>();
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Duke\n \tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");
        Scanner scanner = new Scanner(System.in);
        String input = "";

        while (true) {
            input = scanner.nextLine();
            if (input.toLowerCase().equals("bye")) {
                break;
            }
            if (input.toLowerCase().equals("list")) {
                System.out.println("\t____________________________________________________________");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(String.format("\t%d: %s", i + 1, list.get(i)));
                }
                System.out.println("\t____________________________________________________________");
                continue;
            }
            System.out.println("\t____________________________________________________________");
            System.out.println("\tadded: " + input);
            System.out.println("\t____________________________________________________________");
            list.add(input);
        }
        
        System.out.println("\t____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");
        scanner.close();
        return;
    }
}
