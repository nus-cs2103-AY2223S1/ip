import java.util.Scanner;

public class Duke {

    /**
     *  This method prints out a horizontal line 40 dashes long.
     */
    public static void printLine() {
        for(int i = 0; i < 40; i++) {
            System.out.print("_");
        }
        System.out.println();
    }

    /**
     * This method prints out the string s, wrapped within 2 horizontal lines.
     * @param s The given string to be printed.
     */
    public static void say(String s) {
        printLine();
        System.out.println(s);
        printLine();
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        say("Hello! I'm Pawl\n What can I do for you?");

        String first = scn.next();
        while (!first.equals("bye")){
            say(first);
            first = scn.next();
        }

        say("Bye. Hope to see you again soon!");

    }
}
