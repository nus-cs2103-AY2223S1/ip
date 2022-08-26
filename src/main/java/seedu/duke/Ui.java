package seedu.duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Ui {

    public static final String INTRO = "Hello! I'm Duke\nWhat can I do for you?";
    public static final String DIVIDER = "____________________________________________________";
    public static final String GOODBYE = "Bye. Hope to see you again soon!";


    public static void showLoadingError() {

    }

    public static void showWelcomeMessage() {
        System.out.println(String.format("%s\n%s\n%s", DIVIDER, INTRO, DIVIDER));
    }

    public static void showGoodbyeMessage() {
        System.out.println(String.format("%s\n%s\n%s", DIVIDER, GOODBYE, DIVIDER));
    }

    public static String getUserCommand(String input) {
        String[] inputArr = input.split(" ");
        String command = inputArr[0];
        return command;
    }

}
