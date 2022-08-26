package duke.util;

import java.util.Scanner;

public class Ui {
    private static final String HORIZONTAL_LINE = "  ____________________________________________________________";
    private Scanner commands;

    public Ui() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(HORIZONTAL_LINE + "\n  Hello! I'm Duke\n  What can I do for you?\n" + HORIZONTAL_LINE);
        this.commands = new Scanner(System.in);
    }

    public String getUserCommand() {
        return commands.nextLine();
    }
}

