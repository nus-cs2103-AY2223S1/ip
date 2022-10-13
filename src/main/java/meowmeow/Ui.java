package meowmeow;

/**
 * <p>Class Ui is a class that handles the user interface.</p>
 * <p>This class is used to display the result of the command.</p>
 * <p>This class is a concrete class because it has an implementation.</p>
 */
public class Ui {

    /**
     * Returns the welcome message.
     * @return String welcome message.
     */
    public String showWelcome() {
        //Chatbot intro segment
        String name = "MeowMeow (=^ↀWↀ^=)";

        String intro = "Hewwo! I'm" + name + "\nWhat do you need meow to do for you today?";
        return intro;
    }
}
