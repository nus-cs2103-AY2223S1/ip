package duke;

import java.util.Scanner;

public class Ui {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String LINE = "```````````````````````````````````````````````````````````````````";
    private static final String WELCOME_GREET = "Hello there! I am\n" + LOGO
            + "\nyour personal task tracking assistant!\nWhat can I do for you today?\n";
    private static final String EXIT_GREET = "Bye. Hope to see you again soon!\n";
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public static void showWelcome() {
        System.out.println(WELCOME_GREET + LINE);
    }
    public static void showLine() {
        System.out.println(LINE);
    }
    public void exit() {
        this.scanner.close();
        System.out.println(EXIT_GREET + LINE);
    }

    public String readUserInput() {
        return scanner.nextLine();
    }
    public void showError(String error) {
        System.out.println(error);
    }
}
