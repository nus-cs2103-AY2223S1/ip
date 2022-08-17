/**
 * A list that keeps track of what the user has input.
 * CS2103T iP
 * AY22/23 Semester 1
 *
 * @author Perry Wong
 */

// Level 2
import java.util.Scanner;

public class List {
    /**
     * The list that keeps track of what the user has input.
     */
    private final static Task[] list = new Task[100];

    /**
     * The number/integer that keeps track of the next index in the list that is not filled.
     */
    private static int index = 0;

    /**
     * The method that initialises and runs the list.
     */
    public static void run() {
        Scanner sc = new Scanner(System.in);
        if (sc.hasNext("mark")) {
            String mark = sc.next();
            int i = sc.nextInt();
            if (i > 0) {
                if (list[i - 1] != null) {
                    Task t = list[i - 1];
                    t.mark();
                }
            }
            List.run();
        } else if (sc.hasNext("unmark")) {
            String unmark = sc.next();
            int i = sc.nextInt();
            if (i > 0) {
                if (list[i - 1] != null) {
                    Task t = list[i - 1];
                    t.unmark();
                }
            }
            List.run();
        } else {
            String str = sc.nextLine();
            if (str.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                sc.close();
            } else if (str.equals("list"))  {
                for (int i = 0; i < list.length; i++) {
                    if (list[i] != null) {
                        System.out.println(i + 1 + ". " + list[i].getDescription());
                    }
                }
                List.run();
            } else {
                List.add(str);
            }
        }
    }

    public static void add(String str) {
        System.out.println("added: " + str);
        Task curr = new Task(str);
        list[index] = curr;
        index++;
        List.run();
    }
}
