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
    private static String[] list = new String[100];

    /**
     * The number/integer that keeps track of the next index in the list that is not filled.
     */
    private static int index = 0;

    /**
     * The method that adds the item the user has input into the list (if applicable)
     */
    public static void add() {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        if (str.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            sc.close();
        } else if (str.equals("list"))  {
            for (int i = 0; i < list.length; i++) {
                if (list[i] != null) {
                    System.out.println(i + 1 + ". " + list[i]);
                }
            }
            List.add();
        } else {
            System.out.println("added: " + str);
            list[index] = str;
            index++;
            List.add();
        }
    }
}
