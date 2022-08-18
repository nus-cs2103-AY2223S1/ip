import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        ArrayList<String> tasks = new ArrayList<String>(100);
        Scanner sc = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm Lebron.");
        System.out.println("What can I do for you?");

        while (true) {
            String str = sc.nextLine();
            if (str.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            if (str.equals("list")) {
                for (int i = 0, d = 1; i < tasks.size(); i++, d++) {
                    String statement = String.format("%d. ", d);
                    System.out.println(statement + tasks.get(i));
                }
            }
            else {
                tasks.add(str);
                System.out.println("added: " + str);
            }
        }

    }
}
