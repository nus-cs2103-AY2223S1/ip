package duke.util;

import java.util.Scanner;

//Deals with interactions with the user
public class Ui {
    private Scanner scanner;
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        String logo = " _           _        \n"
                + "| |    _   _| | _____ \n"
                + "| |   | | | | |/ / _ \\\n"
                + "| |___| |_| |   <  __/\n"
                + "|_____|\\__,_|_|\\_\\___|\n";
        System.out.println("__________________________________________________");
        System.out.println(("Hola Amigo! My name is\n" + logo));
        System.out.println(("How may I assist you today?"));
        System.out.println("__________________________________________________");
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
