package duke;

import java.util.Scanner;

public class Ui {

    public void showWelcome() {
        String intro = "Hello! My name is GustavoBot, but you can call me Gus\n"
                + "How may I help you today?";

        System.out.println(intro);
    }

    public String readCommand() {
        Scanner scanObj = new Scanner(System.in);
        String fullCommand = scanObj.nextLine();
        return fullCommand;
    }

    public void printMessage(String message) {
        System.out.println(message);
    }
}
