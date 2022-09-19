package duke.ui;

import java.util.Scanner;

public class Ui {

    private final String LINE = "------------------------------" +
            "----------------------------------";
    private final String WELCOME = "\tHello I'm Duke" + "!!\n" +
            "What do you wanna chat about today?";
    private final String EXIT = "\t Bye. Looking forward to chating " +
            "with you soon again!";

    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void displayMessage(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }

    public void displayWelcomeMessage() {
        displayMessage(WELCOME);
    }

    public String getUserInput() {
        System.out.println("Please enter your command below: ");
        String userInput = sc.nextLine();
        return userInput;
    }

    public void displayExitMessage() {
        displayMessage(EXIT);
        sc.close();
    }

}