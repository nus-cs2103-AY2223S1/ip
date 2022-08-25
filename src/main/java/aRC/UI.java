package arc;

import java.util.Scanner;

public class UI {
    /**
     * Prints hello message to the screen
     */
    public void hello() {
        System.out.println("Hello! I'm aRC! (°▽°)/\nWhat can I do for you?");
    }

    /**
     * Print goodbye message to the screen
     */
    public void bye() {
        System.out.println("Bye. Hope to see you again soon! ʘ ͜ʖ ʘ");
    }

    public void printException(Exception e) {
        e.printStackTrace();
    }

    public String readInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("\n");
        return sc.nextLine();
    }
}
