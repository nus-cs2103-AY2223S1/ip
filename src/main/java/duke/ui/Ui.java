package duke.ui;

import java.util.Scanner;

public class Ui {
    Scanner s;
    public Ui() {
        this.s = new Scanner(System.in);
    }

    public void showWelcomeMessage() {
        System.out.println("Hello! I'm Duke\n"+"What can I do for you?");
    }

    public void showFileCreatingError() {
        System.out.println("Sorry, something went wrong when opening the file.");
    }
    public void showGoodbyeMessage() {
        System.out.println("Goodbye, hope to see you soon.");
    }

    public String readCommand() {
        String res = s.nextLine();

        return res;
    }
    public void showDukeException(String s){
        System.out.println(s);
    }
}
