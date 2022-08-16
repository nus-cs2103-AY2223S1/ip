import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String line_divider = "____________________________________________________________";
        System.out.println("Hello from\n" + logo);
        System.out.println(line_divider);
        System.out.println("Hello I'm Duke\nWhat can I do for you?\n" + line_divider);


        while (true) {
            String curr = sc.nextLine();
            if (curr.equals("bye")) {
                System.out.println(line_divider + "\nBye. Hope to see you again soon!\n" + line_divider);
                break;
            } else if (curr.equals("list")) {
                System.out.println(line_divider);
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(i + 1 + ". " + list.get(i));
                }
                System.out.println(line_divider);
            } else {
                list.add(curr);
                System.out.println(line_divider + "\nadded: " + curr);
                System.out.println(line_divider);
            }
        }

    }
}