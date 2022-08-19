import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");

        ArrayList<String> text = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        while (true) {
            String s = in.nextLine();
            if ("bye".equalsIgnoreCase(s)) {
                break;
            }
            if ("list".equalsIgnoreCase(s)) {
                int length = text.size();
                for (int i = 0; i < length; i++) {
                    System.out.println(i + 1 + ". " + text.get(i));
                }
            }
            else {
                text.add(s);
                System.out.println("added: " + s);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");

        in.close();
    }
}
