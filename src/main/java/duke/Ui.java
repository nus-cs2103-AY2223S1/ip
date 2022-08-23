package duke;

import java.util.Scanner;

public class Ui {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String LOGO =
              " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine();
    }


    public static void showMsg(StringBuilder stringBuilder) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(stringBuilder);
        System.out.println(HORIZONTAL_LINE);
    }

    public static void showError(String error) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(error);
        System.out.println(HORIZONTAL_LINE);
    }

    public static void showWelcomeMsg() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(LOGO);
        System.out.println("How may I help you?");
        System.out.println(HORIZONTAL_LINE);
    }
}
