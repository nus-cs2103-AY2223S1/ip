package duke.ui;

import duke.exception.DukeException;

import java.util.Scanner;

public class Ui {
    public final String LOGO = " ___  ___  __ __ \n"
            + "| . \\| __>|  \\  \\\n"
            + "|   /| _> |     |\n"
            + "|_\\_\\|___>|_|_|_|\n";
    public final String SPACER = "----------------------------------------------------";
    public final String WELCOME = "こんにちは (Konnichiwa)! Rem だよ! (I'm Rem!) :>\n"
            + "今日は何ができますか? (What can I do for you today?)\n";
    public final String GOODBYE = "またね! (See you soon!) <3";
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println(LOGO + "\n" + WELCOME + SPACER);
    }

    public void showGoodbye() {
        System.out.println(GOODBYE);
    }

    public void showError(String error) {
        System.out.println(error);
    }

    public String readCommand() {
        return sc.nextLine();
    }

}
