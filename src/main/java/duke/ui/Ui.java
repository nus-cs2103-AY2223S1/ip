package duke.ui;

import java.util.Scanner;

public class Ui {

    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public static void printLine() {
        System.out.println("\t" + "____________________________________________________________");
    }

    public static void greet() {
        printLine();
        String logo = "\t" + "  ____        _        \n"
                + "\t" + " |  _ \\ _   _| | _____ \n"
                + "\t" + " | | | | | | | |/ / _ \\\n"
                + "\t" + " | |_| | |_| |   <  __/\n"
                + "\t" + " |____/ \\__,_|_|\\_\\___|\n";
        System.out.println("\t" + " Hello from\n" + logo);
        System.out.println("\t" +" Hello! I'm Duke\n");
        System.out.println("\t" +" What can I do for you?");
        printLine();
    }

    public static void exit() {
        printLine();
        System.out.println("\t" + " Bye. Hope to see you again soon!");
        printLine();
    }

    public String readCommand() {
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        }
        return "";
    }

    public void formatMessage(String message) {
        System.out.println("\t " + message);
    }
}
