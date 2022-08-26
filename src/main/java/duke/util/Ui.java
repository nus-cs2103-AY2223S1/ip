package duke.util;

import java.util.Scanner;

public class Ui {
    private static final String LINE = "\n----------------------------------------------------------------\n";
    private Scanner command;

    public Ui() {
        showGreet();
        this.command = new Scanner(System.in);
    }

    public String readInput() {
        return this.command.nextLine();
    }

    public void closeInput() {
        this.command.close();
    }

    public void showGreet() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println(LINE + "Hello! I'm Duke\n" + "What can I do for you?" + LINE + "\n");
    }


}
