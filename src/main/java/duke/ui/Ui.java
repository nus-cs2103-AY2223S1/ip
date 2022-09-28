package duke.ui;

import java.util.Scanner;

public class Ui {

    private final String LINE = "------------------------------" +
            "--------------------";
    private final String WELCOME = "Hello I'm Duke" + "!!\n" +
            "What do you wanna chat about today?";
    private final String EXIT = "Bye. Looking forward to chating " +
            "with you soon again!";

    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String displayMessage(String message) {
        String text = "";
        text += message + "\n";
        return text;
    }

    public String displayWelcomeMessage() {
        return displayMessage(WELCOME);
    }

    public String getUserInput() {
        System.out.println("Please enter your command below: ");
        String userInput = sc.nextLine();
        return userInput;
    }

    public String displayExitMessage() {
        sc.close();
        return EXIT;
    }

}