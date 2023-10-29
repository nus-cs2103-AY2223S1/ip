package zeus;

/**
 * The user interface that the user sees.
 *
 * @author Derrick Khoo
 */
public class Ui {
    /**
     * The greeting from Zeus that the user sees upon running a new instance of Zeus.
     *
     * @return the greeting from Zeus
     */
    public String greet() {
        String output = "";
        output += "Hello! I'm Zeus\n";
        output += "What can I do for you?";
        output += "You can add your todos, deadlines and events,\n";
        output += "and i will keep track of them for you at no cost!";
        return output;
    }

    /**
     * Returns the message that user inputs.
     *
     * @return the message that user inputs
     */
    public String formatMessage(String msg) {
        return msg;
    }
}
