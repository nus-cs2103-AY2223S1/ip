package duke;

/**
 * The Ui class handles the different interactions with the user.
 */

class Ui {
    private static final String GREETING = "Hi friend! How may I help you?";
    private static final String FAREWELL = "See you soon, friend!";

    /**
     * Shows a pre-defined greeting to the user.
     */
    public static void sayHello() {
        System.out.println(Ui.GREETING);
    }

    /**
     * Shows a pre-defined farewell to the user.
     */
    public static void sayGoodbye() {
        System.out.println(Ui.FAREWELL);
    }

    /**
     * Shows a message to the user.
     * 
     * @param message a relevant message to be displayed by the Ui.
     */
    public static void echo(String message) {
        System.out.println(message);
    }
}
