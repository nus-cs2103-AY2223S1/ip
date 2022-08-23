package roger;

import java.util.Scanner;

/**
 * Encapsulates interactions between the user and the program.
 * Interactions include taking input and displaying outputs.
 */
public class Ui {

    /**
     * Show a welcome message.
     */
    public void showWelcome() {
        String logo = "  ____                                \n"
                    + " |  _ \\    ___     __ _    ___   _ __ \n"
                    + " | |_) |  / _ \\   / _` |  / _ \\ | '__|\n"
                    + " |  _ <  | (_) | | (_| | |  __/ | |   \n"
                    + " |_| \\_\\  \\___/   \\__, |  \\___| |_|   \n"
                    + "                  |___/               ";
        System.out.println("Hello its \n" + logo);
        System.out.println("What you wan? What you wan?");
    }

    /**
     * Show a line of text.
     *
     * @param string The text to be shown.
     */
    public void show(String string) {
        System.out.println(string);
    }

    /**
     * Show a few lines of text.
     *
     * @param strings The texts to be shown.
     */
    public void show(String[] strings) {
        for (String string: strings) {
            this.show(string);
        }
    }

    /**
     * Showcase a piece of information.
     *
     * @param preface Preface to the showcased content.
     * @param content The content to be showcased.
     */
    public void showcase(String preface, String content) {
        System.out.println(preface);
        System.out.println("  " + content);
    }

    /**
     * Show a farewell message.
     */
    public void showFarewell() {
        System.out.println("Bye bye niece and nephew.");
    }

    /**
     * Show an exception.
     *
     * @param exception The exception to be shown.
     */
    public void showException(Exception exception) {
        System.out.println("Nephew, what you doing? " + exception.getMessage());
    }

    /**
     * Take one line of user input.
     *
     * @return The line of input taken from the user.
     */
    public String getUserInput() {
        return new Scanner(System.in).nextLine();
    }

}
