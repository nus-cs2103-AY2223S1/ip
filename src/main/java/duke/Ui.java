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
    public void greet() {
        String greet = "Hello! I'm Lan\n"
                + "What can I do for you?";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(greet + "\n" + logo);
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