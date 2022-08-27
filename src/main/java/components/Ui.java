package components;

import components.Parser;

import java.util.Scanner;

public class Ui {

    private String userInput;

    public Ui() {
        System.out.println("What can I do for you?");
    }

    public void getPrompt(Scanner sc) {
            String line = "";
            while (sc.hasNext()) {
                try {
                    line = sc.nextLine();
                    Parser.parseLine(line);
                } catch (DukeException e) {
                    e.printStackTrace();
                }
            }
    }


    public void showLoadingError() {
        System.out.println("duke.txt not found!");
    }

    public void showExceptionError(Exception e) {
        System.out.println(e.getMessage());
    }


}
