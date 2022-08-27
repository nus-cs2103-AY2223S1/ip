package duke;

import java.util.Scanner;

public class Ui {

    Scanner sc = new Scanner(System.in);

    public void showWelcome() {
        //Chatbot intro segment
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String intro = "Hewwo! I'm\n" + logo + "\nWhat can I do for you?";
        System.out.println(intro);;
    }

    public void showLine() {
        System.out.println("_____");
    }

    public String readCommand() {
        return sc.nextLine();
    }

}
