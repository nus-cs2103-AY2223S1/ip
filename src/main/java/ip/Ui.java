package ip;

import java.util.Scanner;

/**
 * <h1>User interaction handler</h1>
 * Handles all communications with the user.
 */
public class Ui {
    private Scanner input = new Scanner(System.in);
    private final String greeting = "  ___          ___  \n" +
                                    " (o o)        (o o) \n" +
                                    "(  V  ) DUKE (  V  )\n" +
                                    "--m-m----------m-m--";
    private final String bye = " ^ ^         \n" +
                                  "(O,O)        \n" +
                                  "(   ) BYE?   \n" +
                                  "-\"-\"---------";

    public Scanner getNextLine() {
        return new Scanner(input.nextLine());
    }

    public void divider() {
        System.out.println("________________________________");
    }

    public void say(String s) {
        System.out.println(s);
    }

    public void greet() {
        System.out.println(greeting);
    }

    public void sayBye() {
        System.out.println(bye);
    }
}
