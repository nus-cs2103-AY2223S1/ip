package caca.ui;

/**
 * This class deals with interactions with the user.
 *
 * @author Carrie Zheng Jiarui
 * @version CS2103T AY22/23 Semester 1, iP
 */
public class Ui {

    /**
     * Greets user with CaCa logo and greeting.
     *
     * @return Greeting message.
     */
    public static String greet() {

        String greeting = "Hello! I'm CaCa.\n"
                + "What can I do for you?\n";

        return greeting;
    }

    /**
     * Says bye to user.
     *
     * @return Goodbye message.
     */
    public String bye() {
        return "Bye. Hope to see you again soon!";
    }

}
