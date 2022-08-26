package duke;

import java.util.Scanner;

public class Ui {

    private Scanner input = new Scanner(System.in);
    private String response = "";

    public Ui() {
    }

    public void start() {
        String greetings = "Good day to you! I'm Jake!\n"
                + "I will help you to keep track of your tasks!\n"
                + "The following are your saved tasks:";
        System.out.println(greetings);
    }

    public String getResponse() {
        return this.response;
    }

    public void askForInput() {
        this.response = input.nextLine();
    }

    public void showLoadingError() {
        System.err.println("File path does not contain a file!\n"
                + "But no worries, I have created one for you, add a task now!");
    }
}
