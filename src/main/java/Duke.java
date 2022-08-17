import jdk.swing.interop.SwingInterOpUtils;

import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello! I'm SmartBot\nWhat can I do for you?");
        String[] lst = new String[100];
        int counter = 0;
        while(true) {
            Scanner sc = new Scanner(System.in);
            String first = sc.next();
            if(first.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if(first.equals("list")) {
                for (int i = 1; i < counter + 1; i++) {
                    System.out.println((i) + ". " + lst[i - 1]);
                }
            }
            else {
                System.out.println("added: " + first);
                lst[counter] = first;
                counter++;
            }
        }
    }
}
