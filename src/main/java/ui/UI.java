package ui;

public class UI {

    /**
     * Creates a new UI.
     */
    public UI () {

    }

    public void showGotTask() {
        System.out.println("----------------------\nOooo looks like you have tasks already planned\n" +
                "----------------------\n");
    }

    public void showNoTask() {
        System.out.println("----------------------\nA fresh start for you :)\n----------------------\n");
    }

    public void welcomeMessage() {
        System.out.println("----------------------\n" +
                "Hello! I'm HelperBot\nWhat can I do for you?\n----------------------\n");
    }

    public String showInaccurateInput() {
        String out = "----------------------\nI am sorry pls input again\n" +
                "----------------------\n";
        return out;
    }



}
