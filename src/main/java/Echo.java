/**
 * Represents an echo function which echoes whatever the user inputs.
 * CS2103T iP
 * AY22/23 Semester 1
 *
 * @author Perry Wong
 */

// Level 1

import java.util.Scanner;

public class Echo {
    /**
     * The method to echo the user's input.
     */
    public static void echo() {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        if (str.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            sc.close();
        } else {
            System.out.println(str);
            Echo.echo();
        }
    }
}
