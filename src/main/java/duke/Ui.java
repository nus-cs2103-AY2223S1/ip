package duke;

import java.util.Scanner;

/**
 * The user interactive platform.
 *
 * @author Lan Jingbo, Jerry
 */
class Ui {
    protected Scanner sc = new Scanner(System.in);

    /**
     * The greet function.
     */
    public String greet() {
        String greet = "Hello! I'm ZMZ\n"
                + "What can I do for you?";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return (greet);
    }

    /**
     * to scan the nextline.
     *
     * @return the nextline
     */
    public String requirement() {
        return sc.nextLine();
    }

    /**
     * To check whether the requirement has next line.
     * @return if it has next line.
     */
    public boolean hasNextReq() {
        return sc.hasNextLine();
    }
}