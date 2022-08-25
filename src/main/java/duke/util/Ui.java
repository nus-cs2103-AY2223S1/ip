package duke.util;

public class Ui {
    private static final String LOGO = "        Duke\n";

    private static final String GREET = "~~~~ Hello I am ~~~~\n" + LOGO + "What do you need help with?";

    private static final String BYE = "See you soon! Don't worry, I saved the duke.data!";

    public static void formatMessage(String input) {
        System.out.println("vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv\n"
            + input + "\n^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
    }

    public static void greet() {
        formatMessage(GREET);
    }

    public static void bye() {
        formatMessage(BYE);
    }
}
