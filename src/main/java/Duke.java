import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        List<String> lst = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        String text = input.nextLine();
        while (!text.equals("bye")) {
            if (text.equals("list")) {
                for (int i = 0; i < lst.size(); i++) {
                    System.out.printf("\t%d. %s\n", i+1, lst.get(i));
                }
            } else {
                System.out.printf("\tAdded: %s\n", text);
                lst.add(text);
            }
            text = input.nextLine();
        }
        System.out.println("\tBye! Hope to see you again soon!");
    }
}
