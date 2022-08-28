package ip;

import java.util.Scanner;

/**
 * Handles all communications with the user.
 *
 * @author Jonathan Lam
 */
public class Ui {
    private Scanner input = new Scanner(System.in);
    private final String greeting = "  ___          ___  \n"
                                   + " (o o)        (o o) \n"
                                   + "(  V  ) DUKE (  V  )\n"
                                   + "--m-m----------m-m--";
    private final String bye = " ^ ^         \n"
                                 + "(O,O)        \n"
                                 + "(   ) BYE?   \n"
                                 + "-\"-\"---------";

    /**
     * Get the next line of input from the user.
     *
     * @return Scanner of the next line of user input.
     */
    public Scanner getNextLine() {
        return new Scanner(input.nextLine());
    }

    /**
     * Prints horizontal line to the output.
     */
    public void divider() {
        System.out.println("________________________________");
    }

    /**
     * Prints the given string to the output.
     * @param s
     */
    public void say(String s) {
        System.out.println(s);
    }

    /**
     * Prints the greeting message to the output.
     */
    public void greet() {
        System.out.println(greeting);
    }


    /**
     * Prints the farewell message to the output.
     */
    public void sayBye() {
        System.out.println(bye);
    }
}
