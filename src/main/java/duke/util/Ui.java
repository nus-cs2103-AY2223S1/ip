package duke.util;

import java.util.Scanner;

//Deals with interactions with the user
/**
 * Insert Javadocs
 */
public class Ui {
    private Scanner scanner;
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Insert Javadocs
     */
    public String showWelcome() {
        String logo = " _           _        \n"
                + "| |    _   _| | _____ \n"
                + "| |   | | | | |/ / _ \\\n"
                + "| |___| |_| |   <  __/\n"
                + "|_____|\\__,_|_|\\_\\___|\n";

        return "__________________________________________________\n"
                + "Hola Amigo! My name is\n"
                + logo
                + "\n"
                + "How may I assist you today?\n"
                + "__________________________________________________";
    }

    public String readCommand() {
        return this.scanner.nextLine();
    }

    public void showLine() {
        System.out.println("__________________________________________________");
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }
}
