package meowmeow;

import java.util.Scanner;

public class Ui {

    private Scanner sc = new Scanner(System.in);

    public void showWelcome() {
        //Chatbot intro segment
        String name = "MeowMeow (=^ↀWↀ^=)";

        String intro = "Hewwo! I'm\n" + name + "\nWhat do you need meow to do?";
        System.out.println(intro);;
    }

    public void showLine() {
        System.out.println("_____");
    }

    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Meowmeow heard: " + input;
    }

}
