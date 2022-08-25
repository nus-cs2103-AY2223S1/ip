package main.java.duke;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Ui {

    private static final String line = "-----------------------------";
    Scanner sc = new Scanner(System.in);

    public Ui() {
    }

    // Duke's self-intro
    public void saysHi() {
        System.out.println(line + "\n" +
                "Hello! I'm Duke\n" +
                "What can I do for you\n" +
                line + "\n");
    }

    // Duke's parting words
    public void saysBye() {
        System.out.println(line + "\n" +
                "Bye. Hope to see you again soon!\n" +
                line + "\n");
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showLoadingError() throws FileNotFoundException {
        throw new FileNotFoundException("File cannot be found!");
    }

}
