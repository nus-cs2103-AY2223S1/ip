package ui;

import exceptions.DukeException;

import java.util.Scanner;

public class Ui {
    private final Scanner sc;

    private void printSeparator() {
        System.out.println("    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public void printMsg(String msg) {
        printSeparator();
        System.out.println("     " + msg);
        printSeparator();
    }

    public void printMultiMsg(String[] msgs) {
        printSeparator();
        for (String msg : msgs) {
            System.out.println("     " + msg);
        }
        printSeparator();
    }

    public Ui() {
        sc = new Scanner(System.in);
    }

    public String readCommand() throws DukeException {
        try {
            return sc.nextLine();
        } catch (Exception e) {
            throw new DukeException("No more input");
        }
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printMultiMsg(new String[]{"Hello my name is Duke", "What can I do for you?"});
    }

    public void showError(String msg) {
        printMsg("☹ OOPS!!! " + msg);
    }

    public void showLoadingError() {
        showError("File could not be loaded. Creating new file.");
    }
}
