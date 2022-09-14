package Duke.UI;

import Duke.Exceptions.DukeException;

import java.util.Scanner;



public class CLIUi {

    private static final String LINE = "______________________________________________________";
    private static final String GREETINGS = "Hello! I am Duke.\n" + "Nice to Meet you, how can I help you ?";
    private static final String HELP = "You can type \"help\" to see all my services and get more information:)";
    private static final String SAVED = "Your data is saved automatically.";
    private static final String ENDING = "Thank you for your using. Hope you have a good time:)";


    private Scanner sc = new Scanner(System.in);

    public String readInput() {
        String input;

        while (true) {
            input = sc.nextLine().strip();
            if (input.trim().isEmpty() == false) {
                break;
            }
            showInvalidInput();
        }

        return input;

    }

    public void showInvalidInput() {
        System.out.println(LINE);
        System.out.println("invalue input");
        System.out.println(LINE);
    }
    public void showLoadingError() {
        System.out.println(LINE);
        System.out.println("Loading Errors");
        System.out.println(LINE);
    }

    public void showWelcome() {
        System.out.println(LINE);
        System.out.println(GREETINGS);
        System.out.println(HELP);
        System.out.println(LINE);
    }

    public void showCommandInfo(String result) {
        System.out.println(LINE);
        System.out.println(result);
        System.out.println(LINE);
    }

    public void showExceptionInfo(Exception e) {
        System.out.println(LINE);
        System.out.println(e.toString());
        System.out.println(HELP);
        System.out.println(LINE);

    }

    public void showDataSaved() {
        System.out.println(LINE);
        System.out.println(SAVED);
        System.out.println(LINE);
    }
    public void showEnding() {
        System.out.println(LINE);
        System.out.println(ENDING);
        System.out.println(LINE);
    }
}
